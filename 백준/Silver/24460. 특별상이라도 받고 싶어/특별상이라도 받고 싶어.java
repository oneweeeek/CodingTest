import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class Main {
    static int[][] map;
    static int n;

    static int divide(int size, int x, int y) {

        if (size == 1) {
            return map[x][y];
        }

        int half = size / 2;

        int[] temp = {
                divide(half, x, y),
                divide(half, x + half, y),
                divide(half, x, y + half),
                divide(half, x + half, y + half),
        };
        Arrays.sort(temp);
        return temp[1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        System.out.println(divide(n, 0, 0));
    }
}
