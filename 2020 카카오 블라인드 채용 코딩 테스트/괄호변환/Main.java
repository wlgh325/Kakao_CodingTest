import java.util.Stack;

class Main {
	public static void main(String[] args){
        String p1 = "(()())()";
        String p2 = ")(";
        String p3 = "()))((()";
        
        // debug
        System.out.println(solution(p1));
        System.out.println(solution(p2));
        System.out.println(solution(p3));
	}
	
    public static String solution(String p) {
        return solve(p);
    }
    
    private static String solve(String p) {
    	// 1. 입력이 빈 문자열일 경우, 빈 문자열 반환
        if(p.equals(""))
        	return "";
        
        // 2. 문자열 w를 두 균형잡힌 문자열로 분리(u,v)
        // u는 균형잡힌 문자열로 더 이상 분해 불가, v는 빈 문자열일 수 있음
        int len = p.length();
        int idx = 0;
        int left = 0;
        int right = 0;
        while(true) {
        	if(p.charAt(idx) == '(')
        		left++;
        	else
        		right++;
        	idx++;
        	if(left == right)
        		break;
        }
        
        String u = p.substring(0, idx);
        String v = p.substring(idx, len);
        
        // 3. 문자열 u가 올바른 괄호 문자열이라면 v에 대해 1단계 부터 실행
        if(check(u))
        	return u += solve(v);        
        else {
        	// 4. 그렇지 않다면
        	// 4.1 빈 문자열에 대해 첫 번째 문자로 '(' 붙이기
        	String temp = "(";
        	
        	// 4.2 문자열 v에 대해 1단계 부터 재귀적으로 수행한 결과 문자열을 이어 붙인다
        	temp += solve(v);
        	
        	// 4.3 ')'를 붙입니다.
        	temp += ")";
        	
        	// 4.4 u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        	u = u.substring(1, u.length()-1);
        	for(int i=0, ulen=u.length(); i<ulen; i++)
        		temp += u.charAt(i) == '(' ? ")" : "(";
        	
        	// 4.5 생성된 문자열을 반환
        	return temp;
        }
    }
    
    private static boolean check(String s) {
    	boolean flag = true;
    	char[] arr = s.toCharArray();
    	Stack<Character> stack = new Stack<>();
    	// 처음부터 거꾸로 되어 있는 경우
    	if(arr[0] == ')')
    		return false;
    	else
    		stack.add(arr[0]);
    	
    	for(int i=1; i<arr.length; i++){
    		char c = arr[i];
    		switch(c) {
	    		case '(':
	    			stack.push(c);
	    			break;
	    		case ')':
	    			if(stack.isEmpty())
	    				flag = false;
	    			else
	    				stack.pop();
	    			break;
    		}
    		if(!flag)
    			return flag;
    	}
    	
    	if(!stack.isEmpty())
    		return false;
    	return flag;
    }
}
