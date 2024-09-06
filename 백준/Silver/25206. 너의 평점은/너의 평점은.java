import java.io.*;
import java.util.*;

public class Main {
	public static HashMap<String, Double> file;

	private static String[] grade;
	private static Double[] num;

	public static void print() { // 구현
		double sum = 0;
		double number = 0;
		double res = 0;

		for (int i = 0; i < 20; i++) { // 입력된 순서 대로 계산
			if (grade[i].equals("P")) { // "P"등급 일시 continue;
				continue;
			} else {
				sum += file.get(grade[i]) * num[i]; // 과목평점 * 해당과목이수학점
				number += num[i]; // 과목이수학점 총 합
			}
		}
		if (sum == 0) { // 모든 과목이 다 "P" 일때
			System.out.printf("%.6f\n", res);
		} else {
			res = sum / number;
			System.out.printf("%.6f\n", res); // 소수점 설정 및 출력
		}
	}

	public static void main(String[] args) throws IOException {
		file = new HashMap<>();
		// Hashmap에 등급별 과목점수 저장
		file.put("A+", 4.5);
		file.put("A0", 4.0);
		file.put("B+", 3.5);
		file.put("B0", 3.0);
		file.put("C+", 2.5);
		file.put("C0", 2.0);
		file.put("D+", 1.5);
		file.put("D0", 1.0);
		file.put("F", 0.0);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = new Double[20];
		grade = new String[20];
		StringTokenizer st;

		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			num[i] = Double.parseDouble(st.nextToken());
			grade[i] = st.nextToken();
		}
		print();
	}

}
