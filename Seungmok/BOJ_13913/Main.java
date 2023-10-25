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

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        int[] arr = new int[100001];
        arr[n] = 1;
        int[] route = new int[100001];

        while(!q.isEmpty()) {
            int temp = q.poll();
            if (temp-1 >= 0 && arr[temp - 1] == 0) {
                q.offer(temp - 1);
                arr[temp - 1] = arr[temp]+1;
                route[temp-1] = temp;
            }
            if (temp+1 <= 100000 && arr[temp + 1] == 0) {
                q.offer(temp + 1);
                arr[temp + 1] = arr[temp]+1;
                route[temp+1] = temp;
            }
            if (temp*2 <= 100000 && arr[temp * 2] == 0) {
                q.offer(temp * 2);
                arr[temp * 2] = arr[temp]+1;
                route[temp*2] = temp;
            }
            if(arr[k] != 0){
                break;
            }
        }
        System.out.println(arr[k]-1);
        int[] rev = new int[arr[k]];
        rev[0] = k;
        int temp = k;
        for(int i=1;i<arr[k];i++) {
            rev[i] = route[temp];
            temp = route[temp];
        }
        for(int i=arr[k]-1;i>=0;i--) {
            System.out.print(rev[i]+" ");
        }
    }
}
