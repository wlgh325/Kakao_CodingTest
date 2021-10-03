#include <string>
#include <vector>

using namespace std;

string solution(string new_id) {
	string answer = "";

	// 3자 이상 15자 이하
	// .는 처음과 끝에만 사용 X, 연속 사용 X

	// 1단계: 소문자로 변환
	for (char& ch : new_id) {
		ch = tolower(ch);
	}

	// 2단계: 소문자, 숫자, -, . 제외 모두 제거
	for (int i = 0; i < new_id.size(); i++) {
		char c = new_id[i];

		if (c == '-' || c == '_' || c == '.' || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
			answer.push_back(c);
		}
	}

	// 3단계: 연속된 .. 제거
	string temp;
	for (int i = 0; i < answer.size(); i++) {
		if (answer[i] == '.') {
			temp.push_back('.');
			while (true) {
				i++;
				if (i >= answer.size() || answer[i] != '.') {
					i--;
					break;
				}
			}
		}
		else {
			temp.push_back(answer[i]);
		}
	}

	answer = temp;


	// 4단계: 맨 앞과 뒤의 . 제거
	if (answer.front() == '.') answer.erase(answer.begin());
	if (answer.back() == '.') answer.erase(answer.end() - 1);

	// 5단계: 빈 문자열이면 'a' 대입
	if (answer.empty()) answer.push_back('a');

	// 6단계: 16자 이상-> 첫 15자 제외 모두 제거, 

	if (answer.size() >= 16) {
		answer = answer.substr(0, 15);
		if (answer.back() == '.') {
			answer.erase(answer.end() - 1);
		}
	}

	// 7단계: 2자 이하 -> 길이 3 될때까지 마지막 문자 반복
	if (answer.size() <= 2) {
		char c = answer.back();
		while (answer.size() != 3) {
			answer.push_back(c);
		}
	}

	return answer;
}