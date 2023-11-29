import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static int[][] matrix;
    static boolean[][] melted;
    static int[][] hole;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void findHole(int x, int y) {                                              // dfs로 구멍 찾기
        Stack<Node> stack = new Stack<>();
        List<Node> list = new LinkedList<>();
        boolean[][] visited = new boolean[c][r];
        visited[x][y] = true;
        list.add(new Node(x, y));
        stack.add(new Node(x, y));

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<1 || nx>c-1 || ny<1 || ny>r-1) return;
                if(visited[nx][ny] || matrix[nx][ny] == 1) continue;
                stack.add(new Node(nx, ny));
                list.add(new Node(nx, ny));
                visited[nx][ny] = true;
                hole[nx][ny] = 1;
            }
        }

        for (Node cur : list) {
            hole[cur.x][cur.y] = 2;
        }
    }

    static boolean isMelted(int x, int y) {                                        // 이번 턴에 녹는 치즈인지 확인
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(matrix[nx][ny] == 0 && hole[nx][ny]!=2) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        int count = 0, time = 0;
        boolean isMeltedDown;
        matrix = new int[c][r];
        for(int i=0;i<c;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<r;j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            melted = new boolean[c][r];
            hole = new int[c][r];
            isMeltedDown = true;
            time++;

            for (int i = 1; i < c - 1; i++) {
                for (int j = 1; j < r - 1; j++) {
                    if (matrix[i][j] == 0 && hole[i][j]==0) findHole(i, j);
                }
            }

            for (int i = 1; i < c - 1; i++) {
                for (int j = 1; j < r - 1; j++) {
                    if (matrix[i][j] == 1) {
                        if (isMelted(i, j)) melted[i][j] = true;
                        else isMeltedDown = false;
                    }
                }
            }

            if (isMeltedDown) {                                                      // 이번 턴에 치즈가 다 녹으면
                for (int i = 0; i < c; i++) {                                        // 남은 개수 세고 break
                    for (int j = 0; j < r; j++) {
                        if (matrix[i][j] == 1) count++;
                    }
                }
                break;
            }

            for (int i = 1; i < c - 1; i++) {
                for (int j = 1; j < r - 1; j++) {
                    if (melted[i][j]) matrix[i][j] = 0;
                }
            }
        }

        bw.write(time + "\n");
        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
