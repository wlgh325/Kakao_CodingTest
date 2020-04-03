import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KakaoIntern2019_3 {
	static int ans;
	static ArrayList<ArrayList> list;

	public static void main(String[] args){
		String[] user_id = {"frodo", "crodo", "abcd123"};
		String[] banned_id = {"*rodo"};
		System.out.println(solution(user_id, banned_id));
	}
	
	public static int solution(String[] user_id, String[] banned_id) {
		boolean[] visited = new boolean[user_id.length];
		list = new ArrayList<>();		
		dfs(banned_id, user_id, 0, 0, visited);
		return ans;
	}
	
	
	public static void dfs(String[] banned_id, String[] user_id, int idx, int n, boolean[] visited) {
		// 금지된 아이디 목록을 모두 검사했다면
		if(n == banned_id.length) {
			// user_id 중 뽑은 금지된 아이디를 뽑아서 temp에 넣기
			ArrayList<String> temp = new ArrayList<>();
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					temp.add(user_id[i]);
				}
			}
			
			// 금지된 아이디 리스트가 저장된 리스트들에 대해서 같은 리스트가 있는지 탐색한다.
			// 여러 리스트중 하나를 뽑아서 그 리스트 안에 있는 원소들과 방금 뽑은 원소들을 담은 temp 리스트의 원소와 비교
			// 포함하지 않는게 하나라도 있다면 flag가 true가 되고 cnt를 증가시킨다.
			// 그렇게 해서 cnt가 list의 크기와 같아 졌다면 지금까지 만들어진 가능한 리스트와 겹치는 리스트가 없다는 뜻
			// 그러므로 가능한 리스트 수(ans)를 증가시키고 가능한 리스트 목록에 방금 만든 temp리스트를 추가한다.
			int cnt = 0;
			for(int i=0; i< list.size(); i++) {
				ArrayList<String> t = list.get(i);
				boolean flag = false;
				for(int j=0; j<temp.size(); j++) {
					if(!temp.contains(t.get(j)))
						flag = true;
				}
				if(flag)
					cnt++;
			}
			if(cnt == list.size()) {
				list.add(temp);
				ans++;
			}
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
				if(!visited[i]) {
					visited[i] = true;				
					dfs(banned_id, user_id, idx+1, n+1, visited);	// 다음 banned_id와 매칭되는 제재 아이디 찾기
					visited[i] = false;					
				}
			}
		}
	}
}