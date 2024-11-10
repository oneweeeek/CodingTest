import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Solution {
	static int n,result,map[][];
	static boolean selected[];
	
	static void cal() {
		int aSum = 0, bSum = 0;
		for(int i=0;i<n;i++) {
			
			for(int j=0;j<n;j++) {
				if(selected[i] && selected[j]) {
					aSum+=map[i][j];
				}else if(!selected[i] && !selected[j]) {
					bSum+=map[i][j];
				}
			}
		}
		int diff = Math.abs(aSum-bSum);
		result = Math.min(result, diff); 
	}
	static void combi(int idx, int depth) {
		
		if(depth == n/2) {
			cal();
		}
		
		for(int i=idx;i<n;i++) {
			selected[i] = true;
			combi(i+1,depth+1);
			selected[i] = false;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			
			n = parseInt(br.readLine());
			map = new int[n][n];
			selected = new boolean[n];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j] = parseInt(st.nextToken());
				}
			}
			result = MAX_VALUE;
			combi(0,0);
			System.out.println("#"+tc+" "+result);
		}
		
	}

}
