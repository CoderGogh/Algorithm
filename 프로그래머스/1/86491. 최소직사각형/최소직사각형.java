class Solution {
        public int solution(int[][] sizes) {
            int maxRow = 0;
            int maxCol = 0;
            for (int[] size : sizes) {
                int currentMax = Math.max(size[0], size[1]);
                int currentMin = Math.min(size[0], size[1]);
                maxRow = Math.max(maxRow, currentMax);
                maxCol = Math.max(maxCol, currentMin);
            }
            return maxRow * maxCol;
        }
    }