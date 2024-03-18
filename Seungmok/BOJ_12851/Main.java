        import java.io.*;
        import java.util.*;

        public class Main {

            static int subin;
            static int sister;
            static int[] visited = new int[100001];
            static Queue<Integer> q = new LinkedList<>();
            static int count = 1;

            static void move(int next, int cur) {
                if (next == sister) {
                    if (visited[sister] == visited[cur] + 1) {
                        count++;
                    } else if (visited[sister] == 0 || visited[sister] > visited[cur] + 1) {
                        count = 1;
                    }
                }
                visited[next] = visited[cur] + 1;
                q.add(next);
            }

            static void bfs() {
                q.add(subin);
                visited[subin] = 1;

                while (!q.isEmpty()) {
                    int cur = q.poll();
                    if (cur - 1 >= 0 && (visited[cur-1]==0 ||  visited[cur-1] >= visited[cur]+1)) {
                        move(cur - 1, cur);
                    }
                    if (cur + 1 <= 100000 && (visited[cur+1]==0 ||  visited[cur+1] >= visited[cur]+1)) {
                        move(cur + 1, cur);
                    }
                    if (cur * 2 <= 100000 && (visited[cur*2]==0 ||  visited[cur*2] >= visited[cur]+1)) {
                        move(cur * 2, cur);
                    }
                }
            }

            public static void main(String[] args) throws IOException {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st = new StringTokenizer(br.readLine());
                subin = Integer.parseInt(st.nextToken());
                sister = Integer.parseInt(st.nextToken());

                bfs();

                bw.write(visited[sister]-1+"\n"+count);
                bw.flush();
                bw.close();
                br.close();
            }
        }
