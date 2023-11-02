import java.io.*;
import java.util.*;

class Node {
    int x, y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[][] groupId;
    static ArrayList<Integer> groupSize;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    static void bfs(int x, int y, int id) {
        Queue<Node> q = new LinkedList<>();
        int size = 1;

        q.add(new Node(x, y));
        groupId[x][y] = id;

        while(!q.isEmpty()) {
            Node cur = q. poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++) {
                int nx = cx + dirX[i];
                int ny = cy + dirY[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && groupId[nx][ny]==0 && matrix[nx][ny]==0) {
                    q.add(new Node(nx, ny));
                    groupId[nx][ny] = id;
                    size++;
                }
            }
        }
        groupSize.add(size);
    }

    static int countSize(int x, int y) {
        ArrayList<Integer> recordId = new ArrayList<>();
        int size = 1;
        for(int i=0;i<4;i++) {
            int nx = x + dirX[i];
            int ny = y + dirY[i];
            if(nx>=0 && nx<n && ny>=0 && ny<m && !recordId.contains(groupId[nx][ny]) && matrix[nx][ny]==0) {
                int id = groupId[nx][ny];
                recordId.add(id);
                size += groupSize.get(id-1);
            }
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        groupId = new int[n][m];
        groupSize = new ArrayList<>();

        for(int i=0;i<n;i++) {
            String temp = br.readLine();
            for(int j=0;j<m;j++) {
                matrix[i][j] = temp.charAt(j) - '0';
            }
        }

        int id=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]==0 && groupId[i][j] == 0) {
                    bfs(i, j, ++id);
                }
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                bw.write(String.valueOf(matrix[i][j]==1 ? countSize(i, j)%10 : 0));
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
