        import java.io.*;
        import java.util.*;

        public class Main {

            public static void main(String[] args) throws IOException {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st;
                int n = Integer.parseInt(br.readLine());
                int[] arr = new int[n];
                int[] memo = new int[n];
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                    memo[i] = 1001;
                }
                memo[n-1] = 0;

                for (int i = n-2; i >= 0; i--) {
                    for (int j = 1; j <= arr[i]; j++) {
                        if(i + j >= n) continue;
                        memo[i] = Math.min(memo[i], memo[i + j] + 1);
                    }
                }

                if (memo[0] == 1001) {
                    memo[0] = -1;
                }

                bw.write(memo[0]+"");
                bw.flush();
                bw.close();
                br.close();
            }
        }
