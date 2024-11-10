import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int tree[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int max = 0;
			tree = new int[n];
			for (int i = 0; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tree[i]);
			}
			// odd 나무길이의 차이가 홀수인 갯수
			int odd = 0;
			int sumDiff = 0;
			for (int i = 0; i < n; i++) {
				int diff = max - tree[i];
				if (diff == 0) {
					continue;
				} else if (diff % 2 == 1) {
					odd++;
				}
				sumDiff += diff;
			}
			// days는 최적 날짜
			int days = sumDiff / 3 * 2 + sumDiff % 3;
			// one 1의 물을 준날
			int one = days / 2 + days % 2;
			if(one >= odd) {
				// 1의 물을 줄 수 있는날이 홀수나무보다 크면 최적으로 줬을테니가
				System.out.println("#" + tc + " " + days);
			}else {
				// 아니면 홀수나무의 1을 줄 수 있는 날이 중요함
				// 사이에 알아서 2더할 수 있는 날은 상관없으니까
				days = odd * 2 -1;
				System.out.println("#" + tc + " " + days);
			}
		}
	}
}
