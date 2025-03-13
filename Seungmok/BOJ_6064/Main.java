//https://www.acmicpc.net/problem/6064

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, M, N, x, y;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int j = 0;
            for (j = x; j <= M * N + 1; j += M) {
                if ((j+1) % N == (y+1) % N) {
                    System.out.println(j);
                    break;
                }
            }
            if (j >= M * N + 1) System.out.println("-1");
        }
    }
}
