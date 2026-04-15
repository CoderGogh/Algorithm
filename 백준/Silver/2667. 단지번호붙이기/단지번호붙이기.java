

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 백준 2667
// https://www.acmicpc.net/problem/2667
public class Main {
    static int N, cnt;
    static int[][] matrix;
    static boolean[][] visit;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        // DFS 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1 && !visit[i][j]) {
                    cnt = 0;
                    dfs(i, j);
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int a : list) {
            System.out.println(a);
        }
    }

    static void dfs(int y, int x) {
        visit[y][x] = true;
        cnt++;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (matrix[ny][nx] == 1 && !visit[ny][nx]) {
                dfs(ny, nx);
            }
        }
    }
}
