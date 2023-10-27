import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int max = 0;
        int[][][] storage = new int[h][n][m];
        int[][][] visited = new int[h][n][m];
        int[] dirX = {1, -1, 0, 0, 0, 0};
        int[] dirY = {0, 0, 1, -1, 0, 0};
        int[] dirZ = {0, 0, 0, 0, 1, -1};
        Queue<Integer[]> q = new LinkedList<>();
        for(int i=0;i<h;i++) {
            for(int j=0;j<n;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<m;k++) {
                    storage[i][j][k] = Integer.parseInt(st.nextToken());
                    if(storage[i][j][k]==1) {
                        visited[i][j][k] = 1;
                        q.add(new Integer[]{k, j, i});
                    }
                    if(storage[i][j][k]==-1) visited[i][j][k] = -1;
                }
            }
        }

        while(!q.isEmpty()) {
            Integer[] cur = q.poll();
            for(int i=0;i<6;i++) {
                int nx = cur[0] + dirX[i];
                int ny = cur[1] + dirY[i];
                int nz = cur[2] + dirZ[i];
                if(nx>=0 && nx<m && ny>=0 && ny<n && nz>=0 && nz<h && visited[nz][ny][nx]==0){
                    q.add(new Integer[]{nx, ny, nz});
                    visited[nz][ny][nx] = visited[cur[2]][cur[1]][cur[0]] + 1;
                }
            }
        }

        for(int i=0;i<h;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<m;k++) {
                    if(visited[i][j][k]==0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, visited[i][j][k]);
                }
            }
        }
        System.out.println(max-1);
    }
}
