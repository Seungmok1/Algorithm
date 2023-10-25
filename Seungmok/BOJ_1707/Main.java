import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> al;
    static int[] visited;
    static boolean bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        visited[root] = 1;
        while(!q.isEmpty()){
            int temp = q.poll();
            for(int v : al.get(temp)) {
                if(visited[v] == 0) {
                    q.add(v);
                    visited[v] = visited[temp] * -1;
                } else if (visited[v] == visited[temp]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        while(tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            visited = new int[v+1];
            al = new ArrayList<>();
            for(int i=0;i<=v;i++) {
                al.add(new ArrayList<>());
            }
            for(int i=0;i<e;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                al.get(x).add(y);
                al.get(y).add(x);
            }
            boolean check = true;
            for(int i=1;i<=v;i++) {
                if (visited[i] == 0) if (!bfs(i)) {
                    check = false;
                    break;
                }
            }
            System.out.println(check ? "YES" : "NO");
        }
    }
}
