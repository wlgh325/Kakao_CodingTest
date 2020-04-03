import java.util.*;

class KakaoIntern2019_2 {
	public static void main(String[] args){
		String s = "{{123}}";
		int[] ans = solution(s);

		// debug
		for(int i=0; i<ans.length; i++)
			System.out.println(ans[i]);
	}
	
	public static int[] solution(String s) {
		// split
		String temp = s.substring(1,s.length()-1) + ",";
		String[] temp2 = temp.split("},");
		for(int i=0; i<temp2.length; i++)
			temp2[i] = temp2[i].substring(1, temp2[i].length());
		
		// list 초기화
		StringTokenizer st = null;
		ArrayList<Integer>[] list = new ArrayList[temp2.length];
		for(int i=0; i<list.length; i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(temp2[i], ",");
			while(st.countTokens() > 0) {
				list[i].add(Integer.parseInt(st.nextToken()));				
			}
		}
		
		// 크기 순으로 정렬
		Arrays.sort(list, new Comparator<ArrayList>() {
			@Override
			public int compare(ArrayList a, ArrayList b) {
				if(a.size() < b.size())
					return -1;
				else if(a.size() > b.size())
					return 1;
				return 0;
			}
		});
		
		int t = list[0].get(0);
		ArrayList<Integer> result = new ArrayList<>();
		result.add(t);
		for(int i=1; i<list.length; i++) {
			for(int j=0; j<list[i].size(); j++) {
				if(result.contains(list[i].get(j)))
					continue;
				else {
					result.add(list[i].get(j));
				}
			}
		}
		
		int[] ans = new int[result.size()];
		for(int i=0; i<result.size(); i++)
			ans[i] = result.get(i);
		return ans;
	}
}