

// 부분 집합
// 선택된, 선택되지 않은 두 개의 선거구로 표현
// 두 선거구 모두 선택된 각 구역별로 모두 연결
// bfs
// 각 구역별 연결 여부 확인 - 인접 행렬

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, min;
    static boolean[][] matrix;  // 가중치가 없는 그래프 --> boolean 타입
    static int[] population;    // 각 구역별 인구수 관리
    static boolean[] select;    // 부분집합에서 사용
    static boolean[] visit;     // 완전탐색에서 재 방문 방지 + 모두 연결되었는지 확인
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new boolean[N+1][N+1];
        population = new int[N+1];
        select = new boolean[N+1];
        visit = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 각 노드 별 연결 구역 수
            for(int j = 1; j <= n; j++) {
                int v = Integer.parseInt(st.nextToken());   // i --> v 연결
                matrix[i][v] = true;
            }
        }

        min = Integer.MAX_VALUE;

        subset(1);
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }

    }

    // select 배열이 완성된 상태(선택 비선택)
    // A, B 두 개로 나누어서 각각 모두 연결되었는지 확인 --> BFS
    // 인구수 계산 후 최솟값 갱신
    static void check(){
        // visit 배열*queue 초기화
        Arrays.fill(visit,false);
        queue.clear();

        // A: selected     B : unselected

        // A
        for(int i = 1; i <= N; i++) {
            if(select[i]){
                visit[i] = true;
                queue.offer(i);
                break;  //   A 선거구에 해당하는 구역 한 개만 넣고, break;
            }
        }
        if(queue.size() == 0){
            return;
        }
        while(!queue.isEmpty()){
            int v = queue.poll();

            for(int i = 1; i <= N; i++) {
                if(!matrix[v][i] || visit[i] || !select[i]){
                    continue;
                }
                visit[i] = true;
                queue.offer(i);
            }
        }
        // ----------------------------------
        // B
        for(int i = 1; i <= N; i++) {
            if(!select[i]){
                visit[i] = true;
                queue.offer(i);
                break;  //   B 선거구에 해당하는 구역 한 개만 넣고, break;
            }
        }
        while(!queue.isEmpty()){
            int v = queue.poll();

            for(int i = 1; i <= N; i++) {
                if(!matrix[v][i] || visit[i] || select[i]){
                    continue;
                }
                visit[i] = true;
                queue.offer(i);
            }
        }
        // 모두 연결되었는 지 확인
        // visit 확인

        for(int i = 1; i <= N; i++) {
            if(!visit[i]){
                return;
            }
        }
        // 정상적인 두 선거구 분리
        int sumA = 0;
        int sumB = 0;
        for(int i = 1; i <= N; i++) {
            if(select[i]){
                sumA += population[i];
            }else{
                sumB += population[i];
            }
        }
        min = Math.min(min,Math.abs(sumA-sumB));
    }
    static void subset(int srcIdx){
        if(srcIdx == N+1){  // 부분 집합 완성
            check();    // select 배열에 선택, 비선택이 표현
            return;
        }
        select[srcIdx] = true;
        subset(srcIdx+1);

        select[srcIdx] = false;
        subset(srcIdx+1);
    }
}
