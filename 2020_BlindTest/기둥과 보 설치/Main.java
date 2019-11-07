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

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        return answer;
    }
}