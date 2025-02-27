import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dots;

    static void sort() {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N - 1; j++) {
                if (dots[i][1] > dots[j + 1][1]) {
                    int[] tmp = dots[i];
                    dots[i] = dots[j + 1];
                    dots[j + 1] = tmp;
                }
            }
        }
    }

    static int sum() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (i == j || dots[i][1] != dots[j][1]) continue;
                min = Math.min(min, Math.abs(dots[i][0] - dots[j][0]));
            }
            answer += min;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        input();

        sort();

        System.out.println(sum());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dots = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
