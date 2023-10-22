import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ListGraph {
    private ArrayList<ArrayList<Integer>> listGraph;
    public ListGraph(int size) {
        this.listGraph = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<size;i++) {
            listGraph.add(new ArrayList<>());
        }
    }

    public void put (int x, int y) {
        listGraph.get(x).add(y);
        listGraph.get(y).add(x);
    }

    public boolean dfs (int el, boolean[] visited, int count) {
        if(count == 5) return true;
        visited[el] = true;
        for(int i : listGraph.get(el)){
            if(!visited[i]) {
                if(dfs(i, visited, count+1)) return true;
            }
        }
        visited[el] = false;
        return false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ListGraph listGraph = new ListGraph(n);

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            listGraph.put(x, y);
        }

        boolean[] visited = new boolean[n];
        int count = 1;
        for(int i=0;i<n;i++) {
            if(listGraph.dfs(i, visited, count)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
