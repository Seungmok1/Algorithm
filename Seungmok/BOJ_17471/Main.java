import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int min=Integer.MAX_VALUE;
    static int[] population;
    static boolean[] visited;
    static boolean[] isSelected;
    static ArrayList<ArrayList<Integer>> al = new ArrayList<>();

    static void subset(int idx) {                                       // 부분집합 만들기
        if(idx == n+1) {
            ArrayList<Integer> red = new ArrayList<>();
            ArrayList<Integer> blue = new ArrayList<>();
            for(int i=1;i<=n;i++) {
                if (isSelected[i]) red.add(i);
                else blue.add(i);
            }
            if(red.size()<n && blue.size()<n && bfs(red) && bfs(blue)){
                int dif = 0;
                for(int i=1;i<=n;i++) {
                    if(red.contains(i)) dif += population[i];
                    else dif -= population[i];
                }
                min = Math.min(min, Math.abs(dif));
            }
            return;
        }
        isSelected[idx] = true;
        subset(idx+1);

        isSelected[idx] = false;
        subset(idx+1);
    }

    static boolean bfs(ArrayList<Integer> party) {      // 연결이 되는지 체크
        Queue<Integer> q = new LinkedList<>();
        q.add(party.get(0));
        visited = new boolean[n+1];
        visited[party.get(0)] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0;i<al.get(cur).size();i++) {
                int next = al.get(cur).get(i);
                if(party.contains(next) && !visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        for (Integer i : party) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        population = new int[n+1];
        isSelected = new boolean[n+1];

        for(int i=0;i<=n;i++) {
            al.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            int range = Integer.parseInt(st.nextToken());
            for(int j=0;j<range;j++) {
                al.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        subset(1);

        if(min == Integer.MAX_VALUE) min = -1;

        bw.write(min+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
