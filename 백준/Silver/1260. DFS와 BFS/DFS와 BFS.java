

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1260
// https://www.acmicpc.net/problem/1260
public class Main {
    static int N, M, V;
    static boolean[] check;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1]; // 0은 더미
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 서로 왔다갔다 가능
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i = 1; i <= N; i++) {
            Collections.sort(graph[i]); // i 에 연결되는 노드들을 작은 순으로 정렬시키기
        }

        check = new boolean[N+1];   // dfs 를 위한 초기화
        dfs(V);
        System.out.println();

        check = new boolean[N+1];   // bfs를 위한 초기화
        bfs(V);
    }
    static void dfs(int v){
        check[v] = true;
        System.out.print(v + " ");
        for (int next_node: graph[v]
             ) {
            if(!check[next_node]){
                dfs(next_node);
            }
        }
    }
    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        check[start] = true;
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current+" ");
            for (int next_node: graph[current]
                 ) {
                if(!check[next_node]){
                    check[next_node] = true;
                    queue.add(next_node);
                }
            }
        }
    }
}
