import java.io.*;
import java.util.*;

public class Main {
    static int m;
    static LinkedList<Character> list;
    static ListIterator<Character> iterator;
    static void solve(char command, char ch) {
        switch (command){
            case 'L':
                if(iterator.hasPrevious()){
                    iterator.previous();
                }
                break;
            case 'D':
                if(iterator.hasNext()){
                    iterator.next();
                }
                break;
            case 'B':
                if(iterator.hasPrevious()){
                    iterator.previous();
                    iterator.remove();
                }
                break;
            case 'P':
                iterator.add(ch);
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        list = new LinkedList<Character>();

        for(int i = 0; i < line.length(); i++) {
            list.add(line.charAt(i));
        }
        iterator = list.listIterator(list.size());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            char command =str.charAt(0);
            char ch = ' ';
            if(command == 'P'){
                ch = str.charAt(2);
            }
            solve(command, ch);
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : list){
            sb.append(ch);
        }
        System.out.println(sb);
    }
}
