import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int n, e, count;
    static int[] parents;

    static int find(int a){
        if(parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }
    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;
        if (aRoot < bRoot){
            parents[bRoot] = a;
        }else{
            parents[aRoot] = b;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = parseInt(br.readLine());
        e = parseInt(br.readLine());
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken()) - 1;
            int to = parseInt(st.nextToken()) - 1;
            union(from, to);
        }
        int root = find(0);
        for (int i = 1; i < n; i++) {
            if(find(i) == root) count++;
        }
        System.out.println(count);
    }
}
