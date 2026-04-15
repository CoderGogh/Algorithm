

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 한 칸의 정보를 저장하기 위한 클래스
    static class Node implements Comparable<Node> {
        int row;
        int col;
        int cost;   // 지금까지의 누적 비용

        Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;                   // 동굴의 크기
    static int[][] cave;            // 입력받은 도둑루피 지도
    static int[][] dist;            // 각 칸까지의 최소 손실 비용
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int problemCount = 1; // 출력 시 Problem 번호 붙이는 변수

        while (true) {
            // 동굴의 크기 입력
            N = Integer.parseInt(br.readLine().trim());
            if (N == 0) {
                break;  // 테케 기반
            }

            // 배열 초기화
            cave = new int[N][N];
            dist = new int[N][N];

            // 동굴의 각 칸의 도둑루피 정보 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            dijkstra();

            sb.append("Problem ").append(problemCount).append(": ")
                    .append(dist[N - 1][N - 1]).append("\n");
            problemCount++;
        }

        System.out.print(sb);
    }

    static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0, cave[0][0]));
        dist[0][0] = cave[0][0];

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            int nowX = now.row;
            int nowY = now.col;
            int nowCost = now.cost;

            // 이미 더 짧은 경로로 방문한 적이 있으면 무시 --> 해당값 사용
            if (nowCost > dist[nowX][nowY]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                    continue;
                }
                // next Cost 계산
                int newCost = nowCost + cave[nextX][nextY];

                // 기존의 거리보다 더 적은 비용이면 갱신
                if (newCost < dist[nextX][nextY]) {
                    dist[nextX][nextY] = newCost;
                    pq.offer(new Node(nextX, nextY, newCost));
                }
            }
        }
    }
}
