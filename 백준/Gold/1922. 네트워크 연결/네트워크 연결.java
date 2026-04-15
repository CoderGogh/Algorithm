import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); 
        int M = Integer.parseInt(br.readLine()); 

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // pq.add(new Edge(0, 0));
        // 0번 노드로 시작했더니 ArrayIndexOutOfBounds 존재라고 오류 뜸
        pq.add(new Edge(1, 0)); 

        int mstCost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            mstCost += cur.cost;
            count++;

            //  종료 조건 잘못 작성, N개 방문해야 함
            // if (count == N - 1) break;
            if (count == N) break; 

            for (Edge next : graph.get(cur.to)) {
                if (!visited[next.to]) pq.add(next);
            }
        }
        System.out.println(mstCost);
    }
}
