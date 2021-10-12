#include <string>
#include <vector>
#include <iostream>

using namespace std;

// vector�� �̿������� stack�� �̿��ؼ��� Ǯ�� ����
int solution(vector<vector<int>> board, vector<int> moves) {
    int ans = 0;
    vector<int> box;

    // ������ ���� ���� ��� �ƹ��ϵ� �Ͼ�� ����
    // ũ������ ��� �۵���Ų �� ��Ʈ���� ����� ������ ����
    int len = board[0].size();

    for (int move : moves) {

        // ���� ���� ���� ã��
        for (int i = 0; i < len; i++) {
            if (board[i][move - 1] != 0) {
                // ã�� ��� ���� ���� �̾� �ڽ��� �ֱ�
                box.push_back(board[i][move - 1]);
                board[i][move - 1] = 0;

                // ��ġ�� ���� �ִ��� check
                int box_size = box.size();

                // ��ġ�� ������ ���� �� ����
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