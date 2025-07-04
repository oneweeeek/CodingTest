import java.io.*;
import java.util.*;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.parseInt;
public class Main {
    static int n,m;
    static int[] arr;

    static void parametricSearch(int left, int right){

        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            int budget = 0;
            for (int i = 0; i < n; i++) {
                budget += Math.min(arr[i], mid);
            }
            if(budget <= m){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int right = MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }
        st = new StringTokenizer(br.readLine());
        m = parseInt(st.nextToken());
        parametricSearch(0,right);
    }

}
