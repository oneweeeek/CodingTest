import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
	static int n;
    static double[] alphabet = new double[26];

    static Stack<Double> stack = new Stack<Double>();
    static void solve(String line){
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if('A' <= ch){
                stack.push(alphabet[ch - 'A']);
            }else{
                double b = stack.pop();
                double a = stack.pop();

                double result = 0;
                switch (ch) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/': result = a / b; break;
                }
                stack.push(result);
            }
        }
    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        String line = br.readLine();

        for (int i = 0; i < n; i++) {
            double num = Double.parseDouble(br.readLine());
			alphabet[i] = num;
        }
        solve(line);
        System.out.printf("%.2f", stack.pop());
	}

}
