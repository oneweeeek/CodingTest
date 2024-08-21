import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m,result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        String[] board = new String[n];
        result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }
        int min = count(board);
        System.out.println(min);

        br.close();
	}

	static int count(String[] board) {
		
		 for (int i = 0; i <= n - 8; i++) {
	            for (int j = 0; j <= m - 8; j++) {
	                int cnt1 = 0;
	                int cnt2 = 0;
	                for (int a = i; a < i + 8; a++) {
	                    for (int b = j; b < j + 8; b++) {
	                        if ((a + b) % 2 == 0) {
	                            if (board[a].charAt(b) != 'B') cnt1++;
	                            else if (board[a].charAt(b) != 'W') cnt2++;
	                        } else {
	                            if (board[a].charAt(b) != 'W') cnt1++;
	                            else if (board[a].charAt(b) != 'B') cnt2++;
	                        }
	                    }
	                }
	                result = Math.min(result, Math.min(cnt1, cnt2));
	            }
	        }
		 return result;
	}
}
