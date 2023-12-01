import java.io.*;
import java.util.*;

public class Main {

    static int r, c, max=0;
    static int[][] matrix;
    static List<Node> virusList = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void spread() {                                              // bfs로 바이러스 퍼트리고
        Queue<Node> q = new LinkedList<>();                             // 안전구역 개수 세기
        boolean[][] visited = new boolean[r][c];
        int count = 0;
        for(Node node : virusList) {
            int x = node.x;
            int y = node.y;
            q.add(new Node(x, y));
            visited[x][y] = true;
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || nx>=r || ny<0 || ny>=c || visited[nx][ny] || matrix[nx][ny]!=0) continue;
                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(matrix[i][j]==0 && !visited[i][j]) count++;
            }
        }
        max = Math.max(max, count);
    }

    static void buildWall(int count) {                              // 벽이 3개가 되는 모든 경우의 수 체크
        if(count == 3){
            spread();
            return;
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(matrix[i][j]==0){
                    matrix[i][j] = 1;
                    buildWall(count+1);
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 2) virusList.add(new Node(i, j));
            }
        }

        buildWall(0);

        bw.write(max+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
