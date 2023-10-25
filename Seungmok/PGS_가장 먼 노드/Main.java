import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        Graph g = new Graph(n);
        for(int i=0;i<edge.length;i++) {
            g.put(edge[i][0], edge[i][1]);
        }
        return g.dijkstra(1, n);
    }
}

class Graph {
    ArrayList<ArrayList<Integer>> graph;
    public Graph(int size) {
        graph = new ArrayList<>();
        for(int i=0;i<=size;i++) {
            graph.add(new ArrayList<>());
        }
    }
    
    public void put(int x, int y) {
        graph.get(x).add(y);
        graph.get(y).add(x);
    }
    
    public int dijkstra(int start, int v) {
        int max = 0, count = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[v+1];
        for(int i=2;i<=v;i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        q.add(start);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i=0;i<graph.get(cur).size();i++) {
                int next = graph.get(cur).get(i);
                if(dist[next] > dist[cur]+1) {
                    dist[next] = dist[cur]+1;
                    q.add(next);
                    max = Math.max(max, dist[next]);
                }
            }
        }
        for(int i=1;i<=v;i++){
            if(max == dist[i]) count++;
        }
        return count;
    }
}
