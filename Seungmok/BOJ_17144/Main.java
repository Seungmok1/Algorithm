import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] matrix;
    static List<Integer> cleaner = new ArrayList<>(2);
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void spread() {
        int[][] newDust = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(matrix[i][j]>0){
                    int spreadDust = matrix[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx<0 || nx>=R || ny<0 || ny>=C || matrix[nx][ny]==-1) continue;
                        newDust[nx][ny] += spreadDust;
                        matrix[i][j] -= spreadDust;
                    }
                    newDust[i][j] += matrix[i][j];
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(matrix[i][j]!=-1) matrix[i][j] = newDust[i][j];
            }
        }
    }

    static void clean() {
        int x = cleaner.get(0)-1;
        int y = 0;

        for (int i = 0; i < 4; i++) {
            while(x+dx[i]>=0 && x+dx[i]<=cleaner.get(0) && y+dy[i]>=0 && y+dy[i]<C){
                if(matrix[x + dx[i]][y + dy[i]]==-1) {
                    matrix[x][y] = 0;
                    break;
                }
                matrix[x][y] = matrix[x + dx[i]][y + dy[i]];
                x += dx[i];
                y += dy[i];
            }
        }

        x = cleaner.get(1)+1;
        y = 0;

        for (int i = 2;; i--) {
            while(x+dx[i]>=cleaner.get(1) && x+dx[i]<R && y+dy[i]>=0 && y+dy[i]<C){
                if(matrix[x + dx[i]][y + dy[i]]==-1) {
                    matrix[x][y] = 0;
                    break;
                }
                matrix[x][y] = matrix[x + dx[i]][y + dy[i]];
                x += dx[i];
                y += dy[i];
            }
            if(i==0) i=4;
            else if(i==3) break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j]==-1) cleaner.add(i);
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            clean();
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(matrix[i][j]>0) answer += matrix[i][j];
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
