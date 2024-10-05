import static java.lang.Integer.*;
import java.io.*;
import java.util.*;

public class Solution {

	static int k, result, map[][];
	static int magnetic[][];

	// num == 회전시킬 자석
	// direction 1 == 시계방향, -1은 반시계 방향
	static void rotation(int num, int direction) {
		if (direction == 1) {
			int save = magnetic[num][7];
			for (int k = 7; k > 0; k--) {
				magnetic[num][k] = magnetic[num][k-1];
			}
			magnetic[num][0] = save;
		} else {
			int save = magnetic[num][0];
			for (int k = 0; k < 7; k++) {
				magnetic[num][k] = magnetic[num][k + 1];
			}
			magnetic[num][7] = save;
		}
	}

	static boolean isSame(int left, int right) {
		if (magnetic[left][2] == magnetic[right][6]) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			magnetic = new int[4][8];
			k = parseInt(br.readLine());

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnetic[i][j] = parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int num = parseInt(st.nextToken()) - 1;
				int direction = parseInt(st.nextToken());
				
				int[] directions = new int[4]; //회전할 방향을 저장
				directions[num] = direction;
				
				for(int j=num+1; j<4; j++) {
					if(isSame(j-1,j)) 
						break;
					else
						directions[j] = -directions[j-1];
				}
				for(int j=num-1; j>=0;j--) {
					if(isSame(j,j+1))
						break;
					else
						directions[j] = -directions[j+1];
				}
				for(int j=0; j<4; j++) {
					if(directions[j] == 0) continue;
					rotation(j, directions[j]);
				}
			}

			for (int i = 0; i < 4; i++) {
				if (magnetic[i][0] == 1) {
					result += Math.pow(2, i);
				}
			}
			System.out.println("#" + tc + " " + result);
			result = 0;
		}
	}
}
