

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static long sum;
    static List<List<Vertex>> adjList = new ArrayList<>();
    static boolean[] visit;     // 인접 행렬 matrix 가 아닌, 인접 행렬을 필요로.
    static PriorityQueue<Vertex> queue  = new PriorityQueue<>( (v1, v2)-> v1.c - v2.c);  // comparable 대신 비교 기준 제시
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 인접 리스트 구성
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<Vertex>());
        }

        visit = new boolean[V+1];   // 0 : dummy
        
        // 인접 리스트 채우기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            // 방향이 없는 간선 // v1 <--> v2 각각의 cost
            adjList.get(v1).add(new Vertex(v2,cost));
            adjList.get(v2).add(new Vertex(v1,cost));
        }
        
        queue.offer(new Vertex(1,0));
        int count = 0;  // V개 선택
        while(!queue.isEmpty()){
            Vertex vertex = queue.poll();
            if(visit[vertex.v]){
                continue;
            }
            visit[vertex.v] = true;
            sum += vertex.c;
            count++;
            if(count == V){
                break;
            }
            for (Vertex v: adjList.get(vertex.v)    // 꺼낸 정점으로부터 갈 수 있는 다른 모든 정점들
                 ) {
                if(visit[v.v]){
                    continue;
                }
                queue.offer(new Vertex(v.v,v.c));
            }
        }
        System.out.println(sum);
        
    }


    static class Vertex{
        int v,c;    // 누구로부터는 관심 X

        public Vertex(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }
}
