        import java.io.*;
        import java.util.*;

        public class Main {

            static int R, C;
            static char[][] map;
            static Node jihoon;
            static int[][] fire;
            static int[][] visited;
            static int[] dx = {1, -1, 0, 0};
            static int[] dy = {0, 0, 1, -1};

            static class Node {
                int x,y;

                public Node(int x, int y) {
                    this.x = x;
                    this.y = y;
                }
            }

            public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                StringTokenizer st = new StringTokenizer(br.readLine());
                Queue<Node> q = new LinkedList<>();
                R = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                visited = new int[R][C];
                map = new char[R][C];
                fire = new int[R][C];

                for (int i = 0; i < R; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < C; j++) {
                        map[i][j] = str.charAt(j);
                        if(map[i][j] == 'J') jihoon = new Node(i, j);
                        else if(map[i][j] == 'F') {
                            fire[i][j] = 1;
                            q.add(new Node(i, j));
                        }
                    }
                }

                while(!q.isEmpty()) {
                    Node cur = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = cur.x + dx[i];
                        int ny = cur.y + dy[i];
                        if(nx<0 || nx>=R || ny<0 || ny>=C || fire[nx][ny]>0 || map[nx][ny]=='#') continue;
                        q.add(new Node(nx, ny));
                        fire[nx][ny] = fire[cur.x][cur.y] + 1;
                    }
                }

                q.add(new Node(jihoon.x, jihoon.y));
                visited[jihoon.x][jihoon.y] = 1;
                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    int cx = cur.x;
                    int cy = cur.y;
                    for (int i = 0; i < 4; i++) {
                        int nx = cx + dx[i];
                        int ny = cy + dy[i];
                        if(nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny]>0 || (visited[cx][cy]+1>=fire[nx][ny] && fire[nx][ny]!=0) || map[nx][ny]!='.') continue;
                        q.add(new Node(nx, ny));
                        visited[nx][ny] = visited[cx][cy] + 1;
                    }
                }

                int min = R + C;

                for (int i = 0; i < C; i++) {
                    if(visited[0][i]>0) min = Math.min(min, visited[0][i]);
                    if(visited[R-1][i]>0) min = Math.min(min, visited[R-1][i]);
                }
                for (int i = 0; i < R; i++) {
                    if(visited[i][0]>0) min = Math.min(min, visited[i][0]);
                    if(visited[i][C-1]>0) min = Math.min(min, visited[i][C-1]);
                }
                if(min == R + C) bw.write("IMPOSSIBLE");
                else bw.write(min + "");

                bw.flush();
                bw.close();
                br.close();
            }
        }
