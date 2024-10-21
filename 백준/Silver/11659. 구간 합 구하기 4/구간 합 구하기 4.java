import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class Main {
	static int n, m, map[], prefixSum[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());

		map = new int[n+1];
		prefixSum = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		map[0] = 0;
		for (int i = 1; i <= n; i++) {
			map[i] = parseInt(st.nextToken());
		}
		for (int i = 1; i < map.length; i++) {
			prefixSum[i] += prefixSum[i - 1] + map[i];
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			System.out.println(prefixSum[end] - prefixSum[start - 1]);
		}

	}

}
