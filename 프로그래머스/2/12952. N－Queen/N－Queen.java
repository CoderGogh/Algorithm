class Solution {
        static int answer = 0;
        public int solution(int n) {
            int[] map = new int[n];
            backtrack(0,n,map);
            return answer;
        }
        public static void backtrack(int row, int n, int[] map){
            if(row == n){
                answer++;
                return;
            }   // 맨 밑까지 도착했을 때 정답 증가
            for(int col = 0; col < n; col++) {  // 옆으로 한 칸 씩 이동하며 체크
                if(possible(row,col,map)){
                    map[row] = col;
                    backtrack(row+1,n,map); // 그 다음 줄로 가서 또 다시 탐색 시작
                }
            }
        }
        public static boolean possible(int row, int col, int[] map){
            for (int i = 0; i < row; i++) {
                if(map[i] == col){
                    return false;
                }
                if(Math.abs((row-i)) == Math.abs((col - map[i]))){
                    return false;
                }
            }
            return true;
        }
    }