

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;
    static int[] rowColumns;    // rowColumns[2] = 3 <-- 2번째 row의 3 column에, Queen을 넣는다.
    // 인덱스가 row, 값이 column
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rowColumns = new int[N];
        rowQueen(0);
        System.out.println(ans);
    }

    // 해당 row 에 Queen 을 놓음
    // 마지막 row 까지 재귀호출됨
    static void rowQueen(int row){
        if(row == N){   // 이전 호출에서 N-1 개의 row 에 모두 Queen 을 놓았다.
            ans++;
            return;
        }

        // 현재 row 에서 맨앞 ~ 맨뒤 column(가로)로 Queen을 놓알 스 있는 지 확인 --> 가능하면 놓는다.
        // Queen 을 특정 col 에 놓을 수 없다면 안 놓는다. <-- 백트래킹
        for (int i = 0; i < N; i++) {
            // 좌우 Queen 은 1개만 놓는 것 체크
            rowColumns[row] = i;    // 선택

            if(check(row)){
                rowQueen(row + 1);
            }
        }
    }

    // row 자리에 Queen 을 놓고 호출
    // 상하, 좌우, 대각선 을 체크 --> 좌우는 check 메소드를 호출하는 곳에서 이미 체크
    // 상하, 대각선만 체크해야 함
    static boolean check(int row){
        // 맨 처음 행 ~ 현재 row 이전까지
        for (int i = 0; i < row; i++) {
            if( rowColumns[i] == rowColumns[row]){  // 상하가 같은 것
                return false;
            }else if(row - i == Math.abs(rowColumns[row] - rowColumns[i])){  //대각선이 같은 경우( 변화량 체크)
                return false;
            }
        }
        return true;
    }
}
