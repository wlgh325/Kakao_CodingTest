import java.util.*;
class KakaoIntern2019_5 {
	public static void main(String[] args){
		int[] stones = {2,4,5,3,2,1,4,2,5,1};
		int k = 3;
		System.out.println(solution(stones, k));	// 3 출력
	}
	
	public static int solution(int[] stones, int k) {
		int ans = 0;		
		boolean flag = false;

		while(true) {
			int cnt = 0;
			for(int j=0; j<stones.length; j++) {
				if(stones[j] != 0) {
					stones[j]--;
					cnt = 0;
				}
				else {
					cnt++;
				}
				if(cnt >= k) {
					flag = true;
					break;
				}
			}
			if(flag)
				break;
			ans++;
		}
		// 연속된 0이 k개 이상이면 못 건넘
		return ans;
	}

	// segment tree를 만든다
	// 1만큼 뺀다.
	// 길이 k만큼의 구간합을 구해가며 0이 나온다면 더 이상 건널 수 없다.
	public static int solution2(int[] stones, int k) {
		int ans = 0;
		
		return ans;
	}
}