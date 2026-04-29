import java.util.ArrayDeque;
import java.util.Deque;
class Solution {
        static int[] prev;
        static int[] next;
        static int pointer;
        static Deque<int[]> stack;
        static boolean[] check;
        public String solution(int n, int k, String[] cmd) {
            /*
            제한 조건
                 1. Node 클래스 사용 vs 배열 인덱스 사용?
                     -> 테스트 데이터 볼륨이 1,000,000개 --> 참조값 변수인 클래스로 선언 x
                 2. LinkedList vs 직접 구현
                     -> 삭제 & 삽입(복구)은 쉽지만, 인덱스를 찾아가는 속도가 느림(데이터 볼륨도 생각)
                     -> 직접 구현(배열 사용)
                3. cmd 명령어에서 빈칸을 기준으로 방향과 이동할 값을 추출할 때, split 함수 or indexOf 함수가 효율성에서 좋을까?
                    -> 데이터 볼륨(100만건) 오버헤드 무조건 나올 듯
                    -> charAt() 사용

             사용할 자료구조
                 1. Stack ( 명령어 저장 -> 사실상 제거한 앞뒤 노드를 다시 연결하는 기능)
                 2. int[] prev & int[] next 배열
                 3. StringBuilder 사용 -> 문자열 연산 속도 증가
             */

// ----------------------------------------------------------------------------
            // 0. 초기화
            prev = new int[n];
            next = new int[n];
            pointer = k;
            check = new boolean[n];
// ----------------------------------------------------------------------------
            // 1. 포인터 연결
            for (int i = 0; i < n; i++) {
                if(i == 0){
                    // 첫 노드일 때 : 이전 노드 -1
                    prev[i] = -1;
                    next[i] = i+1;
                }else if(i == n-1){
                    // 마지막 노드일 때 : 다음 노드 -1
                    prev[i] = i-1;
                    next[i] = -1;
                }else{
                    // 양옆에 노드가 있는 경우
                    prev[i] = i -1;
                    next[i] = i+1;
                }
            }
// ----------------------------------------------------------------------------
            // 2. cmd 명령어 순차적으로 실행
            stack = new ArrayDeque<>();
            for (int i = 0; i < cmd.length; i++) {
                char operation = cmd[i].charAt(0);
                switch(operation){
                    // 표를 벗어나는 이동은 문제에서 주어지지 않음
                    case 'U':   // 위로 이동(제거된 노드는 무시해야 함 = prev 배열 사용)
                        moveUp(Integer.parseInt(cmd[i].substring(2)));
                        break;
                    case 'D':   // 아래로 이동(제거된 노드는 무시해야 함 = next 배열 사용)
                        moveDown(Integer.parseInt(cmd[i].substring(2)));
                        break;
                    case 'C':   // 해당 행 삭제
                        delete();
                        break;
                    case 'Z':   // 가장 최근 삭제한 노드 되돌리기
                        restore();
                        break;
                    default:
                        break;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < check.length; i++) {
                if(check[i]){   // true = 삭제됨
                    sb.append('X');
                }else{
                    sb.append('O');
                }
            }

            return sb.toString();
        }
        public static void restore(){    // Z 명령어(복구)는 사실상 insert
            if(!stack.isEmpty()){
                // 삭제 표시
                // 복구할 노드 꺼내기
                int[] node = stack.pop();
                int targetPointer = node[0];
                int previousNode = node[1];
                int nextNode = node[2];

                check[targetPointer] = false;    // 존재함 = false

                // 재연결
                if(nextNode!= -1){
                    prev[nextNode] = targetPointer;
                }
                if(previousNode!= -1){
                    next[previousNode] = targetPointer;
                }

            }
        }
        public static void delete(){    // C 명령어(제거)
            stack.push(new int[]{pointer,prev[pointer],next[pointer]});
            
            check[pointer] = true;  // 삭제됨 = true
            
            // 포인터 업데이트
            if(prev[pointer]!=-1){
                next[prev[pointer]] = next[pointer];
            }
            if(next[pointer]!=-1){
                prev[next[pointer]] = prev[pointer];
            }

            // 포인터 이동 -> 마지막 행일 경우 위로, 아닐 경우 아래로 포인터 이동
            if(next[pointer] != -1){
                pointer = next[pointer];
            }else{
                pointer = prev[pointer];
            }

        }
        public static void moveUp(int x){ // "U X" & "D X" 이동 명령어
            while (x-->0){
                pointer = prev[pointer];
            }
        }
        public static void moveDown(int x){ // "U X" & "D X" 이동 명령어
            while (x-->0){
                pointer = next[pointer];
            }
        }
    }