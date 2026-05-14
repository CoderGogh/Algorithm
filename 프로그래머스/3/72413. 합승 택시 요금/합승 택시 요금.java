import java.util.*;
class Solution {
        static final int INF = 1000000000;
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = INF;
            // 플로이드 워셜 알고리즘 사용
            int dist[][] = new int[n+1][n+1];
            // 초기화
            for (int i = 1; i <= n; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0; // 자기 자신
            }
            // 값 할당
            for (int[] fare : fares) {
                int from = fare[0];
                int to = fare[1];
                int cost = fare[2];

                dist[from][to] = cost;
                dist[to][from] = cost;
            }

            // 경유지 k를 바꿔가며 i(출발지) 와 j(도착지) 사이의 거리를 게산
            for(int k = 1; k <= n; k++) {
                for(int i = 1; i <= n; i++) {
                    for(int j = 1; j <= n; j++) {
                        if (dist[i][k] == INF || dist[k][j] == INF) {
                continue;
            }
                        dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                    }
                }
            }
            for(int share = 1; share <= n; share++) {
                    if (dist[s][share] == INF ||
        dist[share][a] == INF ||
        dist[share][b] == INF) {
        continue;
    }
                answer = Math.min(answer, dist[s][share] + dist[share][a] + dist[share][b]);
            }
            return answer;


            /*
            * 플로이드 워셜 알고리즘
            * 1. 목적 : 모든 정점 쌍
            * 2. 방식 : 경유지 갱신
            * 3. 음수간선 가능
            * 4. 자료구조 : 2차원 배열
            * 5. 큰 그래프 : 약함
            * 핵심 아이디어
            * : K를 거처가는 게 빠른가?
            * 기본 코드
            *
import java.util.*;

class Main {

    static final int INF = 1000000000;

    public static void main(String[] args) {

        int n = 4;

        int[][] dist = new int[n + 1][n + 1];

        // 초기화
        for (int i = 1; i <= n; i++) {

            Arrays.fill(dist[i], INF);

            dist[i][i] = 0;
        }

        // 간선 정보 입력
        dist[1][2] = 4;
        dist[1][4] = 6;
        dist[2][1] = 3;
        dist[2][3] = 7;
        dist[3][1] = 5;
        dist[3][4] = 4;
        dist[4][3] = 2;

        // 플로이드 워셜
        for (int k = 1; k <= n; k++) {

            for (int i = 1; i <= n; i++) {

                for (int j = 1; j <= n; j++) {

                    dist[i][j] =
                        Math.min(dist[i][j],
                                 dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
            *  */



            /*
            * 다익스트라 알고리즘
            * 1. 목적 :  한 정점 기준
            * 2. 방식 : 가가운 정점 확정
            * 3. 음수간선 불가능
            * 4. 자료구조 : 인접리스트 + 우선순위 큐
            * 5. 큰 그래프 : 강함
            * 6. 기본 코드
            * dist 배열 : 현재까지 발견한 최단거리
            * 우선순위 큐 : 가장 가까운 정점부터 탐색
            * 흐름요약
            * 1. 가장 가까운 정점꺼냄
            * 2. 연결 노드 탐색
            * 3. 거리 갱신
            * 4. 다시 큐 삽입
            * 5. 반복
            *
import java.util.*;

class Main {

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static List<Node>[] graph;
    static int[] dist;

    public static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            int now = current.to;
            int cost = current.cost;

            // 이미 더 짧은 경로가 있으면 스킵
            if (dist[now] < cost) {
                continue;
            }

            for (Node next : graph[now]) {

                int nextCost = cost + next.cost;

                // 더 짧은 거리 발견
                if (nextCost < dist[next.to]) {

                    dist[next.to] = nextCost;

                    pq.offer(new Node(next.to, nextCost));
                }
            }
        }
    }
}
            *
            * */

        }
    }
