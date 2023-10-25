import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;
    Node prev;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Node(int x, int y, Node prev){
        this(x, y);
        this.prev = prev;
    }
}
public class Main {
    static int n, m;
    static char[][] map;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static boolean isCycle(int x, int y, char color) {
        Stack<Node> stack = new Stack<>();
        boolean[][] visited = new boolean[n][m];
        stack.add(new Node(x, y));
        visited[x][y] = true;
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            int prevX = cur.prev != null ? cur.prev.x : cur.x;
            int prevY = cur.prev != null ? cur.prev.y : cur.y;
            for(int i=0;i<4;i++) {
                int nx = cur.x+dirX[i];
                int ny = cur.y+dirY[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny]==color && (nx!=prevX || ny!=prevY)){
                    if(visited[nx][ny]) return true;
//                    System.out.println(nx+" "+ny);
                    stack.add(new Node(nx, ny, cur));
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i=0;i<n;i++) {
            String temp = br.readLine();
            for(int j=0;j<m;j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(isCycle(i, j, map[i][j])){
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }
}
