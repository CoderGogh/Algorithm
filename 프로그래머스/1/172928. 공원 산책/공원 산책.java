import java.util.*;
class Solution {
        private static class RobotDog{
            int x;
            int y;

            public RobotDog(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        public int[] solution(String[] park, String[] routes) {
            int[] answer = {};
            Map<String, int[]> move = Map.of(
                "N", new int[]{-1, 0},
                "W", new int[]{0, -1},
                "E", new int[]{0, 1},
                "S", new int[]{1, 0}
            );
            RobotDog dog = null;
            for (int i = 0; i < park.length; i++) { // 시작 위치 찾기
                if(park[i].contains("S")){
                    dog = new RobotDog(park[i].indexOf("S"),i);
                    break;
                }
            }
            for (int i = 0; i < routes.length; i++) {   // 거리 계산 & 실제로 움직임
                int blank = routes[i].indexOf(" ");
                String direction = routes[i].substring(0,blank);
                int distance = Integer.parseInt(routes[i].substring(blank+1));

                int[] delta = move.get(direction);
                int curY = dog.y;
                int curX = dog.x;
                boolean isOk = true;

                for (int j = 0; j < distance; j++) {
                    int nextY = curY + delta[0];
                    int nextX = curX + delta[1];

                    if(nextY<0 || nextY>= park.length || nextX<0 || nextX>=park[0].length()){
                        isOk = false;
                        break;
                    }
                    if(park[nextY].charAt(nextX) == 'X'){
                        isOk = false;
                        break;
                    }
                    curY = nextY;
                    curX = nextX;
                }
                if(isOk){
                    dog.y = curY;
                    dog.x = curX;

                }
            }
            answer = new int[]{dog.y, dog.x};
            return answer;
        }
    }