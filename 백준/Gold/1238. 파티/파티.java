

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1238번
public class Main {
    static class Node implements Comparable<Node> {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, X;
    static List<List<Node>> graph;      // 원본 그래프 (정방향)
    static List<List<Node>> reverse;    // 역방향 그래프

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverse = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // 정방향
            graph.get(start).add(new Node(end, time));
            // 역방향 (출발과 도착을 뒤집기')
            reverse.get(end).add(new Node(start, time));
        }

        // X에서 각 마을로 가는 최단거리 (정방향)
        int[] distFromX = dijkstra(X, graph);

        // 각 마을에서 X로 오는 최단거리 (역방향)
        int[] distToX = dijkstra(X, reverse);

        int answer = 0;

        // 각 마을의 왕복 거리 계산
        for (int i = 1; i <= N; i++) {
            int roundTrip = distToX[i] + distFromX[i];
            answer = Math.max(answer, roundTrip);
        }

        System.out.println(answer);
    }

    // 다익스트라 알고리즘
    private static int[] dijkstra(int start, List<List<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int current = now.end;
            int currentCost = now.cost;

            if (currentCost > dist[current]) continue;

            for (Node next : graph.get(current)) {
                int nextCost = dist[current] + next.cost;

                if (nextCost < dist[next.end]) {
                    dist[next.end] = nextCost;
                    pq.offer(new Node(next.end, nextCost));
                }
            }
        }

        return dist;
    }
}

