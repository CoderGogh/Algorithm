import java.util.*;

    class Solution {
        Deque<Integer > queue;  // 노드 번호만 담음
    List<List<Integer>> list;   // 전체적인 맵
    int[] visited;  // 거리 누적
        public int solution(int n, int[][] edge) {
            visited = new int[n+1];
            for(int i=0;i<=n;i++){
                visited[i]= -1; // 나머지 노드 방문하지 않음 = -1
            }
            visited[1] = 0; // 첫 노드(출발지) 거리 = 0

            list = new ArrayList<>();
            for(int i=0;i<=n;i++){  // n+1개 생성 // 각 노드별 빈 리스트
                list.add(new ArrayList<>());
            }
            for (int[] e :edge ) {
                list.get(e[0]).add(e[1]);
                list.get(e[1]).add(e[0]);
            }

            queue = new ArrayDeque<>();
            queue.offer(1);

            int maxDistance = 0;

            while(!queue.isEmpty()){
                int curr = queue.poll();

                // bfs
                for(int i:list.get(curr)){
                    if(visited[i]==-1){
                        visited[i] = visited[curr]+1;
                        maxDistance = Math.max(maxDistance,visited[i]);
                        queue.offer(i);
                    }
                }
            }
            int answer = 0;
            for(int i=1;i<=n;i++){
                if(visited[i]== maxDistance){
                    answer++;
                }
            }
            return answer;
        }
    }
