import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[][] dp = new long[N+1][K+1];

        for (int i = 1; i <= K; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            dp[i][1] = 1;
            for (int j = 2; j <= K; j++) {
                for (int l = 0; l <= i; l++) {
                    dp[i][j] += dp[l][j-1] % 1_000_000_000;
                }
            }
        }

        bw.write(dp[N][K]% 1_000_000_000 + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
