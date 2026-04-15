

import java.util.Scanner;

// 완탐 시간초과!
// 그리디

// 개별적인 5KG 를 사용하지 않고 한꺼번에 5KG를 사용하자 -> 5의 배수를 3으로 만들자
public class Main {
    static int N, count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        while (true){
            if(N <0){
                System.out.println(-1);
                break;
            }
            if(N % 5 ==0){
                System.out.println(count + N/5);
                break;
            }

            // 3 사용
            N -= 3;
            count++;
        }
    }
}
