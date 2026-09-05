import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        // [3, 0, 6, 1, 5]
        // 0 0 0 0 1
        // 0 1 1 1 2
        for(int i=0;i<n;i++){
            int h = n - i;// 잔여갯수
            if(citations[i] >=h){   // 해당 값 > 잔여갯수
                return h;
            }
        }
            
        return 0;
    }
}