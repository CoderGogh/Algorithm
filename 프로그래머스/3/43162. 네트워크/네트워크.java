import java.util.ArrayDeque;
import java.util.Deque;
class Solution {
        boolean[] visited;   // dfs
        Deque<Integer> queue;
        public int solution(int n, int[][] computers) {
            int answer = 0;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                if(!visited[i]){
                    answer++;
                }
                // DFS
                dfs(i,n, computers);
                // BFS
//                bfs(i,n, computers);
            }

            return answer;
        }
        public void dfs(int node, int n, int[][] computers){
            visited[node] = true;
            for (int i = 0; i < n; i++) {
                if(computers[node][i] == 1 && !visited[i]){
                    dfs(i,n,computers);
                }
            }
        }
        public void bfs(int startNode, int n, int[][] computers){
            Deque<Integer>queue = new ArrayDeque<>();
            queue.offer(startNode);
            visited[startNode] = true;

            while(!queue.isEmpty()){
                int curr =  queue.poll();
                for (int j = 0; j < n; j++) {
                    if(computers[curr][j] == 1 & !visited[j]){
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
    }