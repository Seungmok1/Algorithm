import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] memo = new int[k+1];
        memo[0]++;

        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            for(int j=coin;j<=k;j++) {
                memo[j] += memo[j-coin];
            }
        }
        System.out.println(memo[k]);
    }
}
