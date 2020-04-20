import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	public static void main(String[] args){
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		int[] ans = solution(6,4,picture);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	
	static boolean[][] visited;
	static int xlen, ylen;
	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		
		int[] answer = new int[2];
		xlen = picture.length;
		ylen = picture[0].length;
		visited = new boolean[xlen][ylen];

		for(int i=0; i<picture.length; i++){
			for(int j=0; j<picture[0].length; j++){
				if(!visited[i][j] && picture[i][j] != 0){
					int temp = bfs(i,j,picture);
					maxSizeOfOneArea = maxSizeOfOneArea < temp ? temp : maxSizeOfOneArea;
					numberOfArea++;
				}
			}
		}

		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public static int bfs(int a, int b, int[][] picture){
		Queue<Pos> q = new LinkedList<>();
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		int area=0;
		

		visited[a][b] = true;
		area++;
		q.offer(new Pos(a,b));

		int color = picture[a][b];
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];

				if(isValidPosition(dx, dy) && !visited[dx][dy] && picture[dx][dy] == color){
					visited[dx][dy] = true;
					q.offer(new Pos(dx,dy));
					area++;
				}
			}
		}

		return area;
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || y < 0 || x >= xlen || y >= ylen) return false;
		return true;
	}
}
