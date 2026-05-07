class Solution {
        public int solution(int[] stones, int k) {
            int min = 1;
            int max = 200000000;
            int answer = 0;

            while (min <= max){
                int mid = (min + max) / 2;
                
                if(check(stones,k,mid)){    // 건널 수 있음
                    answer = mid;
                    min = mid +1;
                }else{  // 건널 수 없음
                    max = mid -1;
                }
            }

            return answer;
        }
        boolean check(int[] stones, int k, int mid){
            int zero = 0;
            for (int stone : stones) {
                if(stone < mid){
                    zero ++;
                    if(zero >= k){
                        return false;
                    }
                }else{
                    zero = 0;
                }
            }
            return true;
        }

    }