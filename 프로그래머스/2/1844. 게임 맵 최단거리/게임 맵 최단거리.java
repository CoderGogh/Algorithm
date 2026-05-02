import java.util.*;
class Solution {
        int[] row = {-1, 1, 0, 0}; // 상하좌우
        int[] col = {0, 0, -1, 1}; // 상하좌우
        int[][] dist;
        Deque<int[]> queue;
        public int solution(int[][] maps) {
            int answer = 0;
            dist = new int[maps.length][maps[0].length];
            queue = new ArrayDeque<>();
            queue.add(new int[]{0,0});  // 시작지점
            dist[0][0] = 1; // 시작 거리(방문 체크 = visited 대신)
            bfs(queue,maps);
            
            int finalDist = dist[maps.length-1][maps[0].length-1];
            return finalDist == 0 ? -1:finalDist;
        }
        void bfs(Deque<int[]>queue, int[][]maps){
            int n = maps.length;
            int m = maps[0].length;

            while(!queue.isEmpty()){
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                // 목표지점 도달 확인
                if(r == n-1 && c == m-1){
                    return;
                }

                // 4방 탐색
                for (int i = 0; i < 4; i++) {
                    int nr = r + row[i];
                    int nc = c + col[i];
                    if(nr >= 0 && nc >= 0 && nr < n && nc < m){
                        // 갈 수 있는 길 1 && 방문하지 않은 0
                        if(maps[nr][nc] == 1 && dist[nr][nc] == 0){
                            dist[nr][nc] = dist[r][c] +1;   // 거리 갱신
                            queue.add(new int[]{nr,nc});
                        }
                    }
                    }
                }
            }
        }