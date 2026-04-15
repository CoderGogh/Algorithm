import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static int maxCount; // 전역변수로 최댓값 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 배치 가능한 칸을 색상별로 분리
        List<int[]> blacks = new ArrayList<>();
        List<int[]> whites = new ArrayList<>();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (board[y][x] == 1) {
                    if (((y + x) & 1) == 0) blacks.add(new int[]{y, x});
                    else whites.add(new int[]{y, x});
                }
            }
        }

        // 대각선 점유 상태(방문 불가 마킹)
        boolean[] d1 = new boolean[2 * n - 1]; // y + x
        boolean[] d2 = new boolean[2 * n - 1]; // y - x + (n - 1)

        // 검은 칸 최댓값 구하기
        maxCount = 0;
        dfs(blacks, 0, d1, d2, 0);
        int blackMax = maxCount;

        // 흰 칸 최댓값 구하기
        maxCount = 0;
        dfs(whites, 0, d1, d2, 0);
        int whiteMax = maxCount;

        System.out.println(blackMax + whiteMax);
    }

    // cells: 같은 색상의 후보 칸 목록
    // idx: 현재 검사 중인 후보 인덱스
    // d1, d2: 대각선 점유(=해당 대각선의 다른 칸들은 방문 불가로 마킹)
    // count: 현재까지 놓은 비숍 수
    static void dfs(List<int[]> cells, int idx, boolean[] d1, boolean[] d2, int count) {
        if (idx == cells.size()) {
            maxCount = Math.max(maxCount, count);
            return;
        }

        int[] p = cells.get(idx);
        int y = p[0], x = p[1];
        int a = y + x;              // 대각선 점유 규칙
        int b = y - x + (n - 1);    // 대각선 점유 규칙 2

        // 1) 현재 칸에 놓을 수 있으면 대각선 점유 마킹 후 진행
        if (!d1[a] && !d2[b]) {
            d1[a] = d2[b] = true;
            dfs(cells, idx + 1, d1, d2, count + 1);
            d1[a] = d2[b] = false; // 원복(마킹 해제)
        }

        // 2) 현재 칸을 건너뛰기
        dfs(cells, idx + 1, d1, d2, count);
    }
}