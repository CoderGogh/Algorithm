import java.util.*;
    class Solution {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        public int solution(int[][] board, int[] moves) {
            // 2차원 배열 :
            // 행 : 세로(위에서부터 카운트) // 층수
            // 열 : 가로

            // moves 배열 : 방문한 열

            // 사라진 인형 숫자 : pop된 횟수

            for (int i = 0; i < moves.length; i++) {
                int current = moves[i] -1;
                for (int j = 0; j < board.length; j++) {
                    if(board[j][current] == 0){
                        continue;
                    }
                    doOperation(board[j][current]);
                    board[j][current] = 0;
                    break;
                }
            }
            return answer;
        }
        private void doOperation(int input){
            if(stack.isEmpty()){
                stack.push(input);
                return;
            }
            if(stack.peek() == input){
                stack.pop();
                answer+=2;
            }else{
                stack.push(input);
            }
        }
    }
