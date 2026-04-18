class Solution {
        int max = 0;
        public int solution(int k, int[][] dungeons) {
            boolean[]visited = new boolean[dungeons.length];
            // k : 현재 체력
            // dungeons : 방문하기 위해 필요한 최소 체력, 방문했을 때 깎이는 체력(-)
            // dungeons 의 갯수 최대 : 8개 -> 완전 탐색해도 가능
            // backtracking  & dfs 사용(실제로 구현x  & for while문 사용)
            return dfs(k,0,dungeons,visited);
        }
        public int dfs(int current_health, int count, int[][] map, boolean[] visited){
            // current_health : 현재 남은 체력
            // count : 현재까지 방문한 던전 수
            // map : 던전의 정보(최소체력, 깎이는 체력)
            // visited : 방문한 던전을 관리하는 배열

            // 밑의 과정이 끝나면 최댓값으로 갱신
            max = Math.max(count,max);

            // 모든 던전 순회
            for (int i = 0; i < map.length; i++) {
                if(current_health >= map[i][0] && !visited[i]){
                    visited[i] = true;  // 방문
                    dfs(current_health - map[i][1], count+1, map,visited);
                    visited[i] = false;
                }
            }
            return max;
        }
    }