import java.util.Arrays;

class Main {
	public static void main(String[] args){
		String dartResult = "1S2D*3T";
		String dartResult2 = "1D2S#10S";
		String dartResult3 = "1D2S0T";
		String dartResult4 = "1S*2T*3S";

		System.out.println(solution(dartResult));
		System.out.println(solution(dartResult2));
		System.out.println(solution(dartResult3));
		System.out.println(solution(dartResult4));
	}

	public static int solution(String dartResult) {
		char[] dart = dartResult.toCharArray();
		int len = dart.length;
		double[] ans = new double[3];

		int idx = 0;	// 몇 번째 다트인가?
		for(int i=0; i<len; i++){
			// 점수인 경우
			if(isNumber(dart[i])){
				// 뒤에 더 문자가 있나?
				// 있다면 숫자인가? => 그러면 무조건 10
				if(i != (len-1) && isNumber(dart[i+1])){
					ans[idx] = 10;			
					i++;
				}
				else
					ans[idx] = dart[i] - '0';
			}
			else{
				switch(dart[i]){
					case 'S':
						if(i != (len-1)){
							if(dart[i+1] != '*' && dart[i+1] != '#')
								idx++;
						}
						break;
					case 'D':
						ans[idx] = Math.pow(ans[idx], 2);
						// 뒤의 문자가 더 있다면, 있는데 옵션이 아니라면 다음 다트로 넘어감
						if(i != (len-1)){
							if(dart[i+1] != '*' && dart[i+1] != '#')
								idx++;
						}
						break;
					case 'T':
						ans[idx] = Math.pow(ans[idx], 3);

						if(i != (len-1)){
							if(dart[i+1] != '*' && dart[i+1] != '#')
								idx++;
						}
						break;
					case '*':
						// 첫 번째 다트가 아니라면 이전 점수도 2배
						if(idx != 0)
							ans[idx-1] = ans[idx-1]*2;
						ans[idx] = ans[idx]*2;
						idx++;
						break;
					case '#':
						ans[idx] = ans[idx]*-1;
						idx++;
						break;
				}
			}
		}

		return (int)Arrays.stream(ans).sum();
	}

	public static boolean isNumber(char c){
		if(Character.isDigit(c)) return true;
		return false;
	}
}
