import java.util.ArrayDeque;
import java.util.Deque;
class Solution{
        boolean[] visited;
        public int solution(int n, int[][]computers) {
            int answer = 0;
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if(!visited[i]){
                    answer++;
                    bfs(i,n,computers);
                }
            }
            return answer;
        }
        public void bfs(int startNode,int n, int[][]computers){
            Deque<Integer> queue = new ArrayDeque<>();
            visited[startNode] = true;
            queue.offer(startNode);

            while(!queue.isEmpty()){
                int node = queue.poll();
                for (int i = 0; i < n; i++) {
                    if(computers[node][i] == 1 && !visited[i]){
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }
