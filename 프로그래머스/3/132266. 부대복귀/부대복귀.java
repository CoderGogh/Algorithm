import java.util.*;
class Solution {
        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            List<List<Integer>> list = new ArrayList<>();
            for(int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            for (int[]road :roads ) {
                int a = road[0];
                int b = road[1];
                list.get(a).add(b);
                list.get(b).add(a);
            }
            int[] dist = new int[n+1];
            Arrays.fill(dist, -1);
            
            Deque<Integer> queue = new ArrayDeque<>();
            
            queue.add(destination);
            dist[destination] = 0;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                
                for (int neighbor : list.get(curr)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[curr] + 1;    
                        queue.add(neighbor);
                    }
                }
            }
            int answer[] = new int[sources.length];
            for(int i = 0; i < sources.length; i++) {
                answer[i] = dist[sources[i]];
            }
            return answer;
        }
    }