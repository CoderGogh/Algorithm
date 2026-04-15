

/*
시뮬레이션
아기 상어가 물고기를 먹으러 찾아가고 & 먹는 행위를 반복    <-- bfs() 를 반복
더이상 먹을 물고기가 없으면 종료

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, shark_row, shark_column, shark_size, shark_eat_count, answer;
    static int[][] map;
    static Queue<Node> queue = new ArrayDeque<>();
    static boolean[][] visit;
    static int[] del_row = {-1,1,0,0};
    static int[] del_col = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 9){
                    shark_row = i;
                    shark_column = j;
                }
                map[i][j] = n;
            }
        }

        // 시뮬레이션 진행
        shark_size = 2; // 최초 아기상어의 사이즈
        while(true){
            int distance = bfs();   // 이동 거리를 받음
            if(distance == 0){  // 이동 거리가 없다면, 더이상 먹을 물고기가 없다는 뜻 ( 물고기는 존재하지만, 먹지 못 하는 물고기가 있을 수 있음)
                break;
            }
            answer += distance; // 누적 거리를 계산
        }
        System.out.println(answer);

    }

    // 현재 아기 상어 위치에서 가장 가까운 물고기를 찾기 --> 걸린 시간(이동한 거리)를 리턴
    static int bfs(){

        // 아래를 구해야 함
        // 먹이 후보
        int minRow = Integer.MAX_VALUE; // 첫번째로 row 를 따지고
        int minCol = Integer.MAX_VALUE; // 두번째로 col 을 따지고
        int minDis = Integer.MAX_VALUE; // 마지막으로 distance 를 따짐

        // bfs 가 여러번 호출되기 때문에,
        // visit 을 초기화 해야함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit[i][j] = false;
            }
        }

        // 탐색 시작 좌표부터 queue 에 넣고 bfs()를 시작
        visit[shark_row][shark_column] = true;
        queue.offer(new Node(shark_row,shark_column,0));

        while(!queue.isEmpty()){
            Node node = queue.poll();

            int row = node.row;
            int col = node.column;
            int dis = node.distance;

            // 꺼낸 Node 가 물고기 --> 상어보다 크기가 작아야 함
            if(map[row][col] != 0 && map[row][col]< shark_size){

                if(dis < minDis){   // 현재의 distance보다 작다면, 업데이트
                    minDis = dis;
                    minRow = row;
                    minCol = col;
                }else if(dis == minDis){    // 거리가 같다면,
                    if(row<minRow){ // row 기준으로 ( 상하)
                        minDis = dis;
                        minRow = row;
                        minCol = col;
                    }else if(row == minRow){    // row 가 같다면
                        if(col < minCol){   // column 기준으로(좌우)
                            minDis = dis;
                            minRow = row;
                            minCol = col;
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int next_row = row + del_row[i];
                int next_col = col + del_col[i];
                // 범위 체크 & visit 체크 & 물고기 크기 체크
                if(next_col<0 || next_row<0 || next_row>= N || next_col >=N || visit[next_row][next_col] || map[next_row][next_col]> shark_size){
                    continue;
                }
                visit[next_row][next_col] = true;
                queue.offer(new Node(next_row,next_col,dis+1));
            }
        }
        // while 문이 끝나면, 최단 경로 minRow, minCol, minDis 가 구해짐
        if(minDis == Integer.MAX_VALUE){    // 먹지 못 했다면, 초기 값인 Max value 로 동일
            return 0;
        }else{
            shark_eat_count++;
            if(shark_eat_count == shark_size){
                shark_size++;
                shark_eat_count = 0;
            }
            map[minRow][minCol] = 0;    // 먹은 물고기 빈칸
            map[shark_row][shark_column] = 0;

            // 새로운 상어의 자리 = 먹은 자리
            shark_row = minRow;
            shark_column = minCol;
        }
        return minDis;
    }

    // 큐에 들어갈 클래스
    static class Node{
        int row, column, distance;  // 상어 위치로부터 얼만큼 왔는 지 distance로 기록

        public Node(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }
    }
}
