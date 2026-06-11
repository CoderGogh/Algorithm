class Solution {
        public int[] solution(int brown, int yellow) {
            int total = brown + yellow;
            int width = 0;
            for(int height = 3; height <= Math.sqrt(total); height++) {
                if(total%height == 0){
                    width = total/height;
                    if((width-2) * (height-2) == yellow){
                        return new int[]{width, height};
                    }
                }
            }
            return new int[]{-1,-1};
        }
    }