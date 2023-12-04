import java.io.*;
import java.util.*;

public class Main {

    static int max=0;
    static int[] turn = new int[10];
    static Horse[] horses = {new Horse(), new Horse(), new Horse(), new Horse()};
    static int[][] board = {
            {1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
            {10, 13, 16, 19, 25},
            {20, 22, 24, 25},
            {30, 28, 27, 26, 25},
            {25, 30, 35, 40},
            {40, 0}
    };

    static class Horse{
        int line = 0, position = 0;
    }

    static void move(Horse horse, int round) {
        int rest =  turn[round];

        while(true) {
            int[] myLine = board[horse.line];
            if (horse.position + rest > myLine.length-1) {
                rest = rest - (myLine.length-1 - horse.position);
                horse.position = myLine.length-1;
            } else {
                horse.position += rest;
                rest = 0;
            }

            if(horse.position%5==0 && horse.position<20){
                if(horse.position==5) horse.line=1;
                if(horse.position==10) horse.line=2;
                if(horse.position==15) horse.line=3;
                horse.position=0;
            }

            if (myLine[horse.position] == 25) horse.line = 4;
            else if (myLine[horse.position] == 40) horse.line = 5;
            else return;
            horse.position = 0;

            if(rest==0) return;
        }
    }

    static boolean check(int idx) {
        int line = horses[idx].line;
        int position = horses[idx].position;
        for (int i = 0; i < 4; i++) {
            if(i==idx || board[horses[i].line][horses[i].position]==0)continue;
            if(horses[i].line==line && horses[i].position==position) return false;
        }
        return true;
    }

    static void play(int round, int score) {
        if(round == 10){
            max = Math.max(max, score);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Horse horse = horses[i];
            if(board[horse.line][horse.position] == 0) continue;

            int line = horse.line;
            int position = horses[i].position;

            move(horse, round);

            if(check(i)) {
                score += board[horse.line][horse.position];
                play(round + 1, score);
                score -= board[horse.line][horse.position];
            }

            horse.line = line;
            horse.position = position;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            turn[i] = Integer.parseInt(st.nextToken());
        }

        play(0, 0);

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
