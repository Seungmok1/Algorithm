import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[101];
        boolean[] visited = new boolean[101];
        Map<Integer, Integer> ladder = new HashMap<>();
        Map<Integer, Integer> snake = new HashMap<>();
        Queue<Integer[]> q = new LinkedList<>();
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder.put(a, b);
        }
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snake.put(a, b);
        }

        q.add(new Integer[] {1, 0});
        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            for(int i=1;i<=6;i++) {
                int next = cur[0] + i;
                int fin;
                if(next>100) continue;
                if(snake.containsKey(next)){
                    q.add(new Integer[] {snake.get(next), cur[1]+1});
                    visited[snake.get(next)] = true;
                    fin = snake.get(next);
                } else if (ladder.containsKey(next)) {
                    q.add(new Integer[] {ladder.get(next), cur[1]+1});
                    visited[ladder.get(next)] = true;
                } else if(!visited[next]){
                    q.add(new Integer[]{next, cur[1]+1});
                    visited[next] = true;
                }
                if(visited[100]) {
                    System.out.println(cur[1]+1);
                    return;
                }
            }
        }
    }
}
