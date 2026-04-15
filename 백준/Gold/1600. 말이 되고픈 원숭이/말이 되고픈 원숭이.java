

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 가중치가 없는 최단경로 --> bfs
// visit  --> 2차원 배열이고, 사방탐색일 경우 visit[row][column]; 로 해결
// 그런데 이 문제는, 말처럼 k번 이동하는 상황 = 동일한 좌표에서 발생 <--- row,col 좌표에서 사방+8방 둘다 가능
// 그래서 같은 좌표라 하더라도 k 값이 어떤 값이냐에 따라 이후의 상황이 달라진다. visit[row][col][k]
public class Main {
    static int K,W,H;
    static int[][] map;
    static boolean [][][] visit;
    static int[] del_row = {-1,1,0,0};
    static int[] del_col = {0,0,-1,1};
    static int[] h_row = {-2,-2,-1,-1,2,2,1,1};
    static int[] h_col = {-1,1,-2,2,-1,1,-2,2};
    static class Node{
        int row, col, dist, k;

        public Node(int row, int col, int dist, int k) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.k = k;
        }
    }
    static Queue<Node> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K  = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visit = new boolean[H][W][K+1]; // 0개 없다 & K개 있다. K+1

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }
    static void bfs(){
        // 시작 좌표
        visit[0][0][K] = true;
        queue.offer(new Node(0,0,0,K));

        while(!queue.isEmpty()){
            Node node = queue.poll();

            // 목표 도달했는 지 체크
            if(node.row == H-1 && node.col == W-1){
                System.out.println(node.dist);
                return;
            }
            // 탐색 1. 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nr = node.row + del_row[i];
                int nc = node.col + del_col[i];

                if(nr <0 || nc<0|| nr>=H || nc>=W || map[nr][nc] == 1 || visit[nr][nc][node.k]){
                    continue;
                }
                visit[nr][nc][node.k] = true;
                queue.offer(new Node(nr,nc,node.dist+1,node.k));
            }

            // 탐색 2. 팔방 탐색

            if(node.k == 0){
                continue;
            }
            // k를 한 번 쓰겠다는 의미
            for (int i = 0; i < 8; i++) {
                int nr = node.row + h_row[i];
                int nc = node.col + h_col[i];

                if(nr <0 || nc<0|| nr>=H || nc>=W || map[nr][nc] == 1 || visit[nr][nc][node.k-1]){
                    continue;
                }
                visit[nr][nc][node.k-1] = true;
                queue.offer(new Node(nr,nc,node.dist+1,node.k-1));
            }
        }
        System.out.println(-1);
    }
}


















