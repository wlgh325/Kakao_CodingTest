import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	public static void main(String[] args){
		String[][] relation = {{"100","ryan","music","1"},
		{"200","apeach","math","2"},
		{"300","tube","computer","3"},
		{"400","con","computer","4"},
		{"500","muzi","music","5"},
		{"600","apeach","music","6"}};
		System.out.println(solution(relation));
	}

	static boolean[] visited;
	static int ans, len;
	static HashSet<String> subset;
	static HashSet<String> remove;

    public static int solution(String[][] relation) {
        // 후보키의 개수 구하기
        // column의 길이는 최대 8개
        // nCr 구하기
		len = relation[0].length;

		ans = 0;
		subset = new HashSet<>();
		remove = new HashSet<>();

		visited = new boolean[len];

		// 모든 subset 찾기
        for(int i=1; i<=len; i++){
			comb(0, len, i, relation);
		}
		
		// 유일성 검사
		for(int i=1; i<=len; i++){
			for(String sub: subset){
				// size 즉, 원소의 개수가 작은것 부터 탐색
				if(sub.length() == i){
					if(checkUnique(relation, sub)){
						// 유일성 만족한다면 이를 포함하는 subset들 지우기
						ans++;
						removeSet(sub);
					}
				}
			}

			// 지우기
			subset.removeAll(remove);
			remove.clear();
		}
        return ans;
    }
    
    public static void comb(int start, int n, int r, String[][] relation){
        if(r == 0){
			StringBuffer temp = new StringBuffer();

			for(int i=0; i<n; i++){
				if(visited[i]){
					temp.append(i);
				}
			}

			subset.add(temp.toString());
            return;
        }
        
        for(int i=start; i<n; i++){
            visited[i] = true;
            comb(i+1, n, r-1, relation);
            visited[i] = false;
        }
	}
	
	public static boolean checkUnique(String[][] relation, String sub){
		HashSet<String> set = new HashSet<>();
		
		for(int i=0; i<relation.length; i++){
			StringBuffer sb = new StringBuffer();
			for(int j=0; j<sub.length(); j++){
				sb.append(relation[i][sub.charAt(j) - '0']);
			}
			set.add(sb.toString());
		}

		if(set.size() == relation.length) return true;
		else return false;
	}

	public static void removeSet(String sub){
		for(String set: subset){
			int cnt = 0;
			for(int i=0; i<sub.length(); i++){
				if(set.contains(sub.substring(i, i+1))){
					cnt++;
				}
			}

			if(cnt == sub.length())
				remove.add(set);
		}
	}
}