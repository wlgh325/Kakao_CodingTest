import java.util.*;

	// 방을 배정한다.
	// 호텔에는 방이 총 k 개 있다.
	// 방 번호는 1~k번
	// 한 번에 한명씩 신청한 대로
	// 투숙하기 원하는 방 제출
	// 원하는 방이 비어있다면 즉시 배정
	// 이미 배정되어 있다면 원하는 방보다 번호가 크면서. 비어있는 방 중 가장 작은 번호의 방
    // 방은 최대 10^12 개
    
class KakaoIntern2019_4 {
    public static void main(String[] args) throws IOException{
		long k = 1000000000001L;
		long[] room_number = {1,1000000000000L,1000000000000L,1,3,1};
        
        long k2 = 10;
        long[] room_number2 = {1,3,1,1,1};
        
        long[] ans = solution(k2, room_number2);
        // debug
		for(int i=0; i<ans.length; i++)
			System.out.println(ans[i]);
    }
    
	public static long[] solution(long k, long[] room_number) {
        long[] ans = new long[room_number.length];
        // 자식, 부모
        HashMap<Long, Long> map = new HashMap<>();
        
        for(int i=0; i<room_number.length; i++) {
        	ArrayList<Long> list = new ArrayList<>();
        	long val = room_number[i];
        	// 빈방이라면
        	if(!map.containsKey(val)) {
        		map.put(val, val+1);
        		ans[i] = val;
        	}
        	else {
        		// 부모 가져오기
        		long parent = map.get(val);
        		list.add(val);
        		while(true) {
        			// 부모의 방이 비어있을때까지 찾기
        			if(!map.containsKey(parent)) {
        				map.put(parent, parent+1);	// 빈방이 있으므로 추가
        				ans[i] = parent;
        				break;
        			}
        			else {
        				list.add(parent);	// 방문한 list 업데이트
        				parent = map.get(parent);
        			}
        		}
        		for(long l : list)
        			map.put(l, parent+1);
        	}
        }
        return ans;
    }
}