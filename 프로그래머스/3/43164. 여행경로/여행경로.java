import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class Solution {
        String [] answer;
        boolean[] visited;
        boolean found = false;
        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets,(a,b)->a[1].compareTo(b[1]));
            List<String> path = new LinkedList<>();
            path.add("ICN");
            visited = new boolean[tickets.length];
            dfs("ICN",tickets,path,0);
            return answer;
        }
        void dfs(String start, String[][]tickets, List<String>path,int count){
            if(found){
                return;
            }
            if(count == tickets.length){
                answer = path.toArray(new String[0]);
                found = true;
                return;
            }
            for (int i = 0; i < tickets.length; i++) {
                if(!visited[i] && tickets[i][0].equals(start)){
                    visited[i] = true;
                    path.add(tickets[i][1]);
                    dfs(tickets[i][1],tickets,path,count+1);
                    path.remove(path.size()-1);
                    visited[i] = false; // 백트래킹
                }
            }
        }
    }