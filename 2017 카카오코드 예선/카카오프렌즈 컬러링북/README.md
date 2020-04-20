# <span style="color:orange; font-size:17pt; font-weight:bold">2017 카카오코드 예선 카카오프렌즈 컬러링 북 자바 풀이</span>
# <span style="color: orange">카카오프렌즈 컬러링북</span>
[프로그래머스 카카오프렌즈 컬러링북](https://programmers.co.kr/learn/courses/30/lessons/1829)
- 풀이시간 : 약 20분
<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 정리</span>
1. 영역: 상하좌우로 연결된 같은 색상의 공간
2. 영역의 개수와 가장 큰 영역의 넓이를 구하여라
<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 풀이</span>
같은 숫자를 가지는 부분을 퍼져서 영역을 구하는 것이므로 bfs를 하면 됩니다.
1. 방문하지 않았고, 0이 아닌 경우 bfs 탐색을 합니다.
2. bfs는 다음과 같이 탐색합니다.
    2.1 4가지 방향으로 모두 탐색합니다.
    2.2 유효한 위치이고 방문하지 않았고 시작점과 같은 색깔인 경우에 방문하고 queue에 넣습니다. 그리고 영역의 넓이를 증가시킵니다.
3. bfs탐색을 통해 구한 넓이가 최대 넓이 보다 큰지 비교하여 update 합니다.
4. 오른쪽 아래 끝점까지 모두 탐색합니다.
5. 영역의 개수는 bfs를 구하러 들어간 횟수가 영역의 넓이가 됩니다.
<br>