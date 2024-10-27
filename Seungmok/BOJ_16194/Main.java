import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.min(dp[j] + dp[i-j], dp[i]);
            }
        }

        bw.write(dp[N] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
