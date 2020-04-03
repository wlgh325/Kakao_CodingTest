import java.io.IOException;
import java.util.ArrayList;

class KakaoIntern2019_5_2 {
    public static void main(String[] args) throws IOException{
		int[] stones = {2,4,5,3,2,1,4,2,5,1};
		int k = 3;
		System.out.println(solution(stones, k));	// 3 출력
    }
    
    static long[] tree, lazy;
    static ArrayList<Integer> leaf;
	public static int solution(int[] stones, int k) {
		int ans = 0;
		int size = stones.length;
		tree = new long[(int)(1 << (int)Math.ceil( Math.log10(size) / Math.log10(2) ) + 1)];
		lazy = new long[(int)(1 << (int)Math.ceil( Math.log10(size) / Math.log10(2) ) + 1)];
		init(stones, 1, 0, size-1);
		
		// 1씩 빼본다
		boolean flag = true;
		while(true) {
			int left = 0;
			int right = 0;
			for(int i=0; i<size; i++) {
				if(sum(1, 0, size-1, i, i) == 0) {
					right -= 1;
					update_range(1, 0, size-1, left, right, -1);
					left = i+1;
					right = i;
				}
				right++;
			}
			if(left != right)
				update_range(1, 0, size-1, left, right, -1);
			
			ans++;
			// 길이 k만큼 합을 구해봄
			for(int i=0; i<=size-k; i++) {
				long result = sum(1, 0, size-1, i, i+k-1);
				if(result == 0) {
					flag = false;
					break;
				}
			}
			if(!flag)
				break;
		}
		return ans;
	}
	
	private static long init(int[] arr, int node, int start, int end) {
		// leaf 노드라면 값을 쓴다.
		if (start == end) {
			return tree[node] = arr[start];
		}
		else {
			// leaft가 아니라면 왼쪽 node와 오른쪽 node의 합을 구한다.
			int mid = (start + end) / 2;
			return tree[node] = init(arr, node*2, start, mid) + init(arr, node*2+1, mid+1, end);
		}
	}

	private static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end-start+1)*lazy[node];
			// leaf가 아니면
			if (start != end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			lazy[node] = 0;	// update했으니까 lazy값 없애줌
		}
	}

	private static void update_range(int node, int start, int end, int left, int right, long diff) {
		update_lazy(node, start ,end);
		if(left > end || right < start)
			return;
		// 변경해야 하는 구간에 모두 포함되는 경우
		// ex) 3~7(left~right)을 변경하려는데 start~end가 3~4인 경우 변경하려는 구간에 포함
		if(left <= start && end <= right){
			// 구간에 모두 포함되기 때문에 구간의 크기*diff 만큼 더해줍니다.
			// 3,4 모두 diff 를 더하기 때문에, 3~4의 더한 값을 저장하고 있는 노드는 (4-3+1)*diff 만큼 더해야 합니다.
			tree[node] += (end-start+1)*diff;
			// 더 이상 update를 수행하지 않고 나중에 다시 업데이트를 수행하러 그 노드를 방문했을 때 업데이트 한다.
			// 그러기 위해서 leaf노드가 아니라면 자식에게 lazy 값을 물려준다.
			if(start != end){
				lazy[node*2] += diff;
				lazy[node*2 + 1] += diff;
			}
			return;
		}
		int mid = ((start + end) / 2);
		update_range(node*2, start, mid, left, right, diff);
		update_range(node*2+1, mid+1, end, left, right, diff);
		tree[node] = tree[node*2] + tree[node*2+1];	// 리프 노드가 아닌 경우에는 두 자식의 합 계산
	}

	private static long sum(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);

		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
	}
}