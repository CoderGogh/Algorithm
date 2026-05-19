class Solution {
        public int solution(int[][] triangle) {
/*
i -> i or i+1 until height = triangle.length
*/
            int height = triangle.length;
            // 두 번째 줄(인덱스 1)부터 시작해서 맨 아래 줄까지 내려갑니다.
            for (int i = 1; i < height; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        triangle[i][j] += triangle[i - 1][0];
                    } else if (j == i) {
                        triangle[i][j] += triangle[i - 1][j - 1];
                    } else {
                        triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                    }
                }
            }

            int answer = 0;
            for (int j = 0; j < height; j++) {
                answer = Math.max(answer, triangle[height - 1][j]);
            }

            return answer;
        }
    }