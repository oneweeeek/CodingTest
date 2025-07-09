import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[] words;

    static int getCommonLength(String a, String b) {
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return len;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int maxLen = -1;
        int ans1 = -1, ans2 = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = getCommonLength(words[i], words[j]);
                if (len > maxLen) {
                    maxLen = len;
                    ans1 = i;
                    ans2 = j;
                }
            }
        }

        System.out.println(words[ans1]);
        System.out.println(words[ans2]);
    }
}
