import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;
    boolean power;
    public Node(int x, int y, boolean power) {
        this.x = x;
        this.y = y;
        this.power = power;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int [n][m];
        int[][][] visited = new int[n][m][2];
        Queue<Node> q = new LinkedList<>();
        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};
        for(int i=0;i<n;i++) {
            String temp = br.readLine();
            for(int j=0;j<m;j++) {
                arr[i][j] = temp.charAt(j) - '0';
            }
        }

        q.add(new Node(0, 0, true));
        visited[0][0][0] = 1;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i=0;i<4;i++) {
                int nx = cur.x + dirX[i];
                int ny = cur.y + dirY[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && ((cur.power && visited[nx][ny][0]==0) || (!cur.power && visited[nx][ny][1]==0))) {
                    if(arr[nx][ny]==0 && cur.power){
                        q.add(new Node(nx, ny, cur.power));
                        visited[nx][ny][0] = visited[cur.x][cur.y][0]+1;
                    } else if (arr[nx][ny]==0 && !cur.power) {
                        q.add(new Node(nx, ny, cur.power));
                        visited[nx][ny][1] = visited[cur.x][cur.y][1]+1;
                    } else if (arr[nx][ny]==1 && cur.power){
                        q.add(new Node(nx, ny, false));
                        visited[nx][ny][1] = visited[cur.x][cur.y][0]+1;
                    }
                }
            }
        }
        if(visited[n-1][m-1][0]==0 && visited[n-1][m-1][1]==0) System.out.println(-1);
        else if (visited[n-1][m-1][1]==0) System.out.println(visited[n-1][m-1][0]);
        else System.out.println(visited[n-1][m-1][1]);
    }
}
