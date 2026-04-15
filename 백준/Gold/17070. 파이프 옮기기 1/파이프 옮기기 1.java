

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] matrix;

    // 오른쪽, 아래, 오른쪽 아래 대각선
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 0, 1};

    // 1: go right      2:  go down          3: go right_down
    static int[] dir = {1,2,3};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1,2,0);
        System.out.println(ans);
    }
    static void dfs(int row, int col, int dir){
        if(row == N && col == N){
            ans++;
            return;
        }
        for(int d = 0; d < 3; d++) {
            if(dir == 0 && d == 1){
                continue;
            }else if(dir == 1 && d == 0){
                continue;
            }
            int next_row = row + dr[d];
            int next_col = col + dc[d];
            if(check(next_row,next_col)){
                continue;
            }
            if(d == 2){
                if(next_row > N || next_col > N || matrix[row][col+1]==1 || matrix[row+1][col]==1 || matrix[row+1][col+1]==1){
                    continue;
                }
            }
            dfs(next_row,next_col,d);
        }
    }
    static boolean check(int next_row, int next_col){
        if(next_row <1 || next_col <1 || next_row>N || next_col > N || matrix[next_row][next_col]==1){
            return true;
        }
        return false;
    }

}
