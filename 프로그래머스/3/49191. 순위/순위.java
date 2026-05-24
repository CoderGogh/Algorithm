class Solution {
        public int solution(int n, int[][] results) {
            boolean[][] check = new boolean[n+1][n+1];

            //직접결과
            for (int[] result : results) {
                check[result[0]][ result[1]] = true;
            }
            // 간접결과
            for(int k = 1; k <= n; k++) {
                for(int i = 1; i <= n; i++) {
                    for(int j = 1; j <= n; j++) {
                        if(check[i][k] && check[k][j]) {
                            check[i][j] = true;
                        }
                    }
                }
            }
            int answer = 0;
            for(int i = 1; i <= n; i++) {
                // 한 노드에서 갈 수 있는 지 체크
                int count = 0;
                for(int j = 1; j <= n; j++) {
                    if(check[i][j] || check[j][i]) {
                        count++;
                    }
                }
                if(count == n-1){
                    answer++;
                }
            }
            return answer;
        }
    }