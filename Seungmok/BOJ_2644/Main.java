import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer=0;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        arr = new int[M][2];
        visited = new boolean[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        if (dfs(a, b, 0)) {
            bw.write(answer + "");
        } else {
            bw.write(-1 + "");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean dfs(int a, int b, int count) {
        if(a == b) return true;
        for (int i = 0; i < M; i++) {
            if (!visited[i]) {
                if (arr[i][0] == a) {
                    visited[i] = true;
                    answer++;
                    if (dfs(arr[i][1], b, count + 1)) {
                        return true;
                    }
                    visited[i] = false;
                    answer--;
                }
                if (arr[i][1] == a) {
                    visited[i] = true;
                    answer++;
                    if (dfs(arr[i][0], b, count + 1)) {
                        return true;
                    }
                    visited[i] = false;
                    answer--;
                }
            }
        }
        return false;
    }
}
