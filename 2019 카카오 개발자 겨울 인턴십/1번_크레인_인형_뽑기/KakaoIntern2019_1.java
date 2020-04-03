import java.util.*;

class KakaoIntern2019_1 {
    public static void main(String[] args){
		int[][] board = {{}};
		int[] moves = {};
		System.out.println(solution(board, moves));
	}
	
	public static int solution(int[][] board, int[] moves) {
		int ans = 0;
		int len = board.length;
		Stack<Integer>[] stack = new Stack[len];
		Stack<Integer> basket = new Stack<>();
		
		for(int i=0; i<board[0].length; i++) {
			stack[i] = new Stack<>();
			for(int j=board.length-1; j>=0; j--) {
				if(board[j][i] != 0)
					stack[i].push(board[j][i]);
			}
		}
		
		for(int i=0; i<moves.length; i++) {
			if(!stack[moves[i]-1].isEmpty()) {
				int t = stack[moves[i]-1].pop();
				if(!basket.isEmpty()) {
					if(basket.peek() == t) {
						basket.pop();
						ans += 2;
					}
					else
						basket.push(t);
				}
				else
					basket.push(t);
			}
		}
		return ans;
	}
}