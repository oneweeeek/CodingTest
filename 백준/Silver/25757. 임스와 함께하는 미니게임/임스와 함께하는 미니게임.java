import static java.lang.Integer.*;
import java.util.*;
import java.io.*;
public class Main {

    static int n;
    static char game;

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        game = st.nextToken().charAt(0);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            set.add(st.nextToken());
        }
        if(game == 'Y'){
            System.out.println(set.size());
        } else if (game == 'F') {
            System.out.println(set.size()/2);
        }else {
            System.out.println(set.size()/3);
        }
    }
}
