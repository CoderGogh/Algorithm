import java.util.*;
class Solution {
        int [] parent;
        public int solution(int n, int[][] costs) {
            int answer = 0;
            int bridges = 0;
                
            Arrays.sort(costs, (a,b) -> a[2] - b[2]);   // 오름차순
            
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
            for (int[] edge : costs) {
                int a =  edge[0];
                int b = edge[1];
                int cost = edge[2];
                
                if(find(a) != find(b)){
                    union(a,b);
                    bridges++;
                    answer += cost;
                    if(bridges == n-1){
                        break;
                    }   
                }
            }
            return answer;
        }
        int find(int x){
            if(parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        void union(int i, int j){
            int x = find(i);
            int y = find(j);
            if(x != y){
                parent[x] = parent[y];
            }
        }
    }