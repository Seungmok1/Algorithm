import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] visited = new int[f+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if(cur+u<=f && visited[cur+u]==0){
                q.add(cur+u);
                visited[cur+u] = visited[cur]+1;
            }
            if (cur-d>0 && visited[cur-d]==0) {
                q.add(cur-d);
                visited[cur-d] = visited[cur]+1;
            }
            if(visited[g] != 0) break;
        }
        if(visited[g] == 0) System.out.println("use the stairs");
        else System.out.println(visited[g]-1);
    }
}
