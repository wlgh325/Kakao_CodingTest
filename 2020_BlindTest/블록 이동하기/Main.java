import java.util.ArrayList;
import java.util.Comparator;

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

        int[][] answer = solution(n, build_frame2);
    }


    // return : [x, y, a]

    
    static int[][][] map;
    static ArrayList<Info> result;

    public static int[][] solution(int n, int[][] build_frame) {        
        result = new ArrayList<>();

        map = new int[n+1][n+1][2];
        // build_frame = {x, y, 종류, 설치/삭제}
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int kinds = build_frame[i][2];
            int menu = build_frame[i][3];

            // 1: 설치
            if(menu == 1){
                if(isEstablishment(x, y, kinds)){
                    map[x][y][kinds] = 1;
                    Info info = new Info(x,y,kinds);
                    result.add(info);
                }
                    
            }
            else if(menu == 0){
                if(isRemove(x,y,kinds)){
                    int idx = findInfo(x, y, kinds);
                    result.remove(idx);
                }
                else 
                    map[x][y][kinds] = 1;                
            }
        }

        //sort
        result.sort(new Comparator<Info>() {
            @Override
            public int compare(Info arg0, Info arg1){
                int a = arg0.x;
                int b = arg1.x;
                if(a == b){
                    int c = arg0.y;
                    int d = arg1.y;

                    if(c == d){
                        int e = arg0.kinds;
                        int f = arg1.kinds;
                        if(e == f)
                            return 0;
                        else if(e > f)
                            return 1;
                        else
                            return -11;
                    }
                    else if(c > d)
                        return 1;
                    else
                        return -1;
                }
                else if(a > b)
                    return 1;
                else
                    return -1;
            }
        });

        int[][] answer = new int[result.size()][3];
        
        // 배열로 변환
        for (int i = 0; i < result.size(); i++) {
            answer[i][0] = result.get(i).x;
            answer[i][1] = result.get(i).y;
            answer[i][2] = result.get(i).kinds;
        }
        System.out.println("h");
        return answer;
    }

    public static boolean isRemove(int x, int y, int kinds){
        boolean flag = true;

        map[x][y][kinds] = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < 2; k++) {
                    if(map[i][j][k]== 1){
                        map[i][j][k] = 0;
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

    public static int findInfo(int x, int y, int kinds){
        int idx = -1;

        for (int i = 0; i < result.size(); i++) {
            Info info = result.get(i);
            if(info.x == x && info.y == y && info.kinds == kinds){
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static boolean isEstablishment(int x, int y, int kinds){
        boolean flag = false;

        switch(kinds){
            case 0:
                // 보가 있는 경우
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