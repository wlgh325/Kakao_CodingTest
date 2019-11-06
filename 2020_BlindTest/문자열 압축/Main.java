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

        public static int solve(String s){
                String answer = "";
                
                // 문자열 길이가 1일 경우 예외처리
                if(s.length() == 1){
                        return 1;
                }

                // i: 몇개 씩 묶을지
                for (int i = 1; i < s.length()/2 + 1; i++) {
                        int len = 1;
                        String before = s.substring(0, i);
                        int temp = s.length() /i;

                        int j=i;
                        for(; j<i*temp;){
                                String next = s.substring(j, j+i);
                                
                                if(before.equals(next)){
                                    len++;    
                                }
                                else{
                                        if(len != 1){
                                                answer += Integer.toString(len);
                                                answer += before;
                                        }
                                        else{
                                                answer += before;
                                        }
                                        len = 1;
                                }
                                //System.out.println("before: " + before + " next: " + next);
                                before = next;
                                j= j+i;
                        }

                        if(len != 1){
                                answer += Integer.toString(len);
                                answer += s.substring(j-i, s.length());
                        }
                        else{
                                answer += s.substring(j-i, s.length());
                        }
                        //System.out.println("answer: " + answer);
                        arrList.add(answer);
                        answer = "";

                }

                int min = 999999;
                for (String str : arrList) {
                        int len = str.length();
                        if(min >= len)
                                min = len;
                }

                return min;
        }
}
