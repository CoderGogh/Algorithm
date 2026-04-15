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
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < m; i++) graph.add(new ArrayList<>());

            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph.get(x).add(new Edge(y, z));
                graph.get(y).add(new Edge(x, z));

                // 실수 ①: total = z; 라고 썼더니 전체 합이 계속 덮어써짐
                // total = z;
                total += z; // 정답
            }

            boolean[] visited = new boolean[m];
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            // 실수 ②: 시작점을 pq.add(new Edge(1, 0)); 로 해버려서 0번 노드가 끊김 😅
            // pq.add(new Edge(1, 0));
            pq.add(new Edge(0, 0)); // 올바른 코드

            int mst = 0;
            int count = 0;

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (visited[cur.to]) continue;
                visited[cur.to] = true;
                mst += cur.cost;
                count++;

                // 실수 ③: 종료 조건을 count == m-1 로 썼더니 마지막 노드가 안 들어감 🤦
                // if (count == m - 1) break;
                if (count == m) break; // 올바른 코드

                for (Edge next : graph.get(cur.to)) {
                    if (!visited[next.to]) pq.add(next);
                }
            }

            sb.append(total - mst).append("\n");
        }

        System.out.print(sb.toString());
    }
}
