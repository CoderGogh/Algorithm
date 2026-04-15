

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 조합 (궁수 3명을 x 좌표 어느 곳에 배치할 지)
// 조합이 완성되면, 규칙에 따라 시뮬레이션 진행
// 궁수로부터 가장 가가이 있는 적을 계산    -- > BFS로 처리 방법도 있음(2차원 배열 탐색)
//                                  --> priorityQueue로 가장 가까운 적 (좌표를 가지고 탐색)

// --> 중간에 장애물이 없기 때문에, priority Queue를 사용할 수 있음
public class Main{
    static int N, M, D, max;
    static int[] archer = new int[3];
    static List<Enemy> enemyOriginal = new ArrayList<>();   // 최초 상태 유지
    static List<Enemy> enemy = new ArrayList<>();   // 시뮬레이션 진행용
    static PriorityQueue<Enemy>queue = new PriorityQueue<>(
            (e1,e2)->e1.dist == e2.dist ? e1.col-e2.col:e1.dist-e2.dist);
    // 제거해야 할 적을, 기준을 세워, 큐에 넣음
    // 궁수와의 거리가 같으면 row, 거리가 다르면 dist 기준

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    enemyOriginal.add(new Enemy(i, j));
                }
            }
        }
        comb(0,0);
        System.out.println(max);
    }
    static void check(){
        // 적 좌표 초기화
        enemy.clear();
        for (Enemy i : enemyOriginal
             ) {
            enemy.add(new Enemy(i.row,i.col));      // 객체를 바로 할당하면 오염됨. 따라서 새로운 객체를 만들어서 넣어줌
        }
        // 시뮬레이션
        int deadCnt = 0;
        while(true){
            for (int i = 0; i < 3; i++) {   // 궁수 3명
                // 각 궁수마다 가까운 거리의 적에게 발사
                queue.clear();
                int ar = archer[i];
                int size = enemy.size();

                // 유효거리 D 안에 있는 모든 적들을 queue에 담음
                for (int j = 0; j < size; j++) {
                    Enemy e = enemy.get(j);
                    e.dist = Math.abs(ar - e.col) + Math.abs(N-e.row);
                    if(e.dist > D){
                        continue;
                    }
                    queue.offer(e);
                }
                // 유효 사격거리 D 안에 해당하는 적이 있으면 제거
                if(!queue.isEmpty()){
                    queue.poll().dead = true;
                }
            }
            // 죽은 적 제거
            // 적이 한 칸 아래로 내려옴
            for(int i = enemy.size()-1; i >=0 ; i--) {
                Enemy e = enemy.get(i);
                if(e.dead){
                    enemy.remove(i);    //   사망 처리
                    deadCnt++;
                }else if(e.row == N-1){
                    enemy.remove(i);
                }else{
                    e.row++;    // 남은 적이 한 칸씩 아래로 이동
                }
            }
            // 적들이 모두 사라지면 종료됨( 죽거나, 밑으로 내려감 )
            if(enemy.size() == 0){
                break;
            }
        }
        max = Math.max(max,deadCnt);
    }
    static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == 3){
            check();
            return;
        }
        if(srcIdx == M){
            return;
        }
        archer[tgtIdx] = srcIdx;    // 열의 위치를 할당
        comb(srcIdx+1,tgtIdx+1);
        comb(srcIdx+1,tgtIdx);
    }
    static class Enemy{
        int row, col, dist; // 궁수와의 거리
        boolean dead;

        public Enemy(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
