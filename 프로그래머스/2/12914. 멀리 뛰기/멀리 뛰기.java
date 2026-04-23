class Solution {
        public long solution(int n) {
            if(n == 1) return 1;
            long[] memoi = new long[n+1];
            memoi[1] = 1;
            memoi[2] = 2;
            for(int i = 3; i <= n; i++) {
                memoi[i] = (memoi[i-1] + memoi[i-2]) % 1234567;
            }
            return memoi[n];
        }
    }