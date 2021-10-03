#include <string>
#include <vector>

using namespace std;

string solution(string new_id) {
	string answer = "";

	// 3�� �̻� 15�� ����
	// .�� ó���� ������ ��� X, ���� ��� X

	// 1�ܰ�: �ҹ��ڷ� ��ȯ
	for (char& ch : new_id) {
		ch = tolower(ch);
	}

	// 2�ܰ�: �ҹ���, ����, -, . ���� ��� ����
	for (int i = 0; i < new_id.size(); i++) {
		char c = new_id[i];

		if (c == '-' || c == '_' || c == '.' || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
			answer.push_back(c);
		}
	}

	// 3�ܰ�: ���ӵ� .. ����
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


	// 4�ܰ�: �� �հ� ���� . ����
	if (answer.front() == '.') answer.erase(answer.begin());
	if (answer.back() == '.') answer.erase(answer.end() - 1);

	// 5�ܰ�: �� ���ڿ��̸� 'a' ����
	if (answer.empty()) answer.push_back('a');

	// 6�ܰ�: 16�� �̻�-> ù 15�� ���� ��� ����, 

	if (answer.size() >= 16) {
		answer = answer.substr(0, 15);
		if (answer.back() == '.') {
			answer.erase(answer.end() - 1);
		}
	}

	// 7�ܰ�: 2�� ���� -> ���� 3 �ɶ����� ������ ���� �ݺ�
	if (answer.size() <= 2) {
		char c = answer.back();
		while (answer.size() != 3) {
			answer.push_back(c);
		}
	}

	return answer;
}