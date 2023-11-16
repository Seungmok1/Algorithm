import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, time;
    static int[][] field;
    static int[][] visited;
    static List<Node> fishes = new LinkedList<>();
    static Node shark;

    static class Node {
        int x, y, size, count;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int size) {
            this(x, y);
            this.size = size;
        }
    }

    static Node findFish() {
        List<Node> adjFishList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(Node fish : fishes){                                          // 상어보다 작고 최단거리에 있는 물고기들을 구함
            if(fish.size >= shark.size) continue;
            int dif = visited[fish.x][fish.y] - 1;
            if(dif==-1) continue;
            if(min > dif){
                min = dif;
                adjFishList.clear();
                adjFishList.add(fish);
            } else if (min == dif){
                adjFishList.add(fish);
            }
        }
        if(adjFishList.size() == 1){                                       // 1마리라면 바로 리턴
            return adjFishList.get(0);
        } else if(adjFishList.size() > 1) {                                // 1보다 크면 제일 위에 있는 것을 탐색
            List<Node> top = new LinkedList<>();
            int minX = N;
            for(Node fish : adjFishList){
                if(minX > fish.x){
                    minX = fish.x;
                    top.clear();
                    top.add(fish);
                } else if(minX == fish.x){
                    top.add(fish);
                }
            }
            if(top.size() == 1){
                return top.get(0);
            }
            Node fish = top.get(0);
            for(int i=1;i<top.size();i++){                            // 제일 위에 있는 것이 여러 개라면
                if(fish.y > top.get(i).y){                            // 제일 왼쪽에 있는 것을 반환
                    fish = top.get(i);
                }
            }
            return fish;
        } else {
            return new Node(-1, 0, 0);                               // 먹을 수 있는 물고기가 없다면 -1을 리턴
        }
    }

    static void bfs() {                                              // bfs로 물고기까지 최단거리구하기
        Queue<Node> q = new LinkedList<>();    
        visited = new int[N][N];
        q.add(shark);
        visited[shark.x][shark.y] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]>0 || field[nx][ny]>shark.size) continue;
                q.add(new Node(nx, ny));
                visited[nx][ny] = visited[cx][cy]+1;
            }
        }
    }

    static void eat(Node fish) {
        field[fish.x][fish.y] = 9;
        field[shark.x][shark.y] = 0;
        shark.x = fish.x;
        shark.y = fish.y;
        fishes.remove(fish);
        shark.count++;
        if(shark.count == shark.size) {
            shark.size++;
            shark.count=0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if(field[i][j]>0 && field[i][j]<9){
                    fishes.add(new Node(i, j, field[i][j]));
                } else if (field[i][j] == 9){
                    shark = new Node(i, j, 2);
                    shark.count = 0;
                }
            }
        }

        while(true) {
            bfs();
            Node fish = findFish();
            if(fish.x==-1) break;
            int t = visited[fish.x][fish.y] - 1;
            if(t==0) continue;
            eat(fish);
            time += t;
        }

        bw.write(time + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
