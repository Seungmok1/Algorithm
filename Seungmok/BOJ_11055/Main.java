import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[N] = arr[N];

        for (int i = N - 1; i >= 0; i--) {
            int max = 0;
            for (int j = N; j > i; j--) {
                if(arr[i] < arr[j]) max = Math.max(max, arr[i] + dp[j]);
                else max = Math.max(max, arr[i]);
            }
            dp[i] = max;
            answer = Math.max(answer, max);
        }

        bw.write(answer + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
