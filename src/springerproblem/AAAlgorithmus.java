package springerproblem;

public class AAAlgorithmus 
{
    //Feldgröße: 6*6
    static int a=5;
    // xWerte und yWerte speichern alle 8 möglichen Richtungen, 
    // in die sich der Springer bewegen kann 
    // Das erste paar (00) wird nur beim ersten Aufruf benötigt
    final static int[] xWerte = {0,-1,-2, 1, 2,-1,-2, 1, 2};
    final static int[] yWerte = {0, 2, 1,-2, 1,-2,-1, 2,-1};
    static int[] xWeg  = new int[a*a];
    static int[] yWeg  = new int[a*a];
    static boolean first=true;
    
    public static boolean solve(int x,int y, int num)
    {    
        int xNeu, yNeu,k;
        boolean solved;
        k=0;       
        
        do{        
            ++k;
            
            if(first==true) 
            {
                --k;
                first=false;
            }
            solved=false;
            
            //Neue Koordinaten
            xNeu=x+xWerte[k];
            yNeu=y+yWerte[k];
            
            //Ist der Zug gültig?
            if(xNeu>=0 && yNeu>=0 && xNeu<a && yNeu<a)
            {
                //Ist das Feld frei?
                if(AASpringerproblemAlt.position[xNeu][yNeu]==0)
                {          
                    //Setze in das Feld
                    AASpringerproblemAlt.position[xNeu][yNeu]=num;
                    xWeg[num-1]=x;
                    yWeg[num-1]=y;
                    
                    //Problem gelöst?
                    if(num<(a*a))
                    {
                        //Wenn Problem noch nicht gelöst: Rekursion
                        solved=solve(xNeu,yNeu,num+1);
                        //Wenn der Rekursionsschritt in eine Sackgasse
                        //geführt hat, wird er zurück genommen
                        if(solved==false)
                        {
                            AASpringerproblemAlt.position[xNeu][yNeu]=0;
                        }
                    }    
                    else
                        solved=true;
                    
                }
            }  
        } while(solved==false && k<8);
        return solved;
    }
}