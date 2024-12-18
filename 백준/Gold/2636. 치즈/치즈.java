import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
import java.awt.Point;

public class Main {

    static int n, m, map[][];
    static int count, lastCheeseAmount;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        meltCheese();
    }

    // 치즈를 녹이는 과정을 수행
    static void meltCheese() {
        while (true) {
            boolean hasCheese = false;
            int currentCheeseAmount = 0;

            // 치즈가 있는지 확인 및 치즈 개수 계산
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        hasCheese = true;
                        currentCheeseAmount++;
                    }
                }
            }

            if (!hasCheese) {
                // 치즈가 모두 녹은 경우 결과 출력
                System.out.println(count);
                System.out.println(lastCheeseAmount);
                return;
            }

            lastCheeseAmount = currentCheeseAmount;
            bfsToMeltCheese();
            count++;
        }
    }

    // 치즈를 녹이는 BFS 수행
    static void bfsToMeltCheese() {
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (isInRange(nx, ny) && !visited[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        // 치즈를 녹임
                        map[nx][ny] = 0;
                    } else if (map[nx][ny] == 0) {
                        // 공기 중인 경우 BFS 진행
                        queue.add(new Point(nx, ny));
                    }
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

