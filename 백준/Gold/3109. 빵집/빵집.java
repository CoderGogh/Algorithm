import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 왼쪽 row 1 개당 한번의 파이프를 만들어야 함. delta for 문에서 여러개의 파이프로 뻗어나가는 경우가 생긴다.
//  => delta 내에서 한번 시도해서 성공하면 for 문을 멈춘다.
public class Main {
    static int R, C, ans;
    static char[][] map;
    
    static int[] dy = { -1, 0, 1 }; // 우상, 우, 우하로 우선순위를 가지는 delta, x 는 항상 +1 증가
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][]; // 초기 배열이 가비지가 되지 않도록
        
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        // 풀이
        // 왼쪽 위 행부터 아래 행으로 움직이면서 각 행에서 pipe 를 최대한 잘 놓아야 한다.
        for (int i = 0; i < R; i++) {
            if( pipe(i, 0) ) ans++;
        }
        
        System.out.println(ans);
    }
    static boolean pipe(int y, int x) {
        // 기저 조건
        if( x == C - 1 ) {
            return true;
        }
        
        // delta 를 이용한 방문 
        for (int d = 0; d < 3; d++) {
            int ny = y + dy[d];
            int nx = x + 1;
            
            // x 는 기저 조건에서 체크, y에 대해서만 범위체크
            // 건물 체크
            if( ny < 0 || ny >= R || map[ny][nx] == 'x' ) continue;
            // delta 탐색으로 중복 방문에 대한 처리는 해당 좌표를 한번 방문하면 무조건 방문처리를 해서 다음 시도때 방문하지 않도록 한다.
            // 해당 좌표를 통한 성공 -> 파이프가 놓여져 있는 경로가 된다. 
            // 해당 좌표를 통한 실패 -> 재방문 필요 X
            // visit 표시는 건물로.
            map[ny][nx] = 'x';
            if( pipe(ny, nx) ) return true; // 현재 delta 를 통한 ny, nx 로 마지막까지 가서 성공하면 여기서 끝낸다.
        }
        
        return false;
    }
}