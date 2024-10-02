import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
public class Main {
	
	static int n,m,a,b;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = parseInt(br.readLine());
		
		for(int tc=1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = parseInt(st.nextToken());
			m = parseInt(st.nextToken());
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				
				a = parseInt(st.nextToken());
				b = parseInt(st.nextToken());
			}
			System.out.println(n-1);
		}
	}

}
