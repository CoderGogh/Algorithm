import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int [] number  = new int[count];
        for (int i = 0; i < count; i++) {
            number[i] = sc.nextInt();
        }
        Arrays.sort(number);
        for (int a : number
             ) {
            System.out.println(a);
        }
    }
}