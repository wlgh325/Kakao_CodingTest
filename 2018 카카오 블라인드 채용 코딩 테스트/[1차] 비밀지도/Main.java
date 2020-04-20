import java.util.ArrayList;

class Main {
	public static void main(String[] args){
		int n = 5;
		int[] arr = {9,20,28,18,11};
		int[] arr2 = {30,1,21,17,28};

		String[] ans = solution(n, arr, arr2);
		for(int i=0; i<ans.length; i++)
			System.out.print(ans[i]);
		System.out.println();
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		int len = arr1.length;
		String[] ans = new String[len];
		//System.out.println(1 << len);
		for(int i=0; i<len; i++){
			int total = arr1[i] | arr2[i];	// 전체 지도중 i번째 행
			int temp = len-1;
			String s = "";	// i번째 지도
			
			while(temp >= 0){
				// temp번째 bit가 벽인지 아닌지 & 연산을 통해 판단
				if( (total & (1 << temp )) == (1 << temp))
					s += "#";
				else
					s += " ";
				temp--;
			}
			// 완성된 i번째 지도 저장
			ans[i] = s;
		}
	   
		return ans;
	}
}
