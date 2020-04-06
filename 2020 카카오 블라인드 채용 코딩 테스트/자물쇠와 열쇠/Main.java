class Main {
	public static void main(String[] args){
        int[][] key = { {0,0,0},
                        {1,0,0},
                        {0,1,1}};

        int[][] lock = { {1,1,1},
                        {1,1,0},
                        {1,0,1}};

        boolean answer = solution(key, lock);
        System.out.println(answer);
    }
    
    
    public static boolean solution(int[][] key, int[][] lock){
        boolean answer = false;

        int lock_size = lock.length;
        int key_size = key.length;
        
        int[][] bigLock = new int[lock_size*2 + lock_size-2][lock_size*2 + lock_size - 2];
        int diff = lock_size - key.length;

        // 크게 확장한 열쇠 만들기
        for (int i = 0; i < lock_size; i++) {
            for (int j = 0; j < lock_size; j++) {
                bigLock[i+lock_size-1][j+lock_size-1] += lock[i][j];
            }
        }


        // 열쇠와 자물쇠 맞춰보기
        for (int w = 0; w< lock_size*2 + diff - 1; w++) {
            for (int l = 0; l < lock_size*2 + diff - 1; l++) {
                for (int k = 0; k < 3; k++) {
                    int[][] copyLock = deepCopy(bigLock);
        
                    for (int i = 0; i < key.length; i++) {
                        for (int j = 0; j < key.length; j++) {
                            copyLock[i+w][j+l] += key[i][j];
                        }
                    }    
        
                    if(!isOpen(lock_size, copyLock)){
                        key = rotate90(key);
                    }
                    else
                        return true;
                    
                }
                key = rotate90(key);
            }
        }
        
        return answer;
    }

    public static int[][] deepCopy(int[][] origin){
        if(origin == null) return null;

        int[][] dest = new int[origin.length][origin[0].length];

        for (int i = 0; i < origin.length; i++) {
            System.arraycopy(origin[i], 0, dest[i], 0, origin[0].length);
        }

        return dest;
    }

    public static int[][] rotate90(int[][] arr){
        int len = arr.length;
        int[][] rotated = new int [len][len];

        
        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated.length; j++) {
                rotated[i][j] = arr[len-j-1][i];
            }
        }

        return rotated;
    }

    
    //lock의 모든 부분이 1이되면 됨
    public static boolean isOpen(int size, int[][] arr){
        boolean flag = true;

        for (int i =0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(arr[i+size-1][j+size-1] != 1){
                    return false;
                }
            }   
        }

        return flag;
    }
}
