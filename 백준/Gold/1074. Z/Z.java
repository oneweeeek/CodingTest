import java.io.*;
import java.util.*;
import static java.lang.Integer.*;


/* 2^N * 2^N 배열을 z모양으로 탐색
 * 2^1 * 2^1이 될때까지 4등분 한 후에 재귀적으로 순서대로 방문
 * */
	
public class Main {
	
	static int n, targetR, targetC, cnt /*map[][]*/;
	static int half,size,standardR,standardC;
	// 배열의 크기가 N<1을 만족할때까지 4등분 후 방문 -> 즉 길이가 2가 될때까지 등분
	static void cut(int r, int c, int len) {
		
		// 배열의 크기가 2이면 분할하지 않음
		if(len == 2) {
			// 2*2배열의 갯수만큼 cnt++
			for(int i=r, rEnd = r+len; i< rEnd;i++) {
				for(int j = c, cEnd = c+len; j< cEnd;j++) {
//					map[i][j] = cnt;
					if(i==targetR && j==targetC) {
						System.out.println(cnt);
						return;
					}
					cnt++;
				}
			}
		}
		
		half = len/2;
		size = half*half;
		standardR = r+half;
		standardC = c+half;
		
		// 1사분면
		if(targetR< standardR&& targetC < standardC) {
			cut(r,c,half);
		}
		// 2사분면
		else if(targetR<standardR && targetC >= standardC) {
			cnt+=size;
			cut(r,standardC,half);
			
		}
		// 3사분면
		else if(targetR>=standardR && targetC<standardC) {
			cnt += size*2;
			cut(standardR,c,half);
		}
		// 4사분면
		else {
			cnt += size*3;
			cut(standardR,standardC,half);
		}
	}
		
	
	
	/* 1. 메모리 초과가 발생하는 이유는 프로그램이 생성하는 map 배열의 크기 때문입니다. 
	 * 현재 map 배열은 2^N * 2^N 크기인데, N이 커질수록 배열의 크기는 기하급수적으로 증가하게 됩니다. 
	 * 예를 들어, N=20일 경우, 배열의 크기는 2^20 * 2^20 = 1,048,576 * 1,048,576이 되며, 
	 * 이는 메모리 제한을 초과하게 됩니다.
	 * 
	 * 2. 시간초과남
	 * 1234사분면쪽으로 다 탐색들어가는데 이거를 r,c가있는 사분면으로만 탐색들어가게 1,2,3,4 나눠서?
	 * 
	 * */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parseInt(st.nextToken());
		targetR = parseInt(st.nextToken());
		targetC = parseInt(st.nextToken());
		int len = (int) Math.pow(2, n);
//		map = new int [len][len];
		cnt = 0;
		cut(0,0,len);
//		System.out.println(map[r][c]);
	}
	
	
}
