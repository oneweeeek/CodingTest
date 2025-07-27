import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int n;
    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, arr[i] * (i + 1));
        }
        System.out.println(result);
    }
}
