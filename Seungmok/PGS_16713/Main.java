import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] nums = new int[N + 1];
        int[] preXor = new int[N + 1];
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            preXor[i] = preXor[i - 1] ^ nums[i];
        }

        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int xor = preXor[b] ^ preXor[a - 1];
            result ^= xor;
        }

        System.out.println(result);
    }
}

// https://www.acmicpc.net/problem/16713
