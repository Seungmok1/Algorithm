import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] dots;

    static void sort() {
        for (int i = 1; i <= N; i++) {
            Collections.sort(dots[i]);
        }
    }

    static int sum() {
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < dots[i].size(); j++) {
                answer += Math.min(toLeft(i, j), toRight(i, j));
            }
        }

        return answer;
    }

    static int toLeft(int color, int idx) {
        if(idx == 0) return Integer.MAX_VALUE;
        return dots[color].get(idx) - dots[color].get(idx - 1);
    }

    static int toRight(int color, int idx) {
        if(idx == dots[color].size() - 1) return Integer.MAX_VALUE;
        return dots[color].get(idx + 1) - dots[color].get(idx);
    }

    public static void main(String[] args) throws IOException {
        input();

        sort();

        System.out.println(sum());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dots = new ArrayList[N + 1];
        for (int i = 1; i <= N;i++) {
            dots[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            dots[color].add(index);
        }
    }
}



=============before refactoring===============



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
