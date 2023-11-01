import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[] dirX = {-2, -2, 0, 0, 2, 2};
    static int[] dirY = {-1, +1, -2, +2, -1, +1};
    static int n, r1, c1, r2, c2;
    
    static int[][] visited;static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r1, c1));
        visited[r1][c1] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;

            for (int i = 0; i < 6; i++) {
                int nx = cx + dirX[i];
                int ny = cy + dirY[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = visited[cx][cy] + 1;
                }
                if (visited[r2][c2] != 0) return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        visited = new int[n][n];
        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        bfs();

        bw.write(String.valueOf(visited[r2][c2]-1));

        bw.flush();
        bw.close();
        br.close();
    }
}
