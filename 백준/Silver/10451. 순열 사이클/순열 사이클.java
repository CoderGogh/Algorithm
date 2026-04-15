

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10451번: 순열 사이클
// https://www.acmicpc.net/problem/10451
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] p = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }

            boolean[] visited = new boolean[N + 1];
            int cycles = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    cycles++;
                    int cur = i;
                    while (!visited[cur]) {
                        visited[cur] = true;
                        cur = p[cur];
                    }
                }
            }
            sb.append(cycles).append('\n');
        }

        System.out.print(sb.toString());
    }
}
