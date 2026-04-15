import java.io.*;
import java.util.*;

public class Main {
    static int r, c, startX, startY, startDir, ansX = -1, ansY = -1, totalPipe;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static char[] pipe = {'1', '2', '3', '4', '-', '|', '+'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'M') {
                    startY = i; startX = j;
                }
                if (map[i][j] != '.') totalPipe++;
            }
        }
        totalPipe++;
        // 시작 방향 찾기
        for (int i = 0; i < 4; i++) {
            int nx = startX + dx[i], ny = startY + dy[i];
            if (isValid(nx, ny) && map[ny][nx] != '.' && map[ny][nx] != 'Z') {
                startDir = i;
            }
        }
        simulate(startX, startY, startDir);
        for (char p : pipe) {
            map[ansY][ansX] = p;
            if (simulate(startX, startY, startDir) == totalPipe) {
                System.out.println((ansY + 1) + " " + (ansX + 1) + " " + p);
                return;
            }
        }
    }

    static boolean isValid(int x, int y) {
        return y >= 0 && y < r && x >= 0 && x < c;
    }

    static int simulate(int x, int y, int dir) {
        visited = new boolean[r][c];
        visited[y][x] = true;
        int count = 1;

        while (true) {
            x += dx[dir];
            y += dy[dir];
            if (!isValid(x, y) || !canEnter(x, y, dir)) break;
            char cell = map[y][x];
            if (cell == '.') {
                if (ansX == -1) { ansX = x; ansY = y; }
                break;
            }
            if (cell == 'Z') return count + 1;
            if (!visited[y][x]) {
                visited[y][x] = true;
                count++;
            }
            dir = getNewDir(x, y, dir);
        }
        return count;
    }

    static boolean canEnter(int x, int y, int dir) {
        char c = map[y][x];
        if (c == '.' || c == 'Z' || c == '+') return true;
        if (c == '-') return dir == 0 || dir == 2;
        if (c == '|') return dir == 1 || dir == 3;
        if (c == '1') return dir == 0 || dir == 3;
        if (c == '2') return dir == 0 || dir == 1;
        if (c == '3') return dir == 1 || dir == 2;
        if (c == '4') return dir == 2 || dir == 3;
        return false;
    }

    static int getNewDir(int x, int y, int dir) {
        char c = map[y][x];
        if (c == '-' || c == '|' || c == '+') return dir;
        if (c == '1') return dir == 0 ? 1 : 2;
        if (c == '2') return dir == 0 ? 3 : 2;
        if (c == '3') return dir == 1 ? 0 : 3;
        if (c == '4') return dir == 2 ? 1 : 0;
        return dir;
    }
}