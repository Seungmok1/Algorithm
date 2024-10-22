import java.util.*;

class Solution {
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] visited;

    static class Node{
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] board) {
        int answer = 0;
        visited = new int[board.length][board[0].length()];
        Node start = new Node(0, 0);
        Node end = new Node(0, 0);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') start = new Node(i, j);
                else if (board[i].charAt(j) == 'G') end = new Node(i, j);
            }
        }
        
        bfs(board, start);
        
        answer = visited[end.x][end.y]-1;
        
        return answer;
    }
    
    static void bfs(String[] board, Node start) {
        Queue<Node> q = new LinkedList<>();
        visited[start.x][start.y] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length()) continue;
                if(board[nx].charAt(ny) == 'D') continue;
                
                Node next = move(board, cx, cy, i);
                
                if (visited[next.x][next.y] != 0 && visited[cx][cy] + 1 >= visited[next.x][next.y]) continue;
                q.add(next);
                visited[next.x][next.y] = visited[cx][cy] + 1;
            }
        }
    }
    
    static Node move(String[] board, int cx, int cy, int d){
        if(cx < 0 || cy < 0 || cx >= board.length || cy >= board[0].length() || board[cx].charAt(cy) == 'D') return new Node(cx-dx[d], cy-dy[d]);
        return move(board, cx + dx[d], cy + dy[d], d);
    }
}
