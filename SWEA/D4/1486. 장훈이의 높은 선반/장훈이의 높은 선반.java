import static java.lang.Integer.*;
import java.util.*;
import java.io.*;

public class Solution {
	static int n,b,arr[],min;
	
	static void dfs(int depth, int sum) {
        if(b <= sum) { // 합계가 이미 최소합계를 넘어가면 종료
            min = Math.min(min, sum);
            return;
        }
        // sum이 현재 min보다 크거나 같으면 더 이상 탐색할 필요가 없다
        if (min <= sum) {
            return;
        }
		if(depth == arr.length) {
			return;
		}
		dfs(depth+1, sum+arr[depth]);
		dfs(depth+1,sum);
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = parseInt(br.readLine());
        
        for(int tc=1;tc<=T;tc++) {
        	
        	st = new StringTokenizer(br.readLine());
        	n = parseInt(st.nextToken());
        	b = parseInt(st.nextToken()); // 장훈이키
        	st = new StringTokenizer(br.readLine());
        	arr = new int[n];
        	for(int i=0;i<n;i++) {
        		arr[i] = parseInt(st.nextToken());
        	}
        	min = MAX_VALUE;
        	dfs(0, 0);
        	System.out.println("#"+tc+" "+(min-b));
        }
    }
}
