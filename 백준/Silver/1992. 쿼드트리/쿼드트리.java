
import java.io.BufferedReader;
import java.io.InputStreamReader;
// top down
public class Main {
    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
//      map = new char[N][N]; // 공간 낭비 발생 <= toCharArray()
        map = new char[N][];
        
        for (int i = 0; i < N; i++) { // 행
            map[i] = br.readLine().toCharArray();
        }
        divide(0, 0, N);
        System.out.println(sb);
    }
    static boolean check(int y, int x, int n) {
        char ch = map[y][x]; // 검사 시작 위치의 문자
        
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if( ch != map[i][j] ) return false; // 즉시 return false
            }
        }
        
        return true;
    }
    
    static void divide(int y, int x, int n) {
    
        if( check(y, x, n) ) { // 모두가 같은 문자이면
            
            sb.append(map[y][x]); // 시작 문자 1개 추가
            
        }else { // 모두가 같은 문자는 아니다.
            sb.append("(");
            
            // 4 영역으로 나누어서 동일한 작업을 수행
            int half = n / 2;
            
            divide(y, x, half); // 왼쪽 위
            divide(y, x + half, half); // 오른쪽 위
            divide(y + half, x, half); // 왼쪽 아래
            divide(y + half, x + half, half); // 오른쪽 아래
            
            sb.append(")");
        }
    
    }
}