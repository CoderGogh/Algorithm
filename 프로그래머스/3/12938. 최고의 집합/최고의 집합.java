class Solution {
        public int[] solution(int n, int s) {
            if(n>s){
                return new int[]{-1};
            }
            int[] answer = new int[n];

            int v = s/n;
            int r = s%n;

            for(int i=0;i<n;i++){
                answer[i] = v;
            }
            for(int i=0;i<r;i++){
                answer[n-1-i]++;
            }
            return answer;
        }
    }