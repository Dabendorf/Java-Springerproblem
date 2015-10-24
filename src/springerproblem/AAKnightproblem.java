package springerproblem;

public class AAKnightproblem {

	public static void main(String[] args) {
		System.out.println(new AAKnightproblem().new Solver().getResultField());
	}

	class Solver {

		private int[][] result = null;
		private final int size = 8;
		private int[][] jumps = { {-1,2}, {-2,1}, {1,-2}, {2,1}, {-1,-2}, {-2,-1}, {1,2}, {2,-1} };
		
		public Solver() {
			this.doIt(0, 0, new int[size][size], 1);
		}

		private boolean doIt(int x, int y, int[][] visited, int jump) {
			if(jump == (visited.length*visited.length)) {
				visited[x][y] = jump;
				result = visited;
				return true;
			}

			for(int i=0; i<8; i++) {
				if((x+jumps[i][0]) > -1 && (y+jumps[i][1]) > -1 && (x+jumps[i][0]) < visited.length && (y+jumps[i][1]) < visited.length) {
					if(visited[x+jumps[i][0]][y+jumps[i][1]] == 0) {
						visited[x][y] = jump;
						if(doIt((x+jumps[i][0]), (y+jumps[i][1]), visited, jump+1))
							return true;
						visited[x][y] = 0;
					}
				}
			}
			return false;
		}

		public String getResultField(){
			String returnString = "";
			for(int[] row : result) {
				for(int col : row) {
					returnString += (col +"\t");
				}
				returnString += "\n";
			}
			return returnString;
		}

	}

}