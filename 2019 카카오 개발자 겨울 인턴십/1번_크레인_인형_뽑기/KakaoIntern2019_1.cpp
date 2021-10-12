#include <string>
#include <vector>
#include <iostream>

using namespace std;

// vector를 이용했지만 stack을 이용해서도 풀이 가능
int solution(vector<vector<int>> board, vector<int> moves) {
    int ans = 0;
    vector<int> box;

    // 인형이 없는 곳일 경우 아무일도 일어나지 않음
    // 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수
    int len = board[0].size();

    for (int move : moves) {

        // 제일 위의 인형 찾기
        for (int i = 0; i < len; i++) {
            if (board[i][move - 1] != 0) {
                // 찾은 경우 맨위 인형 뽑아 박스에 넣기
                box.push_back(board[i][move - 1]);
                board[i][move - 1] = 0;

                // 겹치는 인형 있는지 check
                int box_size = box.size();

                // 겹치는 인형이 있을 수 있음
                if (box_size >= 2) {
                    if (box[box_size - 1] == box[box_size - 2]) {
                        box.erase(box.end() - 1);
                        box.erase(box.end() - 1);
                        ans += 2;
                    }
                }

                break;
            }
        }
    }
    return ans;
}

int main() {
    vector<vector<int>> board = { {0, 0, 0, 0, 0},{0, 0, 1, 0, 3},{0, 2, 5, 0, 1},{4, 2, 4, 4, 2},{3, 5, 1, 3, 1 } };
    vector<int> moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
    int ans = solution(board, moves);
    cout << ans << endl;
    return 0;
}