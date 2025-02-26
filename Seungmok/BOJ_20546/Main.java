import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] stock = new int[14];
    static int[] count = new int[2];
    static int[] seed = new int[2];

    static void trade(){
        for (int i = 0; i < 14; i++) {
            if(seed[0] >= stock[i]){
                count[0] = seed[0] / stock[i];
                seed[0] = seed[0] % stock[i];
                break;
            }
        }

        count[1] = 0;

        for (int i = 3; i < 14; i++) {
            if (stock[i-3] < stock[i-2] && stock[i - 2] < stock[i - 1] && stock[i - 1] < stock[i] && count[1] > 0) {
                seed[1] += count[1] * stock[i];
                count[1] = 0;
            }
            if (stock[i-3] > stock[i-2] && stock[i - 2] > stock[i - 1] && stock[i - 1] > stock[i] && seed[1] >= stock[i]) {
                count[1] = count[1] + seed[1] / stock[i];
                seed[1] = seed[1] % stock[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        trade();

        String answer = "";

        if(seed[0] + count[0] * stock[13] > seed[1] + count[1] * stock[13]) answer = "BNP";
        else if(seed[0] + count[0] * stock[13] < seed[1] + count[1] * stock[13]) answer = "TIMING";
        else answer = "SAMESAME";

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        seed[0] = seed[1] = money;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 14; i++) {
            stock[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }
}
