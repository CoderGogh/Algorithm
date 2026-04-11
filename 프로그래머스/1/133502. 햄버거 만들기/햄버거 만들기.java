import java.util.*;
class Solution {
        public int solution(int[] ingredient) {
            int answer = 0;
            Stack<Integer> stack = new Stack<>();
            for (int raw : ingredient) {
                stack.push(raw);

                // 스택에 재료가 4개 이상 쌓였을 때만 검사
                if (stack.size() >= 4) {
                    int size = stack.size();
                    // 마지막 4개가 1, 2, 3, 1인지 확인
                    if (stack.get(size - 4) == 1 &&
                            stack.get(size - 3) == 2 &&
                            stack.get(size - 2) == 3 &&
                            stack.get(size - 1) == 1) {

                        answer++;
                        stack.pop(); // 1 제거
                        stack.pop(); // 3 제거
                        stack.pop(); // 2 제거
                        stack.pop(); // 1 제거
                    }
                }
            }
            return answer;
        }
    }