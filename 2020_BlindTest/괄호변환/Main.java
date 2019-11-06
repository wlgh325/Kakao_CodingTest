import java.util.ArrayList;


class Main {
	public static void main(String[] args){
        String p1 = "(()())()";
        String p2 = ")(";
        String p3 = "()))((()";

        String p4 = "";

        String answer = solution(p2);
        System.out.println(answer);
    }
    
    // (와 )의 개수가 같으면 균형잡힌 괄호 문자열
    // 여기에 짝도 모두 맞을 경우 올바른 괄호 문자열

    // 올바른 괄호 문자열로 변환
    public static String solution(String p){
        String answer = "";

        answer = solve(p);
        return answer;
    }

    public static String solve(String p){
        String answer = "";
        int rparen = 0;
        int lparen = 0;

        String u = "";
        String v = "";

        // 1번 규칙
        if(p.equals(""))
            return answer;

        // 2번. 균형잡힌 문자열 u, v로 변환
        for (int i = 0; i < p.length(); i++) {
            
            if(p.substring(i, i+1).equals("("))
                lparen++;
            else
                rparen++;

            if(lparen == rparen){
                u = p.substring(0, i+1);
                v = p.substring(i+1, p.length());
                break;
            }
        }

        // 3번. u가 올바른 괄호 문자열인지 검사
        if(correctString(u)){
            solve(v);
        }
        else{
            // 4-1
            String temp = "(";

            // 4-2
            temp += solve(v);

            // 4-3
            temp += ")";

            // 4-4
            String sliceStr = u.substring(1, u.length()-1);
            temp += flip(sliceStr);

            answer = temp;
        }


        System.out.println("u: " + u);
        System.out.println("v: " + v);

        return answer;
    }

    public static boolean correctString(String v){
        boolean flag = false;

        
        return flag;
    }

    public static String flip(String s){
        char[] str = s.toCharArray();
        
        if(s.equals("")){
            return "";
        }

        for (int i = 0; i < str.length; i++) {
            if(str[i] == '(')
                str[i] = ')';
            else
                str[i] = '(';
        }

        return str.toString();
    }
}
