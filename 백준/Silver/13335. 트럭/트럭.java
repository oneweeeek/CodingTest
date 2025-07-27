import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int n, w, l, max;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        w = parseInt(st.nextToken());
        l = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] bridge = new int[n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int truck = parseInt(st.nextToken());
            bridge[i] = truck;
        }
        int cursor = 0;
        int time = 0;
        boolean qFlag = false;

        while (!qFlag){
            if(!q.isEmpty()){
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    cur[0] += 1;
                    max -= cur[1];
                    if(cur[0] <= w){
                        q.offer(cur);
                        max += cur[1];
                    }
                }
            }
            if(cursor < n){
                int maxWeight = max + bridge[cursor];
                if(q.size() <= w && maxWeight <= l){
                    max += bridge[cursor];
                    q.add(new int[]{1, bridge[cursor++]});
                }
            }
            if(q.isEmpty()) qFlag = true;
            time++;
        }
        System.out.println(time);
    }
}
