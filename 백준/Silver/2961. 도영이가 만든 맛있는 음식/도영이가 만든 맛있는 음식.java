
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Main {
    static int N, min;
    static boolean[] select;
    static int[][] src; // 재료의 신맛과 쓴맛을 계산
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        select = new boolean[N];
        src  = new int[N][2];   // 0: 신맛 , 1: 쓴맛

        min  = Integer.MAX_VALUE;   // 가장 큰 값으로 세팅해놓고, 점점 작게 만들어감 = 초기화

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            src[i][0] = Integer.parseInt(st.nextToken());   // 신맛
            src[i][1] = Integer.parseInt(st.nextToken());   // 쓴맛
        }
        subset(0);  // 첫번재 요소부터 선택/비선택을 반복함 = 모든 부분집합을 구함

        System.out.println(min);
    }
    static void subset(int src_id){
        // 종료조건
        if(src_id == src.length){   // 배열의 모든 자리까지 도달 시
            int sin_flavor = 1;
            int ssn_flavor = 0;
            int count = 0;
            for (int i = 0; i < N; i++) {
                if(select[i]){
                    sin_flavor *= src[i][0];
                    ssn_flavor += src[i][1];
                    count ++;
                }
            }
            //
            if(count>0){
                min = Math.min(Math.abs(ssn_flavor-sin_flavor),min);
            }
            return;
        }
        // 선택
        select[src_id] =true;
        subset(src_id+1);
        // 비선택
        select[src_id] = false;
        subset(src_id+1);
    }
}
