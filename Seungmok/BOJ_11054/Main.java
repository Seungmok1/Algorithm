import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] up = new int[N];
        int[] down = new int[N];
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            up[i] = down[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
                if (arr[N - 1 - j] < arr[N - 1 - i]) {
                    down[N - 1 - i] = Math.max(down[N - 1 - i], down[N - 1 - j] + 1);
                }
            }
        }

        for (int k = 0; k < N; k++) {
            answer = Math.max(answer, up[k]+down[k]-1);
        }

        bw.write(answer + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
