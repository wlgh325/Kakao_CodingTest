import java.util.LinkedList;
import java.util.Queue;

class Info {
	int x;
	int y;
	int dir;
	int time;
	
	Info(int x, int y, int dir, int time){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.time = time;
	}
}

class Main {
    public static void main(String[] args){
        int[][] board = {{0,0,0,1,1},
                        {0,0,0,1,0},
                        {0,1,0,1,1,},
                        {1,1,0,0,1},
                        {0,0,0,0,0}};

        System.out.println(solution(board));
    }
    
    static boolean[][][] visited;
    // 우, 하, 좌, 상
    static int[] xdir = {0,1,0,-1};
    static int[] ydir = {1,0,-1,0};
    static int N;
    public static int solution(int[][] board) {
    	int ans = Integer.MAX_VALUE;
    	Queue<Info> q = new LinkedList<>();
    	N = board.length;
    	visited = new boolean[N][N][4];
    	
    	q.offer(new Info(0,0,0,0));
    	visited[0][0][0] = true;
        
        while(!q.isEmpty()) {
        	Info info = q.poll();
        	int x = info.x;
        	int y = info.y;
        	int dir = info.dir;
        	int time = info.time;
        	
        	int x2 = x + xdir[dir];
        	int y2 = y + ydir[dir];
        	
        	if(x == N-1 && y == N-1 || (x2 == N-1 && y2 == N-1)) {
        		ans = ans > time ? time : ans;
        		continue;
        	}
        	
        	int dx=0, dy=0, dx2=0, dy2=0;
    		for(int i=0; i<4; i++) {
        		dx = x + xdir[i];
        		dy = y + ydir[i];
        		dx2 = x2 + xdir[i];
        		dy2 = y2 + ydir[i];
        		
    			// 유효 범위 면서 방문하지 않았고 벽이 아닌 경우 이동가능
    			if(isValidPosition(dx,dy) && isValidPosition(dx2,dy2) && board[dx][dy] != 1 && board[dx2][dy2] != 1) {
    				if(!visited[dx][dy][dir]) {
    					visited[dx][dy][dir] = true;
    					q.offer(new Info(dx,dy,dir,time+1));    					
    				}
    			}
			}
    		
    		// (x,y) 기준 회전
    		for(int i=0; i<2; i++) {
    			int rx = 0;
    			int ry = 0;
    			int ddir = 0;
    			// 가로
    			if(dir%2 == 0) {
    				if(i==0) {
    					ddir = 3;
    					// 0-> 3
    					if(dir == 0) {
    						rx = x-1;
        					ry = y+1;
    					}
    					else if(dir == 2) {
    						// 2->3
    						rx = x-1;
    						ry = y-1;
    					}
    				}
    				else {
    					ddir = 1;
    					if(dir == 0) {
    						// 0 ->1
    						rx = x+1;
        					ry = y+1;	
    					}
    					else if(dir == 2) {
    						// 2->1
    						rx = x+1;
    						ry = y-1;
    					}
    				}
    			}
    			else {
    				if(i==0) {
    					ddir = 2;
    					// 1-> 2
    					if(dir == 1) {
    						rx = x+1;
        					ry = y-1;
    					}
    					else {
    						// 3->2
    						rx = x-1;
    						ry = y-1;
    					}
    				}
    				else {
    					ddir = 0;
    					if(dir == 1) {
    						// 1->0
    						rx = x+1;
        					ry = y+1;	
    					}
    					else {
    						// 3->0
    						rx = x-1;
    						ry = y+1;
    					}
    				}
    			}
    			if(!isValidPosition(rx, ry)) continue;
    			if(board[rx][ry] == 1) continue;
    			
    			dx2 = x + xdir[ddir];
    			dy2 = y + ydir[ddir];
    			if(isValidPosition(x, y) && isValidPosition(dx2, dy2) && board[x][y] != 1 && board[dx2][dy2] != 1) {
    				if(!visited[x][y][ddir]) {
    					visited[x][y][ddir] = true;
    					q.offer(new Info(x,y,ddir,time+1));
    				}
    			}
    		}
    		
    		dx2 = x + xdir[dir];
    		dy2 = y + ydir[dir];
    		dir = (dir+2) % 4;	// 방향 반대로
    		for(int i=0; i<2; i++) {
    			int ddir = 0;
    			int rx=0, ry=0;
    			
    			// 가로
    			if(dir%2 == 0) {
    				// 시계
    				if(i==0) {
    					ddir = 3;
    					if(dir == 0) {
    						// 0->3
    						rx = dx2-1;
    						ry = dy2+1;
    					}
    					else {
    						// 2->3
    						rx = dx2-1;
        					ry = dy2-1;
    					}
    				}
    				else {
    					ddir = 1;
    					if(dir == 0) {
    						// 0->1
    						rx = dx2+1;
    						ry = dy2+1;
    					}
    					else {
    						// 2->1
    						rx = dx2+1;
        					ry = dy2-1;	
    					}
    					
    				}
    			}
    			else {
    				if(i==0) {
    					ddir = 2;
    					if(dir == 1) {
    						// 1->2
    						rx = dx2+1;
    						ry = dy2-1;
    					}
    					else {
    						// 3->2
    						rx = dx2-1;
        					ry = dy2-1;	
    					}
    				}
    				else {
    					ddir = 0;
    					if(dir == 1) {
    						// 1 -> 0
    						rx = dx2+1;
    						ry = dy2+1;
    					}
    					else {
    						// 3 -> 0
    						rx = dx2-1;
        					ry = dy2+1;
    					}
    				}
    			}
    			
    			if(!isValidPosition(rx, ry)) continue;
    			if(board[rx][ry] == 1) continue;
    			
    			dx = dx2 + xdir[ddir];
    			dy = dy2 + ydir[ddir];
    			
    			if(isValidPosition(dx, dy) && isValidPosition(dx2, dy2) && board[dx][dy] != 1 && board[dx2][dy2] != 1) {
    				if(!visited[dx2][dy2][ddir]) {
    					visited[dx2][dy2][ddir] = true;
    					q.offer(new Info(dx2,dy2,ddir,time+1));
    				}
    			}
    		}
        }
        
        return ans; 
    }
    
    private static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= N || y >= N) return false;
    	return true;
    }
}