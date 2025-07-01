import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class Main {

	static int n,k;
	static int[] arr;
	static boolean[] used;
	static int count;
	static void solve(int weight, int day){

		if(day + 1 == n){
			count++;
			return;
		}

		for (int i = 0; i < n; i++) {

			if(weight - k + arr[i] >= 500 && !used[i]){
				used[i] = true;
				solve(weight -k + arr[i], day + 1);
				used[i] = false;
			}

		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		k = parseInt(st.nextToken());
		arr = new int[n];
		used = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		solve(500, 0);
		System.out.println(count);
	}

}
