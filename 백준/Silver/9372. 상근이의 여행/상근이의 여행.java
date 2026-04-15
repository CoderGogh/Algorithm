

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 9372
// https://www.acmicpc.net/problem/9372
public class Main {
    static int T,N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트 케이스 수
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            // 자료구조에 저장
            // 자료구조........뭘로 해야 하나........................................이런게 제일 헷갈리네 젠장
            // 상근이는 새로운 비행기를 무서워하는 것도 어이없네 생각해보니까
            // 항상 연결 그래프 MST 연결 edge 정보 사실상 필요없음이네
            // 뭐야 이거 문제 약간 이상한데
            for (int j = 0; j < M; j++) {
                br.readLine();
            }
            System.out.println(N-1);
        }
    }
}
