import java.util.ArrayList;

class Main {
        static ArrayList<String> arrList = new ArrayList<>();

	public static void main(String[] args){
                String p1 = "aabbaccc";
                //String p2 = "ababcdcdababcdcd";
                //String p3 = "abcabcdede";
                //String p4 = "abcabcabcabcdededededede";
                //String p5 = "xababcdcdababcdcd";
                //String p6 = "a";

                int min = solve(p1);
                System.out.println(min);
	}
	public static int solution(String s) {
        	if(s.length() == 1){
            	return 1;
        	}        
	        return solve(s);
	}
	
	public static int solve(String s){
		String answer = "";
		int min = 9999999;

		// i: 몇개 씩 묶을지
		// 문자 길이의 절반까지만 비교하면 된다! 절반이 넘으면 길이가 달라서 겹치는게 없으니
		for (int i = 1; i < s.length()/2 + 1; i++) {
			int len = 1;
			// 비교할 이전 문자
			String before = s.substring(0, i);

			// 묶은 길이로 나눈 만큼만 검색
			// 길이가 8인데 2씩 나누면 (1,2) (2,3) (3,4) 3번만 비교하면 된다
			int temp = s.length() /i;

			int j=i;
			// 문자열 뒤로 이동하면서 비교
			for(; j<i*temp;){
				String next = s.substring(j, j+i);

				// 같으면 길이 증가
				if(before.equals(next))
				    len++;
				else{
						// 같은게 안나왔는데 이전까지 같은 문자가 있었을 경우
					if(len != 1){
					    answer += Integer.toString(len);
					    answer += before;
					}
					else
					    answer += before;	// 같은게 없었으면 그냥 문자 붙임
					len = 1;
				}

				// 뒤의 문자가 이전문자가 된다
				before = next;
				j= j+i;
			}

			// 맨 뒤 문자 처리
			if(len != 1){
			    answer += Integer.toString(len);
			    answer += s.substring(j-i, s.length());
			}
			else
			    answer += s.substring(j-i, s.length());

			// 압축된 문자의 길이가 최소값인지 판단
			int cnt = answer.length();
			if(min >= cnt)
			    min = cnt;

			answer = "";
		}

		return min;
    	}
}
