class Solution {
        public String solution(int[] numbers, String hand) {
            String answer = "";
            StringBuilder sb = new StringBuilder();
            // 숫자 0~9까지의 좌표를 미리 매핑 (인덱스가 곧 숫자)
            int[][] numPos = {
                    {3, 1}, // 0의 좌표
                    {0, 0}, // 1의 좌표
                    {0, 1}, // 2의 좌표
                    {0, 2}, // 3의 좌표
                    {1, 0}, // 4...
                    {1, 1}, // 5
                    {1, 2}, // 6
                    {2, 0}, // 7
                    {2, 1}, // 8
                    {2, 2}  // 9
            };
            int[] leftFinger = {3,0};
            int[] rightFinger = {3,2};
            for (int num :
                    numbers) {
                if(num == 1 || num == 4 || num == 7){
                    sb.append("L");
                    leftFinger = numPos[num];
                }else if(num == 3 || num == 6 || num == 9){
                    sb.append("R");
                    rightFinger = numPos[num];
                }else{
                    int leftDist = getDist(leftFinger, numPos[num]);
                    int rightDist = getDist(rightFinger, numPos[num]);
                    
                    if(leftDist<rightDist){
                        sb.append("L");
                        leftFinger = numPos[num];
                    }else if(rightDist<leftDist){
                        sb.append("R");
                        rightFinger = numPos[num];
                    }
                    else{
                        if(hand.equals("right")){
                            sb.append("R");
                            rightFinger = numPos[num];
                        }else{
                            sb.append("L");
                            leftFinger = numPos[num];
                        }
                    }
                }

            }
            return sb.toString();
        }private int getDist(int[]pos, int[]target){
            return Math.abs(pos[0] - target[0]) + Math.abs(pos[1]-target[1]);
        }
    }