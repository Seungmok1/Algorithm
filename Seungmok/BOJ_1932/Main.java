import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N];
        int[][] dp = new int[N+1][N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][0] = arr[1][0];

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if(j -1 < 0) dp[i][j] = dp[i-1][j] + arr[i][j];
                else dp[i][j] = Math.max(dp[i - 1][j - 1] + arr[i][j], dp[i - 1][j] + arr[i][j]);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[N][i]);
        }

        bw.write(max + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
