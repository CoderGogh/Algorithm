

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 벽을 세우는 것 : 조합
// 바이러스가 퍼지는 것 : 완전 탐색 bfs * dfs
// 반복문으로 2차원 배열 중 안전지대 게산 -> max 계산
// 위의 처리를 위해서는 map이 변경되어야 한다
// 원본 -> 조합 -> 완전탐색, 원본 -> 조합 -> 완전 탐색
// => 하나의 맵으로는 어렵다 & 원본 맵은 그대로 유지 & 복사한 맵에 조합 * 완전 탐색 적용
// 0(zero) 인 항목 중 벽 3개를 세워야 한다. <-- 0 항목 : src  // tgt : 3개
// 0인 항목을 자료구조 LIst 에 넣고 3개를 뽑는 방법
// virus 항목도 별도의 자료구조 List 로 관리
public class Main {
    static int N,M,max;
    static int[][] map, copyMap;
    static List<Node> zero = new ArrayList<Node>(); // 0인 구역을 담음
    static List<Node> virus = new ArrayList<Node>(); // virus인 구역을 담음
    static int zeroSize;
    static Node[] wall = new Node[3];   // tgt 역할
    static int[] del_row = {-1,1,0,0};
    static int[] del_col = {0,0,-1,1};
    static Queue<Node> queue = new ArrayDeque<>();

    static class Node{  // 좌표를 표시
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }
    public static  void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 0){
                    zero.add(new Node(i,j));
                }
                else if(n == 2){
                    virus.add(new Node(i,j));
                }
                map[i][j] = n;
            }
        }
        zeroSize = zero.size();

        max = Integer.MIN_VALUE;
        comb(0, 0);
        System.out.println(max);

    }
    static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == 3){
            // 조합 완성
            check();
            return;
        }
        if(srcIdx == zeroSize){
            return;
        }
        wall[tgtIdx] = zero.get(srcIdx);    // 선택
        comb(srcIdx+1,tgtIdx+1);
        comb(srcIdx+1,tgtIdx);  // 비선택
    }
    static void check(){
        // map -> copyMap 카피
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        // copyMap에서 wall 이 있는 공간에 1 채움
        for (int i = 0; i < 3; i++) {
            int row = wall[i].row;
            int col = wall[i].col;
            copyMap[row][col] = 1;
        }

        // 바어러스 퍼짐 bfs
        // 큐애 해당 collection을 한 번에 다 담음
        queue.addAll(virus);
        
        while(!queue.isEmpty()){
            Node n = queue.poll();

            for (int d = 0; d <4 ; d++) {
                int nr = n.row + del_row[d];
                int nc = n.col + del_col[d];
                if(nr<0 || nc <0 || nr>=N || nc>=M){
                    continue;
                }
                if(copyMap[nr][nc] == 0){   // 바이러스 퍼짐
                    copyMap[nr][nc] = 2;
                    queue.offer(new Node(nr,nc));
                }
            }
        }
        // 안전지대 갱신 --> max 갱신
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(copyMap[i][j] == 0){
                    sum++;
                }
            }
        }
        max = Math.max(max,sum);
    }
}



