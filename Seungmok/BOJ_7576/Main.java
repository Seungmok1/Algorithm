import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] storage = new int[n][m];
        int[][] visited = new int[n][m];
        Queue<Node> q = new LinkedList<>();
        int max = 0;

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                if(storage[i][j] == 1) {
                    Node node = new Node(j, i, 0);
                    q.add(node);
                }
            }
        }

        while(!q.isEmpty()) {
            Node temp = q.poll();
            int x = temp.x;
            int y = temp.y;
            int date = temp.date;
            for(int i=0;i<4;i++) {
                if(x+dirX[i]>=0 && x+dirX[i]<m && y+dirY[i]>=0 && y+dirY[i]<n) {
                    if(storage[y][x+dirX[i]] == 0) {
                        q.add(new Node(x+dirX[i], y, date+1));
                        storage[y][x+dirX[i]] = 1;
                    }
                    if(storage[y+dirY[i]][x] == 0) {
                        q.add(new Node(x, y+dirY[i], date+1));
                        storage[y+dirY[i]][x] = 1;
                    }
                }
            }
            max = Math.max(max, date);
        }

        for(int[] i : storage) {
            for(int j : i) {
                if (j == 0) {
                    max = -1;
                    break;
                }
            }
        }

        System.out.println(max);
    }
}

class Node {
    int x;
    int y;
    int date;
    Node (int x, int y, int date) {
        this.x = x;
        this.y = y;
        this.date = date;
    }
}
