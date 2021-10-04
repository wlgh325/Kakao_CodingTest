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
        // 1. 최소 2번 이상 나온 구성
        // 2. 가장 많이 주문된 단품메뉴 조합(개수가 같으면 모두 추가)
        if (p.second >= 2 && max_arr[p.first.size()] == p.second) {
            ans.push_back(p.first);
        }
    }
}

void combination(int n, string str, int k) {
    vector<int> temp;

    // 선택할 개수 k 만큼 1 넣기
    for (int i = 0; i < k; i++) {
        temp.push_back(1);
    }

    // 나머지는 0 넣기
    for (int i = 0; i < n - k; i++) {
        temp.push_back(0);
    }

    // sorting
    sort(temp.begin(), temp.end());

    // 조합
    do {
        string comb;
        for (int i = 0; i < temp.size(); i++) {
            if (temp[i] == 1) {
                comb.push_back(str[i]);
            }
        }

        // 이미 있는 경우
        if (possible[comb]) {
            possible[comb] += 1;

            // 각 갯수마다 Hit된 최대값 저장
            max_arr[comb.size()] = max(max_arr[comb.size()], possible[comb]);
        }
        else {
            possible[comb] = 1;
        }
    } while (next_permutation(temp.begin(), temp.end()));
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> ans;

    // 원하는 구성 개수 조합만 구하기
    for (int num : course) {
        for (string str : orders) {
            // str내 문자 오름차순 처리 => WX == XW
            sort(str.begin(), str.end());

            int size = str.size();

            // 모든 조합 구하기
            if (size >= num) combination(size, str, num);
        }
    }

    // 코스요리 만들기
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