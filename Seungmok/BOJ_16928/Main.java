import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(a, b);
        }
        bw.write(bfs() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs() {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{1, 0});

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            for (int i = 1; i <= 6; i++) {
                int next = cur[0] + i;
                if (next > 100) continue;
                if (map.containsKey(next)) {
                    q.add(new Integer[]{map.get(next), cur[1] + 1});
                    visited[map.get(next)] = true;
                } else if (!visited[next]) {
                    q.add(new Integer[]{next, cur[1] + 1});
                    visited[next] = true;
                }
                if (visited[100]) {
                    return cur[1] + 1;
                }
            }
        }
        return -1;
    }
}
