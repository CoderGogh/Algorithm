

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, cost = 0;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N][3];
        map = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = map[0][i];
        }
        for(int i = 1; i < N; i++) {
            dp[i][0] = map[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
            dp[i][1] =  map[i][1] +Math.min(dp[i-1][0],dp[i-1][2]);
            dp[i][2] =  map[i][2] +Math.min(dp[i-1][1],dp[i-1][0]);
        }
        int answer = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
        System.out.println(answer);

    }
}
