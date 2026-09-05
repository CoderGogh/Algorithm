import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0;
        int maxH = 0;
        for (int i = 0; i < sizes.length; i++) {
            int w = sizes[i][0];
            int h = sizes[i][1];
            int longer = Math.max(w, h);
            int shorter = Math.min(w, h);
            maxW = Math.max(maxW, longer);
            maxH = Math.max(maxH, shorter);
        }
        
        return maxW * maxH;
    }
}