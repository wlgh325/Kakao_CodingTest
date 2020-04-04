# <span style="color:orange; font-size:17pt; font-weight:bold">2019 카카오 겨울 인턴 코테 4번 호텔방 배정 자바(java)  풀이</span>
- LEVEL 4
- [2019 카카오 겨울 인턴 코테  4번 호텔방 배정](https://programmers.co.kr/learn/courses/30/lessons/64063)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 방을 배정한다.
2. 호텔에는 방이 총 k 개 있다.
3. 방 번호는 1~k번
4. 한 번에 한명씩 신청한 대로
5. 투숙하기 원하는 방 제출
6. 원하는 방이 비어있다면 즉시 배정
7. 이미 배정되어 있다면 원하는 방보다 번호가 크면서. 비어있는 방 중 가장 작은 번호의 방
8. 방은 최대 10^12 개
9. room_number 배열의 최대 크기는 200,000
10. 모든 고객이 방을 배정받을 수 있는 경우만 입력으로 주어진다.
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
참고로 모의시험때 정확성만 통과하였고 효율성은 통과하지 못하였다가 풀이 설명을 보고 다시 문제를 풀었습니다.  
처음에는 naive하게 먼저 접근하였습니다. 아래가 그 코드 입니다. 
ArrayList에 k번 방 까지 모두 넣어놓고 -1이 아니면 방이 있고 아니면 빈 방임을 체크하여 검사하였습니다.  
이는 효율성 2번 이후는 모두 시간초과가 나옵니다.
```
import java.util.*;

class Solution {
    static ArrayList<Long> arrList;
    
    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        arrList = new ArrayList<>();
        // k개의 방 생성
        for (long i = 0; i <= k; i++) {
            arrList.add(i);
        }

        for (int i = 0; i < room_number.length; i++) {
            long temp = room_number[i];
            // 방이 있는지 확인
            if(arrList.get((int)temp) != -1){
                // 있다면 답에 쓰고 -1로 바꿔서 없음으로 체크
                answer[i] = arrList.get((int)temp);
                arrList.set((int)temp, (long)-1);
            }
            else{
                // 방이 없다면 그 뒤의 방 중에 -1이 아닌 즉, 빈 방을 찾기
                for (long j = temp; j < arrList.size(); j++) {
                    if(arrList.get((int)j) != -1){
                        answer[i] = arrList.get((int)j);
                        arrList.set((int)j, (long)-1);
                        break;
                    }
                }
            }
        }

        System.out.println("");
        return answer;
    }
}
```

# <span style="color: red; font-size:15pt">HashMap을 이용한 문제 풀이</span>
HashMap을 이용해서 map에 자식과 부모를 저장합니다.  
key는 자식 value는 부모입니다.  
1. 고객에게 배정할 방이 빈방이면 즉시 배정합니다. 그리고 부모 노드는 1을 더한 값으로 만듭니다.
2. 배정할 방이 없다면 빈 방이 나올때까지 부모노드를 계속해서 찾아갑니다.
3. 빈 방이 나오기 전까지 방문한 모든 노드들또한 고객에게 배정한 방 번호+1로 합니다.
위 사항을 map을 이용하여 표현할 수 있습니다.

1. 요구한 방이 빈 방이라면 즉, map에 그 key가 없다면 map에 key값으로 넣고 그 값에 1을 더 한 값으로 value로 넣어서 부모로 지정해줍니다. 그리고 답에 저장합니다.
2. 만약 비어있지 않다면 부모를 가져옵니다. 즉 그 방번호의 value값을 가져옵니다. 그리고 방문한 모든 노드들의 부모를 바꿔주기 위해서 list에 넣습니다.
    이는 부모 탐색을 통해 빈방을 더 빨리 찾기 위함입니다.
3. while문을 통해 부모의 방이 비어있을때 까지 계속 찾습니다.
    빈 방인지 탐색하는 것은 1번에서처럼 key값이 있는지 찾아봅니다.
    비어있지 않은 경우에는 그 방번호를 list에 넣습니다.
4. 부모를 탐색하다가 빈 방을 찾았다면 list에 있는 값들을 꺼내서 그 값들의 부모를 고객에게 배정한 방 번호+1로 모두 바꿔줍니다.