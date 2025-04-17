import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br.readLine();
        String str = br.readLine();
        int sum = 0;
        char[] arr = str.toCharArray();
        for (char c : arr) {
            sum += Integer.parseInt(String.valueOf(c));
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
        br.close();
    }
}
