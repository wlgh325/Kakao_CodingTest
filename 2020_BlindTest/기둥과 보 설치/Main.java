import javax.lang.model.util.ElementScanner6;

class Main{
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

        int[][] answer = solution(words, queries);
    }


    // return : [x, y, a]

    
    static int[][][] map = new int[n][n][1];


    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        // build_frame = {x, y, 종류, 설치/삭제}
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int kinds = build_frame[i][2];
            int menu = build_frame[i][3];

            // 1: 설치
            if(menu == 1){
                if(isCorrect(x,y,kinds)){
                    switch(kinds){
                        // 기둥
                        case 0:
                            map[x][y][0] = kinds;
                            map[x][y+1][0] = kinds;
                            break;
                        // 보
                        case 1:
                            map[x][y][1] = kinds;
                    }
                    
                }
                
            }
            else if(menu == 0){

            }
        }

        System.out.println("h");
        return answer;
    }

    public static boolean isCorrect(int x, int y, int kinds){
        boolean flag = true;

        switch(kinds){
            case 0:
                // 바닥에 있어야 함
                if(y == 0)
                    return flag;
                // 보의 한쪽 끝 부분
                else if(map[x][y] == 1){
                    return flag;
                }
                // 다른 기둥위에 있어야 함
                else if(map[x][y] == 0){
                    return flag;
                }
                else
                    flag = false;
                break;
            case 1:
                // 한쪽 끝이 기둥위에 있어야 함
                if(map[x][y] == 0 || map[x+1][y] == 0){
                    return flag;
                }
                // 양쪽 끝에 보가 있어야 함
                else if(map[x][y] == 1 && map[x+1][y] == 1){
                    return flag;
                }
                else{
                    flag = false;
                }

        }
        return flag;
    }
}