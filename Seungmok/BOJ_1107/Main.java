import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[] broken = new boolean[10];

    static int moveToNear() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 999999; i++) {
            if (isValid(i)) {
                int press = Math.abs(N - i) + String.valueOf(i).length();
                min = Math.min(min, press);
            }
        }
        return min;
    }

    static boolean isValid(int n) {
        if(n == 0) return !broken[0];
        while (n > 0) {
            if(broken[n % 10]) return false;
            n /= 10;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        input();

        int answer = Math.min(moveToNear(), Math.abs(N - 100));

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int brokenButtonCount = Integer.parseInt(br.readLine());

        if(brokenButtonCount > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < brokenButtonCount; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }
    }
}
