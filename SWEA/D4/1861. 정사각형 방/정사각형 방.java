import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
 
 
/*
[입력]
첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N (1 ≤ N ≤ 103)이 주어진다.
다음 N개의 줄에는 i번째 줄에는 N개의 정수 Ai, 1, … , Ai, N (1 ≤ Ai, j ≤ N2) 이 공백 하나로 구분되어 주어진다.
Ai, j는 모두 서로 다른 수이다.
 
 
[출력]
각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
한 칸을 띄운 후, 처음에 출발해야 하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지를 공백으로 구분하여 출력한다.
이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.
 
[예제 풀이]
첫 번째 테스트 케이스는 1 또는 3이 적힌 곳에 있어야 한다.
두 번째 테스트 케이스는 3 또는 6이 적힌 곳에 있어야 한다.
 
# 입력
2
2
1 2
3 4
3
9 3 4
6 1 5
7 8 2
 
# 출력
#1 1 2
#2 3 3
 * */
 
public class Solution {
    // 내가 있는 방에서 상하좌우로 이동 가능 but 이동하려는 숫자의 방이 현재 방의 숫자보다 정확히 1 커야 한다
    // 가장 많은 방을 이동할 수 있는 처음 방의 위치를 구하는 프로그램
    /* x,y기준으로 너비탐색
     *  N^2으로 완전탐색하는데 (x,y)기준 4방탐색해서 값이 1큰놈만 bfs()돌리기 !! 라는 논리로 세워봤습니다
     * */
     
    static int n, map[][];
    static Queue<Coordinate> queue;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static boolean visited[][];
    static int ans,min,count;
     
    static boolean isRange(int x,int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }
    static class Coordinate{
        int x,y,number;
        public Coordinate(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
     
    static void bfs() {
        while(!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int curX = coordinate.x;
            int curY = coordinate.y;
            int curNumber = coordinate.number;
             
            for(int d=0;d<4;d++) {
                int nx = curX+dx[d];
                int ny = curY+dy[d];
                if(isRange(nx,ny)&&!visited[nx][ny]&&curNumber+1 == map[nx][ny]) { // 범위안 && 방문x && 현재좌표 번호+1 == 다음좌표번호가 같다면 이동가능
                    visited[nx][ny] = true;
                    count++;
                    queue.offer(new Coordinate(nx,ny,map[nx][ny]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            n = parseInt(br.readLine());
            map = new int[n][n];
            for (int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    map[i][j] = parseInt(st.nextToken());
                }
            }
            min = MAX_VALUE; // 최대이동할 수 있는 방이 많은 출발점중 최소값
            ans = MIN_VALUE; // 최대이동할 수 있는 방 갯수
            for (int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    for(int d=0;d<4;d++) {
                        int nx = i+dx[d];
                        int ny = j+dy[d];
                        if(isRange(nx,ny)&&map[nx][ny] == map[i][j]+1) { // 범위안에 있고 현재요소 값+1이 사방탐색값과 같으면 후보군이므로 bfs시작
                        	visited = new boolean[n][n]; // 방문처리할 boolean 배열
                            count = 0;
                            queue = new LinkedList<Coordinate>();
                            count++;
                            visited[i][j] = true;
                            queue.offer(new Coordinate(i,j,map[i][j])); // 현좌표 카운트하고 방문처리하고 큐에 추가 후 너비탐색
                            bfs();
                            if (count > ans || (count == ans && map[i][j] < min)) { // 최대이동할 수 있는 방값을 갱신할수 있다면
                                ans = count;	                                    // 현좌표와 최소값중에 비교해서 최소값 갱신
                                min = map[i][j];
                            }
//                            if(count >= ans) { 올바르지 않은 조건이라고 합니다.. 
//                            	ans = count;
//                                min = Math.min(min, map[i][j]); 
//                                for(int k=0;k<n;k++) {
//                                	for(int l =0;l<n;l++) {
//                                		System.out.print(visited[k][l]+" ");
//                                	}
//                                	System.out.println();
//                                }
//                            }
                        }
                    }
                }
            }
            System.out.println("#"+tc+" "+min+" "+ans);
        }
    }
}