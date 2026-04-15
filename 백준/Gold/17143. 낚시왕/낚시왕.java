

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C,M; // M: number of sharks
    static int answer = 0;
    static int[][] matrix;
    static Shark[] sharks;
    static class Shark{
        int row, column;
        int[] position;
        int speed;
        int direction;
        int size;
        boolean alive;

        public Shark(int row, int column, int speed, int direction, int size) {
            this.row = row;
            this.column = column;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
            this.alive = true;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[R+1][C+1];
        sharks = new Shark[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(r,c,s,d,z);
            matrix[r][c] = i+1; // 초기 위치, 1번부터 시작
        }

        for (int col = 1; col <= C; col++) {
            answer += fishing(col);
            sharkMove();
        }
        System.out.println(answer);
    }

    static int fishing(int col){
        for (int row = 1; row <= R; row++) {
            if(matrix[row][col] != 0){
                int sharkIndex = matrix[row][col] - 1;
                Shark shark = sharks[sharkIndex];
                shark.alive = false;
                matrix[row][col] = 0;
                return shark.size;
            }
        }
        return 0;
    }

    static void sharkMove(){
        int[][] newMatrix = new int[R+1][C+1];

        for (int i = 0; i < M; i++) {
            Shark shark = sharks[i];
            if(!shark.alive){
                continue;
            }
            int r = shark.row;
            int c = shark.column;
            int s = shark.speed;
            int d = shark.direction;

            // 실제 이동 계산
            // 수직 이동 (위/아래)
            if (d == 1 || d == 2) {
                int move = s % (2 * (R - 1));
                for (int m = 0; m < move; m++) {
                    if (d == 1 && r == 1) d = 2;
                    else if (d == 2 && r == R) d = 1;
                    r += (d == 1) ? -1 : 1;
                }
            }
            // 수평 이동 (왼/오)
            else if (d == 3 || d == 4) {
                int move = s % (2 * (C - 1));
                for (int m = 0; m < move; m++) {
                    if (d == 3 && c == C) d = 4;
                    else if (d == 4 && c == 1) d = 3;
                    c += (d == 3) ? 1 : -1;
                }
            }

            // 최종 위치와 방향 저장
            shark.row = r;
            shark.column = c;
            shark.direction = d;

            // 충돌 처리 (큰 상어만 살아남음)
            if (newMatrix[r][c] == 0) { // 자리가 빈 경우 --> 상어 그대로 배치
                newMatrix[r][c] = i + 1;
            } else {    // 여러 상어가 한 자리에 있는 경우
                int otherIdx = newMatrix[r][c] - 1;
                Shark other = sharks[otherIdx];
                if (shark.size > other.size) {
                    other.alive = false;
                    newMatrix[r][c] = i + 1;
                } else {
                    shark.alive = false;
                }
            }
        }
        matrix = newMatrix;
    }
}
