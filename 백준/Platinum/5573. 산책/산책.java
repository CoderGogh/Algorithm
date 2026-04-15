

import java.io.*;
import java.util.*;
public class Main {

        static int H, W, N;
        static int[][] arr = new int[1002][1002];
        static int[][] dp = new int[1002][1002];

        static void dfs(int y, int x) {
            if (y > H || x > W) {
                System.out.println(y + " " + x);
                return;
            }
            if (arr[y][x] == 0) {
                dfs(y + 1, x);
            } else {
                dfs(y, x + 1);
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][1] = N - 1;

            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    int temp = dp[i][j];
                    if (arr[i][j] == 1) {
                        dp[i][j + 1] += temp / 2;
                        dp[i + 1][j] += temp / 2;
                        if (temp % 2 == 1) dp[i][j + 1]++;
                    } else {
                        dp[i][j + 1] += temp / 2;
                        dp[i + 1][j] += temp / 2;
                        if (temp % 2 == 1) dp[i + 1][j]++;
                    }
                }
            }

            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    if (dp[i][j] % 2 == 1) {
                        arr[i][j] = (arr[i][j] + 1) % 2;
                    }
                }
            }

            dfs(1, 1);
        }
    }
