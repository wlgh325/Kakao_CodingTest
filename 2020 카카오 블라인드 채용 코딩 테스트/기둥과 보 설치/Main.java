import java.util.ArrayList;

class Info {
    int x;
    int y;
    int kinds;

    Info(int x, int y, int kinds){
        this.x = x;
        this.y = y;
        this.kinds = kinds;
    }
}

class Main {
   public static void main(String[] args){
        int n = 5;
        int[][] build_frame = {{1,0,0,1},
        {1,1,1,1},
        {2,1,0,1},
        {2,2,1,1},
        {5,0,0,1},
        {5,1,0,1},
        {4,2,1,1},
        {3,2,1,1}};

        int n2 = 5;
        int[][] build_frame2 = {{0,0,0,1},
        {2,0,0,1},
        {4,0,0,1},
        {0,1,1,1},
        {1,1,1,1},
        {2,1,1,1},
        {3,1,1,1},
        {2,0,0,0},
        {1,1,1,0},
        {2,2,0,1}};

        int[][] answer = solution(n, build_frame2);
        for(int i=0; i<answer.length; i++) {
        	for(int j=0; j<3; j++) {
        		System.out.print(answer[i][j] + " ");
        	}
        	System.out.println();
        }
   }
   
   static int[][][] map;
   public static int[][] solution(int n, int[][] build_frame) {
	   map = new int[n+1][n+1][2];
       // [x,y,a,b] : [좌표x, 좌표y, 구조물 종류, 설치/삭제]
       // 구조물은 오른쪽으로, 기둥은 위쪽으로 설치/삭제
       
       // n개 명령 수행
       for(int i=0; i<build_frame.length; i++) {
    	   int x = build_frame[i][0];
    	   int y = build_frame[i][1];
    	   int kinds = build_frame[i][2];
    	   int cmd = build_frame[i][3];
    	   
    	   // 1: 설치
           if(cmd == 1){
               if(isEstablishment(x, y, kinds))
                   map[x][y][kinds] = 1;
           }
           else if(cmd == 0){
        	   // 제거 가능한지 확인
               if(isRemove(x,y,kinds))
            	   map[x][y][kinds] = 0;
               else 
                   map[x][y][kinds] = 1;                
           }
       }
       
       ArrayList<Info> list = new ArrayList<>();
       // answer 배열에 담을 설치된 구조물들 list에 추가
       for(int i=0; i<n+1; i++) {
    	   for(int j=0; j<n+1; j++) {
    		   if(map[i][j][0] == 1)
    			   list.add(new Info(i,j,0));
    		   if(map[i][j][1] == 1)
    			   list.add(new Info(i,j,1));
    	   }
       }
       // answer 배열에 담기
       int[][] answer = new int[list.size()][3];
       for(int i=0; i<list.size(); i++) {
    	   Info p = list.get(i);
    	   answer[i][0] = p.x;
    	   answer[i][1] = p.y;
    	   answer[i][2] = p.kinds;
       }
       return answer;
   }
   
   public static boolean isRemove(int x, int y, int kinds){
       boolean flag = true;
       
       // 일단 제거
       map[x][y][kinds] = 0;
       
       // 제거한 뒤에 지금 있는 장애물들이 설치가 가능한지 검사
       // 다시 설치가 가능하다면 제거해도 문제가 없다!
       for (int i = 0; i < map.length; i++) {
           for (int j = 0; j < map.length; j++) {
               for (int k = 0; k < 2; k++) {
            	   // 장애물이 설치되어 있다면
                   if(map[i][j][k]== 1){
                	   // 제거해보기
                       map[i][j][k] = 0;
                       // 그 위치에 방금 제거한 장애물이 설치가 가능한지 확인
                       if(!isEstablishment(i, j, k)){
                           map[i][j][k] = 1;
                           return false;
                       } 
                       map[i][j][k] = 1;
                   }
               }
           }
       }

       return flag;
   }

   public static boolean isEstablishment(int x, int y, int kinds){
       boolean flag = false;
       // 보와 기둥을 나눠서 체크
       switch(kinds){
           case 0:
               // 그 위치에 보가 있는 경우 보 위에 설치 가능
               if(map[x][y][1] == 1)
                   return true;
               // 왼쪽에 보의 끝에 걸친 경우
               if(x-1 >= 0 && map[x-1][y][1] == 1)
                   return true;
               // 바닥에 설치하는 경우
               if(y == 0)
                   return true;
               // 다른 기둥 위에 있는 경우
               if(y-1 >= 0 && map[x][y-1][0] == 1)
                   return true;

               break;
           // 보를 세우기
           case 1:
               // 한쪽 끝이 기둥 위에 있는 경우
               if(y-1 >= 0 && (map[x][y-1][0] == 1 || map[x+1][y-1][0] == 1))
                   return true;
               // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는 경우
               if(x-1 >= 0 && map[x-1][y][1] == 1 && map[x+1][y][1] == 1)
                   return true;

       }
       return flag;
   }
}