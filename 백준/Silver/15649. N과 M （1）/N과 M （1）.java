
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 55492    1680
public class Main {
    static int N, M;
    static int[] src, tgt;
    static boolean[] select;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        src = new int[N + 1]; // 0 dummy, 1,2..자연수표현
        select = new boolean[N + 1];
        tgt = new int[M];

        // src 자연수로 채운다.
        for (int i = 1; i <= N; i++) {
            src[i] = i;
        }

        perm(0);
    }
    static void perm(int tgtIdx) {
        // 기저 조건
        if( tgtIdx == M ) {
            // 순열 완성, complete code
            // 옆으로 한개씩 공백과 함께 출력
            for (int n : tgt) {
                System.out.print(n + " ");
            }
            System.out.println(); // 개행
            return;
        }

        for (int i = 1; i <= N; i++) {
            if( select[i] ) continue;

            tgt[tgtIdx] = src[i];

            select[i] = true;
            perm(tgtIdx + 1);
            select[i] = false;
        }
    }
}
