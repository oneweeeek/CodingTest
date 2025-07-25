import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
public class Main {
    static int n, min;
    static int[][] scores;
    static boolean[] selected;
    static void combi(int index, int depth){

        if(depth == n/2){
            cal();
            return;
        }

        for (int i = index; i < n; i++) {
            if(!selected[i]){
                selected[i] = true;
                combi(i, depth + 1);
                selected[i] = false;
            }
        }
    }
    static void cal(){
        int startScore = 0;
        int linkScore = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(selected[i] && selected[j]){
                    startScore += scores[i][j];
                }
                if(!selected[i] && !selected[j]){
                    linkScore += scores[i][j];
                }
            }
        }
        min = Math.min(min, Math.abs(startScore - linkScore));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        scores = new int[n][n];
        selected = new boolean[n];
        min = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                scores[i][j] = parseInt(st.nextToken());
            }
        }
        combi(0,0);
        System.out.println(min);
    }
}
