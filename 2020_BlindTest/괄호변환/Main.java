import java.util.ArrayList;

class Main {
	public static void main(String[] args){
        String p1 = "(()())()";
        String p2 = ")(";
        String p3 = "()))((()";

        String p4 = "";

        String answer = solution(p3);
        System.out.println(answer);
    }
    
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
            u += solve(v);
            answer = u;
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

        return answer;
    }

    public static boolean correctString(String v){
        boolean flag = false;
        ArrayList<Character> stack = new ArrayList<>();
        int top = -1;

        char[] str = v.toCharArray();
        

        // 처음부터 거꾸로 되어있는 경우
        if(str[0] == ')'){
            return flag;
        }
        else{
            stack.add(str[0]);
            top++;
        }
        
        
        for (int i = 1; i < str.length; i++) {
            if(stack.get(top) != str[i]){
                stack.remove(top);
                top--;
            }
            else{
                stack.add(str[0]);
                top++;
            }

        }

        if(stack.size() == 0)
            flag = true;

        return flag;
    }

    public static String flip(String s){
        String temp = "";

        if(s.equals("")){
            return "";
        }

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                temp += ")";
            else
                temp += "(";
        }

        return temp;
    }
}
