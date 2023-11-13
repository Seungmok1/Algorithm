import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int N,M,K;
    static int[][] A, field;
    static List<Tree> treeList;
    static Queue<Tree> deadTree;

    static class Tree{
        int x, y, age;
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    static void spring() {
        Iterator<Tree> iterator = treeList.iterator();
        while (iterator.hasNext()) {
            Tree tree = iterator.next();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if (field[x][y] < age) {
                deadTree.add(tree);
                iterator.remove();
            } else {
                field[x][y] -= age;
                tree.age++;
            }
        }
    }

    static void summer() {
        while(!deadTree.isEmpty()){
            Tree tree = deadTree.poll();
            field[tree.x][tree.y] += tree.age / 2;
        }
    }

    static void fall() {
        List<Tree> temp = new LinkedList<>();
        for(Tree tree : treeList){
            if(tree.age % 5 != 0) continue;
            for(int i=0;i<8;i++) {
                int nx = tree.x + dx[i];
                int ny = tree.y + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                temp.add(new Tree(nx, ny, 1));
            }
        }
        treeList.addAll(0, temp);
    }

    static void winter() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                field[i][j] += A[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        field = new int[N][N];
        treeList = new LinkedList<>();
        deadTree = new LinkedList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                field[i][j] = 5;
            }
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            treeList.add(new Tree(
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())));
        }

        while(K>0){
            spring();
            summer();
            fall();
            winter();
            K--;
        }

        bw.write(treeList.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
