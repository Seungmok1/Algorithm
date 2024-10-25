import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        int max = 0;

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        int[] dp = new int[max + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int j = 4; j <= max; j++) {
            dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
        }

        for (int i = 0; i < T; i++) {
            bw.write(dp[arr[i]] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
