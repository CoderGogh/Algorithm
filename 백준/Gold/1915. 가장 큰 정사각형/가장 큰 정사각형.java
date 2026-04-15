import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[1001][1001];
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sqrtAns = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int y = 1; y <= n; y++) {
            String line = br.readLine();
            for (int x = 1; x <= m; x++) {
                arr[y][x] = line.charAt(x-1) - '0';
            }
        }
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (arr[y][x] != 0) {
                    arr[y][x] = Math.min(arr[y-1][x], arr[y][x-1]);
                    arr[y][x] = Math.min(arr[y][x], arr[y-1][x-1]) + 1;
                    sqrtAns = Math.max(arr[y][x], sqrtAns);
                }
            }
        }

        System.out.println(sqrtAns * sqrtAns);

        br.close();
    }
}