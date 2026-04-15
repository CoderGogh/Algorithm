
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[][] memoi;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            memoi = new int[m+1][m+1];  // 매 테스트마다 초기화 필요함
            System.out.println(comb(m, n));
        }
    }
    static int comb(int m, int n){
        if(n == 0 || m == n){
            return 1;
        }
        // 계산값(memoi) 재사용
        if(memoi[m][n]!=0){
            return memoi[m][n];
        }
        return memoi[m][n]= comb(m-1,n-1) + comb(m-1,n);    // 계산값 저장
    }
}