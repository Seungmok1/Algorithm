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
    static int N, L, R;
    static int[][] A;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Node> list = new ArrayList<>();
    static boolean[][] visited;

    static int bfs(int x, int y) {          // 인구 차이가 범위안에 있는지 완전탐색
        int sum = A[x][y];
        Queue<Node> q = new LinkedList<>();
        list = new ArrayList<>();
        q.add(new Node(x, y));
        list.add(new Node(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
                    int dif = Math.abs(A[cx][cy] - A[nx][ny]);
                    if(dif>=L && dif<=R) {
                        q.add(new Node(nx, ny));
                        list.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        sum += A[nx][ny];
                    }
                }
            }
        }
        return sum;
    }

    static void calPopulation(int sum) {          // 인구 계산
        int population = sum / list.size();
        for (Node cur : list) {
            A[cur.x][cur.y] = population;
        }
    }

    static int move() {                            // 완전탐색 + 인구계산하고 했으면 date++, 아니면 리턴
        int date = 0;
        while(true){
            boolean isMoved = false;
            visited = new boolean[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++){
                    if(!visited[i][j]){
                        int sum = bfs(i, j);
                        if(list.size() > 1){
                            calPopulation(sum);
                            isMoved = true;
                        }
                    }
                }
            }
            if(!isMoved) return date;
            date++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(move() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
