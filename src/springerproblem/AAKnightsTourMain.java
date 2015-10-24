package springerproblem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class AAKnightsTourMain
{
    public static void main(String[] args)
    {
        KnightsTourSolver k = new KnightsTourSolver(8, 8);
        //k.solve();
        k.solve(4,4,1);
    }
}
 
class KnightsTourSolver
{
    private final int sizeX;
    private final int sizeY;
    private final int board[];
    private final Point deltas[] =
    {
        new Point(-1,  2),
        new Point( 1,  2),
        new Point(-1, -2),
        new Point( 1, -2),
        new Point(-2,  1),
        new Point( 2,  1),
        new Point(-2, -1),
        new Point( 2, -1),
    };
   
    public KnightsTourSolver(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new int[sizeX*sizeY];
    }
   
    private int get(int x, int y)
    {
        return board[x+y*sizeX];
    }
   
    private void set(int x, int y, int n)
    {
        board[x+y*sizeX] = n;
    }
   
    private boolean isValid(int x, int y)
    {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }
   
    private List<Point> computeTargets(int x, int y)
    {
        List<Point> targets = new ArrayList<Point>();
        for (Point delta : deltas)
        {
            int tx = x + delta.x;
            int ty = y + delta.y;
            if (isValid(tx, ty) && get(tx, ty) == 0)
            {
                targets.add(new Point(tx, ty));
            }
        }
        return targets;
    }
   
    boolean solve()
    {
        for (int x=0; x<sizeX; x++)
        {
            for (int y=0; y<sizeY; y++)
            {
                boolean solved = solve(x, y, 1);
                if (solved)
                {
                    return true;
                }
            }
        }
        return false;
    }
   
    public boolean solve(int currentX, int currentY, int step)
    {
        if (step == sizeX * sizeY)
        {
            set(currentX, currentY, step);
            System.out.println("After step "+step);
            print();
            return true;
        }
        set(currentX, currentY, step);
       
        System.out.println("After step "+step);
        print();
       
        List<Point> targets = computeTargets(currentX, currentY);
       
        applyWarnsdorffRule(targets);
       
        for (Point target : targets)
        {
            boolean solved = solve(target.x, target.y, step+1);
            if (solved)
            {
                return true;
            }
        }
        set(currentX, currentY, 0);
        return false;
    }
   
    private void applyWarnsdorffRule(List<Point> targets)
    {
        Map<Point, Integer> targetCounters = new HashMap<Point, Integer>();
        for (Point target : targets)
        {
            List<Point> nextTargets = computeTargets(target.x, target.y);
            targetCounters.put(target, nextTargets.size());
        }
        Collections.sort(targets, new Comparator<Point>()
        {
            @Override
            public int compare(Point p0, Point p1)
            {
                Integer c0 = targetCounters.get(p0);
                Integer c1 = targetCounters.get(p1);
                return Integer.compare(c0, c1);
            }
        });
    }
   
   
    private void print() {
        StringBuilder sb = new StringBuilder();
        for (int y=0; y<sizeY; y++) {
            for (int x=0; x<sizeX; x++) {
                int v = get(x,y);
                if (v == 0) {
                    sb.append(" .. ");
                } else {
                    sb.append(String.format(" %2d ", v));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}