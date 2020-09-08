# <span style="color:orange; font-size:17pt; font-weight:bold">2017 카카오코드 본선 단체사진 찍기 자바 풀이</span>
# <span style="color: orange">단체사진 찍기</span>
- Level2
- [프로그래머스 단체사진 찍기](https://programmers.co.kr/learn/courses/30/lessons/1835?language=java)
<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 정리</span>
1. 프렌즈들은 모두 사진찍고 싶어하는 대형이 모두 다르다
2. 모두가 원하는 조건을 만족하면서도 다르게 서는 방법의 경우의 수를 알아내야 한다.
3. 조건은 최대 100개
4. 줄 세워야 하는 프렌즈들은 총 8명
<br><br>

#  <span style="color:red; font-size:15pt; font-weight:bold">문제 풀이</span>
순열을 구해서 줄을 모두 세우고 조건을 만족하는지 확인하면서 경우의 수를 구한다.  
총 8명 밖에 없으므로 경우의 수는 많아봤자 약 4000개 밖에 되지 않는다. 그렇기 때문에 브루트 포스로 구하면 된다.  
1. 순열을 구해서 order 배열에 담는다
2. 줄 선 순서대로 string 형태로 변환한다
3. 조건을 parsing 한다.
4. index의 차이를 이용해서 조건의 프렌즈들이 얼마나 떨어져 있는지 파악하여 조건을 만족하는지 판단한다
5. 조건을 만족한다면 경우의 수를 count 한다.
<br>