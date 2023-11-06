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
    static boolean[][] matrix = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void drawDragonCurve(int x, int y, int d, int g){
        ArrayList<Node> dragonCurve = new ArrayList<>();
        dragonCurve.add(new Node(x, y));
        dragonCurve.add(new Node(x + dx[d], y + dy[d]));
        for(int i=0;i<g;i++) {
            Node turn = dragonCurve.get(dragonCurve.size()-1);
            ArrayList<Node> temp = new ArrayList<>(dragonCurve);
            for(int j=temp.size()-2;j>=0;j--) {
                Node node = temp.get(j);
                dragonCurve.add(new Node(turn.y - node.y + turn.x, node.x - turn.x + turn.y));    // turn을 기준으로 90도 돌리기
            }
        }
        for(Node node : dragonCurve) {
            int cx = node.x;
            int cy = node.y;
            if(cx<0 || cx>100 || cy<0 || cy>100) continue;
            matrix[cy][cx] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int count=0;
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            drawDragonCurve(
              Integer.parseInt(st.nextToken()),
              Integer.parseInt(st.nextToken()),
              Integer.parseInt(st.nextToken()),
              Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(matrix[j][i] && matrix[j][i+1] && matrix[j+1][i] && matrix[j+1][i+1]) count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
