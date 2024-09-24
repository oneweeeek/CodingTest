import java.io.*;
import java.math.BigInteger;

import static java.lang.Integer.*;

public class Main {

	static int n;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parseInt(br.readLine());
		// 이동 횟수는 a^n=2^n−1 이 된다.
		System.out.println(BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE));
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
			System.out.print(sb);
		}

	}

	static void hanoi(int num, int from, int tmp, int to) {

//		n개의 탑을 1번에서 3번으로 이동하려면
//		 n-1개를 1 -> 2 이동 
//		남은 1개를 1 -> 3 이동
//		n-1개를 2 -> 3 이동
		if (num == 1) {
			sb.append(from + " " + to + "\n");
			return;
		}
		hanoi(num - 1, from, to, tmp); // 1번에서 2번으로
		sb.append(from + " " + to + "\n");
		hanoi(num - 1, tmp, from, to); // 2번에서 3번으로

	}
}
