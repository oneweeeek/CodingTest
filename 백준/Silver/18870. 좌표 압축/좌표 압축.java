import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if(map.containsKey(copy[i])) continue;
            map.put(copy[i], count++);
        }
        StringBuilder sb = new StringBuilder();
        for(int num : arr){
            sb.append(map.get(num)).append(" ");
        }
        System.out.println(sb);
    }
}
