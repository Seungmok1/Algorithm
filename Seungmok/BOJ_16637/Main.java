        import java.io.*;
        import java.util.*;

        public class Main {

            static int max = Integer.MIN_VALUE;
            static List<Integer> nums = new ArrayList<>();
            static List<Character> ops = new ArrayList<>();

            private static int cal(int idx, int num1, int num2) {
                switch (ops.get(idx)) {
                    case '+': return num1+num2;
                    case '-': return num1-num2;
                    case '*': return num1*num2;
                }
                return 0;
            }

            private static void dfs(int idx, int sum) {
                if (idx >= ops.size()) {
                    max = Math.max(max, sum);
                    return;
                }
                dfs(idx + 1, cal(idx, sum, nums.get(idx + 1)));

                if (idx + 1 < ops.size()) {
                    int result = cal(idx + 1, nums.get(idx + 1), nums.get(idx + 2));
                    dfs(idx + 2, cal(idx, sum, result));
                }
            }

            public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                int n = Integer.parseInt(br.readLine());
                String input = br.readLine();
                for (int i = 0; i < input.length(); i++) {
                    if (i % 2 == 0) {
                        nums.add(input.charAt(i) - '0');
                    } else {
                        ops.add(input.charAt(i));
                    }
                }

                dfs(0, nums.get(0));

                bw.write(max + "");
                bw.flush();
                bw.close();
                br.close();
            }
        }
