import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KakaoIntern2019_3_2 {
	static HashSet<Integer> set;
	public static void main(String[] args){
		String[] user_id = {"frodo", "crodo", "abcd123"};
		String[] banned_id = {"*rodo"};
		System.out.println(solution(user_id, banned_id));
	}
	
	public static int solution(String[] user_id, String[] banned_id) {
		set = new HashSet<>();
		dfs(banned_id, user_id, 0, 0, 0);
		return set.size();
	}
	
	
	public static void dfs(String[] banned_id, String[] user_id, int idx, int n, int visit) {
		// 금지된 아이디 목록을 모두 검사했다면
		if(n == banned_id.length) {
			set.add(visit);
			return;
		}
		
		// 재귀를 통해 pattern에 맞는 문자들을 선택해 나간다.
		for(int i=0; i<user_id.length; i++) {
			String s = banned_id[idx].replace("*", ".");
			Pattern pattern = Pattern.compile(s);
			Matcher matcher = pattern.matcher(user_id[i]);
			
			// 정규 표힌식에 매칭 되고, 길이가 같은 경우
			if(matcher.find() && user_id[i].length() == banned_id[idx].length()) {
				// 제제 아이디로 체크하지 않은 경우 check
				if(((visit>>i) & 1 ) != 1) {
					dfs(banned_id, user_id, idx+1, n+1, (visit | 1 << i));	// 다음 banned_id와 매칭되는 제재 아이디 찾기
				}
			}
		}
	}
}