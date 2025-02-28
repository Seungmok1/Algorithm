import java.io.*;
import java.util.*;

public class Main {

    static int E, S, M;

    static int cal() {
        int e, s, m;
        for (int year = 1; ; year++) {
            e = year % 15 == 0 ? 15 : year % 15;
            s = year % 28 == 0 ? 28 : year % 28;
            m = year % 19 == 0 ? 19 : year % 19;

            if (e == E && s == S && m == M) return year;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        System.out.println(cal());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}
