

import java.util.Scanner;

// 백준 10974번
public class Main {
    static int N;
    static int[] src;
    static int[] tgt;
    static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        src = new int[N];
        tgt = new int[N];
        check = new boolean[N];

        for (int i = 0; i < N; i++) {
            src[i] = i + 1; // 1부터 N까지 초기화
        }

        permutation(0);
        sc.close();
    }

    static void permutation(int tgtIdx) {
        if (tgtIdx == N) { // 기저 조건
            for (int i = 0; i < N; i++) {
                System.out.print(tgt[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (check[i]) continue;

            check[i] = true;
            tgt[tgtIdx] = src[i];
            permutation(tgtIdx + 1);
            check[i] = false; // 백트래킹
        }
    }
}
