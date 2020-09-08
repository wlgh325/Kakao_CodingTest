class Main {
	public static void main(String[] args){
		String[] data = {"N~F=0", "R~T>2"};
		int n = 8;
		System.out.println(solution(n, data));
	}
	
	static int ans;
    static char[] order;
    static boolean[] visited;
    static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	static int cnt;
	
	public static int solution(int n, String[] data) {
        // 경우의 수 찾기
        // 조건은 최대 100개
        // 총 8명
        ans = 0;
        cnt = 0;
        order = new char[8];
        visited = new boolean[8];
        
        perm(0, n, data);
        return ans;
    }

	// 가능한 순열 구하기
    public static void perm(int depth, int n, String[] data){
        if(depth == 8){
			// 문자열 변환
			String temp = "";
			for(int i=0; i<8; i++)
				temp += order[i];

			// 모든 조건을 만족하는지 check
			for(int i=0; i<n; i++){
				String str = data[i];
				char a = str.charAt(0);
				char b = str.charAt(2);
				char ch = str.charAt(3);
				int num = str.charAt(4) - '0' + 1; // 아무도 없다는 것은 index 차이로 보면 1차이

				int aidx = temp.indexOf(a);
				int bidx = temp.indexOf(b);

				// index의 차이를 구해서 몇명있는지 판단
				if(ch == '='){
					if(Math.abs(aidx - bidx) != num)
						return;
				}
				else if(ch == '<'){
					if(Math.abs(aidx - bidx) >= num)
						return;
				}
				else if(ch == '>'){
					if(Math.abs(aidx - bidx) <= num)
						return;
				}
			}

			ans++;
            return;
        }
        
        for(int i=0; i<8; i++){
            if(!visited[i]){
                visited[i] = true;
                order[depth] = arr[i];
                perm(depth+1, n, data);
                visited[i] = false;
            }
        }
    }
}