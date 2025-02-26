import java.io.*;

public class Main {

    static int N, answer=1;
    static char[][] board;
    static int[] mx = {1, -1, 0, 0};
    static int[] my = {0, 0, 1, -1};

    static void change() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + mx[k];
                    int ny = j + my[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    char temp = board[i][j];
                    board[i][j] = board[nx][ny];
                    board[nx][ny] = temp;
                    findMax();
                    temp = board[i][j];
                    board[i][j] = board[nx][ny];
                    board[nx][ny] = temp;
                }
            }
        }
    }

    static void findMax() {
        for (int i = 0; i < N; i++) {
            int r = 1, c = 1;
            for (int j = 0; j < N - 1; j++) {
                if(board[i][j] == board[i][j+1]) {
                    r++;
                    answer = Math.max(answer, r);
                } else r = 1;

                if(board[j][i] == board[j+1][i]){
                    c++;
                    answer = Math.max(answer, c);
                } else c = 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        findMax();

        change();

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        br.close();
    }
}
