# <span style="color:orange; font-size:17pt; font-weight:bold">2020 카카오 블라인드 공채 코딩 테스트 (2020 Kakao Blind Recruitment) 기출문제</span>
# <span style="color: orange">2번 괄호변환</span>

<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 정리</span>
1. 모든 괄호를 뽑아서 올바른 순서대로 배치된 괄호 문자열을 알려주는 프로그램을 개발하려고 한다.
2. 균형잡힌 괄호 문자열: '('와 ')' 로만 이루어진 문자열이 있을 경우, '('의 개수와 ')'의 개수가 같은 문자열
3. 올바른 괄호 문자열: 균형잡힌 문자열 + 짝이 모두 맞음
4. 균형잡힌 괄호 문자열 => 올바른 괄호 문자열로 변환
변환하는 자세한 과정은 문제 참고 : [2020 카카오 코테 기출 2번 괄호변환](https://programmers.co.kr/learn/courses/30/lessons/60057)
5. 균형잡힌 괄호 문자열 p가 주어질때 주어진 알고리즘을 수행해 올바른 괄호 문자열로 변환하여 return 하여라.
<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 풀이</span>
이 문제는 문제에 주어진 그대로 코딩하면 됩니다.  
알고리즘 부분에서 재귀적인 부분을 잘 캐치해낼 수 있다면 그리 어렵지 않게 구현할 수 있습니다.  
재귀 함수를 많이 작성해보지 않았다면 어려울 수도 있을 것 같습니다.  
알고리즘 3번과 4-2번을 보고 1-3까지 재귀적으로 호출해야겠다고 생각을 하고 그대로 작성해 내려가면 됩니다.  
자세한 사항은 코드에 주석으로 달았습니다.
<br>