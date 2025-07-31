import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {

    static int[] arr;
    static int n, x, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        x = parseInt(br.readLine());

        Arrays.sort(arr);
        int left = 0;
        int right = n-1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if(sum == x){
                result++;
                left++;
            }else if(sum < x){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(result);
    }
}
