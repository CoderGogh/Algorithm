import java.util.*;
class Solution {
        // 클래스로 선언 vs 배열 인덱스로 사용
        int[] parent;
        public int solution(int n, int[][] costs) {
            int answer = 0;
            int brideges = 0;
            
            Arrays.sort(costs, (a, b) -> a[2] - b[2]);
            
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // 자기자신
            }
            
            for (int[] edge : costs) {
                int a = edge[0];
                int b = edge[1];
                int cost = edge[2];
                
                if(find(a) != find(b)){
                    union(a,b);
                    answer += cost;
                    brideges++;
                    
                    if(brideges == n-1){
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
        void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY){
                parent[rootX] = rootY;
            }
        }
    }