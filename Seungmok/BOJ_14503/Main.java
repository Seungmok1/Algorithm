import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, c, d;
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean isDirty() {
        boolean dirty = false;
        for(int i=0;i<4;i++){
            int nx = r + dx[i];
            int ny = c + dy[i];
            if(matrix[nx][ny]==0) {
                dirty = true;
                break;
            }
        }
        return dirty;
    }

    static int cleaning() {
        int answer = 0;
        while(true) {
            if(matrix[r][c]==0) {
                matrix[r][c] = 2;
                answer++;
            }
            if(isDirty()){
                d = (d + 3) % 4;
                if(matrix[r+dx[d]][c+dy[d]]==0){
                    r = r + dx[d];
                    c = c + dy[d];
                }
            } else if (matrix[r-dx[d]][c-dy[d]]!=1) {
                r = r - dx[d];
                c = c - dy[d];
            } else break;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(cleaning() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
