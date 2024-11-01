import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class Main {
	static int n, num[];
	static char operators[];
	static boolean operatorSelected[];
	static char currentOperator[];
	static int max, min;

	static void dfs(int depth) {

		if (depth == n - 1) {
			cal();
		}

		for (int i = 0; i < n - 1; i++) {
			if (!operatorSelected[i]) {
				operatorSelected[i] = true;
				// 연산자 할당
				currentOperator[depth] = operators[i];
				dfs(depth + 1);
				operatorSelected[i] = false;
			}
		}
	}

	static void cal() {
		int sum = num[0];
		for (int i = 1; i < n; i++) {
			char operater = currentOperator[i-1];
				switch (operater) {
				case '+':
					sum += num[i];
					break;
				case '-':
					sum -= num[i];
					break;
				case '*':
					sum *= num[i];
					break;
				case '/':
					sum /= num[i];
					break;
				}
			}
		max = Math.max(sum, max);
		min = Math.min(sum, min);
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		num = new int[n];
		operatorSelected = new boolean[n - 1];
		currentOperator = new char[n - 1];
		for (int i = 0; i < n; i++) {
			num[i] = parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int plus = parseInt(st.nextToken());
		int minus = parseInt(st.nextToken());
		int multiplication = parseInt(st.nextToken());
		int division = parseInt(st.nextToken());
		operators = new char[plus + minus + multiplication + division];
		int index = 0;
		for (int i = 0; i < plus; i++) {
			operators[index++] = '+';
		}
		for (int i = 0; i < minus; i++) {
			operators[index++] = '-';
		}
		for (int i = 0; i < multiplication; i++) {
			operators[index++] = '*';
		}
		for (int i = 0; i < division; i++) {
			operators[index++] = '/';
		}
		max = MIN_VALUE;
		min = MAX_VALUE;
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

}