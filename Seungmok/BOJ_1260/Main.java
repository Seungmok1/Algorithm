import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int N, M, V;
    static int[][] matrix;
    static boolean[] visited;

    static void dfs(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;
        System.out.print(v + " ");
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            if (matrix[i][0] == v && !visited[matrix[i][1]]) {
                answer.add(matrix[i][1]);
            } else if (matrix[i][1] == v && !visited[matrix[i][0]]) {
                answer.add(matrix[i][0]);
            }
        }

        Collections.sort(answer);
        answer.forEach(i -> dfs(i));
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N+1];
        List<Integer> answer = new ArrayList<>();
        q.add(start);
        visited[start] = true;
        answer.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                if (matrix[i][0] == cur && !visited[matrix[i][1]]) {
                    next.add(matrix[i][1]);
                    visited[matrix[i][1]] = true;
                }
                if (matrix[i][1] == cur && !visited[matrix[i][0]]) {
                    next.add(matrix[i][0]);
                    visited[matrix[i][0]] = true;
                }
            }

            Collections.sort(next);
            next.forEach(num -> {
                q.add(num);
                answer.add(num);
            });
        }

        answer.forEach( i -> System.out.print(i+" "));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        matrix = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N+1];
        dfs(V);
        System.out.println();
        bfs(V);
    }
}
