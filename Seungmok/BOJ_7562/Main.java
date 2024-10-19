import java.io.*;
import java.util.*;

public class Main {

    static int T, I;
    static int[][] visited;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    static class Node{
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            I = Integer.parseInt(br.readLine());
            visited = new int[I][I];
            st = new StringTokenizer(br.readLine());
            Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            Node dest = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bfs(start);
            int answer = visited[dest.x][dest.y] == 0 ? 0 : visited[dest.x][dest.y]-1;
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        visited[start.x][start.y] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
                if (visited[nx][ny] != 0 && visited[cx][cy] + 1 >= visited[nx][ny]) continue;
                visited[nx][ny] = visited[cx][cy] + 1;
                q.add(new Node(nx, ny));
            }
        }
    }
}
