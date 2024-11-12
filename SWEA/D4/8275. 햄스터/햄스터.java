import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
 
public class Solution {
 
    static int n, x, m, l, r, s, max;
    static int checkCases[][];
    static int cage[];
    static int answerCage[];
    static int answer;
    static StringBuilder sb = new StringBuilder();
    static void cal() {
    	
        for(int i=0;i<m;i++) {
            int l = checkCases[i][0];
            int r = checkCases[i][1];
            int s = checkCases[i][2];
            
            int sum = 0;
            for(int j=l;j<=r;j++) {
                sum += cage[j];
            }
            if(sum != s) {
                return;
            }
        }
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum +=cage[i];
        }
        if(sum > max) {
            max = Math.max(sum, max);
            for(int i=0;i<n;i++) {
                answerCage[i] = cage[i];
            }
        }
    }
    static void perm(int depth) {
        if(depth == n) {
            cal();
            return;
        }
        for (int i = 0; i <= x; i++) {
            cage[depth] = i;
            perm(depth+1);
        }
    }
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
 
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            x = parseInt(st.nextToken());
            m = parseInt(st.nextToken());
 
            checkCases = new int[m][3];
            cage = new int[n];
            answerCage = new int[n];
 
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int index = 0;
                checkCases[i][index++] = parseInt(st.nextToken())-1;
                checkCases[i][index++] = parseInt(st.nextToken())-1;
                checkCases[i][index++] = parseInt(st.nextToken());
            }
            max = -1;
            perm(0);
            if(max == -1) {
                sb.append("#"+tc+" " + max+"\n");
            }else {
                sb.append("#"+tc+" ");
                for(int i=0;i<n;i++) {
                    sb.append(answerCage[i]+" ");
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
            sb.setLength(0);
        }
    }
 
}