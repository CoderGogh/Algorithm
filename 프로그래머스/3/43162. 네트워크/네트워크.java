class Solution {
    int count = 0;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i<n;i++){
            if(!visited[i]){
                dfs(i,computers, visited);
                count++;
            }
        }
        return count;
    }
    void dfs(int curr, int[][]computers, boolean[] visited){
        visited[curr] = true;
        
        for(int i = 0;i<computers.length; i++){
            if(computers[curr][i] == 1 && !visited[i]){
                dfs(i,computers,visited);
            }
        }
    }
}