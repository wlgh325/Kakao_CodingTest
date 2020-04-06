class Main {
	public static void main(String[] args){
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		if(solution(key, lock))
			System.out.println("true");
		else
			System.out.println("false");
	}
	
    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int size = lock.length;
        int[][] newLock = new int[size*3-2][size*3-2];
        // 자물쇠를 둘러싼 빈 공간 생성
        for(int i=size-1, end=size*2-1; i<end; i++) 
        	System.arraycopy(lock[i-(size-1)], 0, newLock[i], size-1, lock[i-(size-1)].length);
        
        int newSize = newLock.length;
        int keySize = key.length;
        boolean flag = false;
        
        for(int i=0; i<4; i++) {
        	key = rotate(key);
        	
        	int[][] copy = new int[newSize][newSize];
        	// sliding 방식으로 모두 차례차례 맞춰보기
        	for(int x=0; x<=newSize-keySize; x++) {
                for(int y=0; y<=newSize-keySize; y++) {
                    // 원본 배열 유지를 위해 복사
        			for(int j=0; j<newSize; j++)
        				System.arraycopy(newLock[j], 0, copy[j], 0, newSize);
        			for(int j=0; j<keySize; j++) {
                		for(int k=0; k<keySize; k++) {
                			copy[j+x][k+y] += key[j][k];
                		}
                	}
        			print(copy);
        			if(check(copy, size)) {
        				answer = true;
        				flag = true;
        				break;
        			}
        		}
        		if(flag)
        			break;
        	}
        	if(flag)
        		break;
        }
        return answer;
    }
    
    public static boolean check(int[][] lock, int size) {
    	boolean flag = true;
    	for(int i=size-1; i<size*2-1; i++) {
    		for(int j=size-1; j<size*2-1; j++) {
    			if(lock[i][j] != 1) {
    				flag = false;
    				break;
    			}
    		}
    		if(!flag)
    			return flag;
    	}
    	return flag;
    }
    
    // 시계방향 회전
    public static int[][] rotate(int[][] key) {
    	int[][] dest = new int[key.length][key[0].length];
    	for(int i=0; i<key.length; i++) {
    		for(int j=0; j<key[0].length; j++) {
    			dest[i][j] = key[key.length-j-1][i];    			
    		}
    	}
    	return dest;
    }
}