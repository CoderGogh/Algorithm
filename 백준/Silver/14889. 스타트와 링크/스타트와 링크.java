

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14889번
// 조합 + 완탐 필요
public class Main {
    static int N;
    static int[][] S;
    static boolean[] team; // 참 : start팀 , 거짓 : 링크팀
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        team = new boolean[N];
        team[0] = true; // 대칭 제거: 0번 사람을 항상 Start 팀에 넣기.
        dfs(1, 1);

        System.out.println(answer);
    }

    // idx: 다음 후보 인덱스, count: 현재 Start 팀에 속한 인원 수
    static void dfs(int idx, int count) {
        if (answer == 0) return; // 최솟값이 0이면 더 이상 탐색할 필요 없음
        if (count == N / 2) {
            computeDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            team[i] = true;
            dfs(i + 1, count + 1);
            team[i] = false;
        }
    }

    static void computeDifference() {
        int startSum = 0;
        int linkSum = 0;
        // 서로 다른 i와 j에 대하여 (S[i][j] + S[j][i])를 계산
        for (int i = 0; i < N; i++) {   // i는 0부터 시작
            for (int j = i + 1; j < N; j++) {   // j는 1부터 시작
                // 서로 다른 사람.
                if (team[i] && team[j]) {
                    startSum += S[i][j] + S[j][i];
                } else if (!team[i] && !team[j]) {
                    linkSum += S[i][j] + S[j][i];
                }
            }
        }
        answer = Math.min(answer, Math.abs(startSum - linkSum));
    }
}
