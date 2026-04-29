class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            // 1. 배열의 인덱스로 풀기
            for (int i = 0; i < prices.length; i++) {
                int x = prices[i];
                int count = 0;
                for(int j = i+1; j < prices.length; j++) {
                    count++;
                    if(prices[j] < x){
                        break;
                    }
                }
                answer[i] = count;
            }
            return answer;
        }
    }