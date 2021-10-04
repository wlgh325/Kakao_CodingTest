#include <string>
#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

static map<string, int> possible;
static int max_arr[11];
void makeCourse(vector<string>& ans, vector<int> course) {

    for (pair<string, int> p : possible) {
        // 1. �ּ� 2�� �̻� ���� ����
        // 2. ���� ���� �ֹ��� ��ǰ�޴� ����(������ ������ ��� �߰�)
        if (p.second >= 2 && max_arr[p.first.size()] == p.second) {
            ans.push_back(p.first);
        }
    }
}

void combination(int n, string str, int k) {
    vector<int> temp;

    // ������ ���� k ��ŭ 1 �ֱ�
    for (int i = 0; i < k; i++) {
        temp.push_back(1);
    }

    // �������� 0 �ֱ�
    for (int i = 0; i < n - k; i++) {
        temp.push_back(0);
    }

    // sorting
    sort(temp.begin(), temp.end());

    // ����
    do {
        string comb;
        for (int i = 0; i < temp.size(); i++) {
            if (temp[i] == 1) {
                comb.push_back(str[i]);
            }
        }

        // �̹� �ִ� ���
        if (possible[comb]) {
            possible[comb] += 1;

            // �� �������� Hit�� �ִ밪 ����
            max_arr[comb.size()] = max(max_arr[comb.size()], possible[comb]);
        }
        else {
            possible[comb] = 1;
        }
    } while (next_permutation(temp.begin(), temp.end()));
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> ans;

    // ���ϴ� ���� ���� ���ո� ���ϱ�
    for (int num : course) {
        for (string str : orders) {
            // str�� ���� �������� ó�� => WX == XW
            sort(str.begin(), str.end());

            int size = str.size();

            // ��� ���� ���ϱ�
            if (size >= num) combination(size, str, num);
        }
    }

    // �ڽ��丮 �����
    makeCourse(ans, course);

    return ans;
}



int main() {
    vector<string> orders = { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
    vector<int> course = { 2,3,5 };
    vector<string> ans = solution(orders, course);

    for (string str : ans) {
        cout << str << endl;
    }

    return 0;
}