import java.util.*;
class Solution {
        public int solution(String dirs) {
            // 첫 시작 (0,0) -> 움직일 때마다 점이 아닌 선분을 중복제거해야함
            // (0,0) -> (0,1) : 0001 선분 = 1000 선분(양방향) -> SET으로 관리
            // 배열 벗어나는 것들은 마지막에 SET에서 제거
            HashSet<String> set = new HashSet<>();
            int x = 0;
            int y = 0;
            for (int i = 0; i < dirs.length(); i++) {
                char op = dirs.charAt(i);

                int nextX = x;
                int nextY = y;

                if (op == 'U') nextY++;
                else if (op == 'D') nextY--;
                else if (op == 'R') nextX++;
                else  nextX--;

                if (nextX < -5 || nextX > 5 || nextY < -5 || nextY > 5) {
                    continue;
                }

                String path1 = "" + x + y + nextX + nextY;
                String path2 = "" + nextX + nextY + x + y;

                set.add(path1);
                set.add(path2);
                x = nextX;
                y = nextY;
            }
            return set.size()/2;
        }
    }