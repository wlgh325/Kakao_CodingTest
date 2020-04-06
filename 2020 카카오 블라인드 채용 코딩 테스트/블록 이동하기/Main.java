import java.util.ArrayList;

class Main{

    public static void main(String[] args){
        int[][] board = {{0,0,0,1,1},
                        {0,0,0,1,0},
                        {0,1,0,1,1,},
                        {1,1,0,0,1},
                        {0,0,0,0,0}};

        int answer = solution(board);
    }

    // 로봇 크기 : 2x1
    // 가장 왼쪽 상단: 1,1

    // 0 : 빈칸
    // 1: 벽

    // 90도 회전 가능
    // 회전하는데 1초 걸림
    // dir 상하좌우, 0123
    // 오르쪽 축으로 위 아래 45
    // 왼쪽 축으로 위 아래 67
    static int robotX = 0;
    static int robotY = 0;

    static int dir = 3; // 상하좌우
    public static int solution(int[][] board) {        
        int answer = 0;
        
        bfs(board);
        return answer;
    }

    public static int bfs(int[][] board){
        ArrayList<Integer> commandList = find(board);
        return 0;
    }
    
    public static ArrayList<Integer> find(int[][] board){
        ArrayList<Integer> commandList = new ArrayList<>();

        // 상하좌우 확인하기{
        switch(dir){
            case 0:
                // 위쪽
                if(robotX-1 >0){
                    if(board[robotX-2][robotY] != 0)
                        commandList.add(0);
                }
                // 아래쪽
                if(robotX < board.length - 1){
                    if(board[robotX+1][robotY] != 0)
                        commandList.add(1);
                }
                // 왼쪽
                if(robotY >= 1){
                    if(board[robotX][robotY-1] != 0 && board[robotX][robotY-1] != 0)
                        commandList.add(2);
                }
                // 오른쪽
                if(robotY < board.length - 1){
                    if(board[robotX][robotY+1] != 0 && board[robotX][robotY+1] != 0)
                        commandList.add(3);
                }
                break;
            case 1:
                // 위쪽
                if(robotX > 0){
                    if(board[robotX-1][robotY] != 0)
                        commandList.add(0);
                }
                // 아래쪽
                if(robotX +1 < board.length - 1){
                    if(board[robotX-1-1][robotY] != 0)
                        commandList.add(1);
                }
                // 왼쪽 확인
                if(robotY-1 >= 0){
                    if(board[robotX][robotY-1] != 0 && board[robotX+1][robotY-1] != 0)
                        commandList.add(2);
                }
                // 오른쪽 확인
                if(robotY +1 < board.length - 1){
                    if(board[robotX][robotY+1] != 0 && board[robotX+1][robotY+1] != 0)
                        commandList.add(3);
                }
                break;
            case 2:
                // 위쪽
                if(robotX > 0){
                    if(board[robotX-1][robotY-1] != 0 && board[robotX-1][robotY] != 0)
                        commandList.add(0);
                }
                // 아래쪽 확인
                if(robotX < board.length - 1){
                    if(board[robotX+1][robotY-1] != 0 && board[robotX+1][robotY] != 0)
                        commandList.add(1);
                }
                // 왼쪽 확인
                if(robotY-1 >= 0){
                    if(board[robotX][robotY-1-1] != 0)
                        commandList.add(2);
                }
                // 오른쪽 확인
                if(robotY < board.length-1){
                    if(board[robotX][robotY+1] != 0)
                        commandList.add(3);
                }
                break;
            case 3:
                // 위쪽 확인
                if(robotX > 0){
                    if(board[robotX-1][robotY] != 0 && board[robotX-1][robotY+1] != 0)
                        commandList.add(0);
                }
                // 아래쪽 확인
                if(robotX < board.length - 1){
                    if(board[robotX+1][robotY] != 0 && board[robotX+1][robotY+1] != 0)
                        commandList.add(1);
                }
                // 왼쪽 확인
                if(robotY-1 >= 0){
                    if(board[robotX][robotY-1] != 0)
                        commandList.add(2);
                }
                // 오른쪽 확인
                if(robotY < board.length-1){
                    if(board[robotX][robotY+1+1] != 0)
                        commandList.add(3);
                }
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
        }
        return commandList;
    }
}