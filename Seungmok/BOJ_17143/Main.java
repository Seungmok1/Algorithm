import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M, answer=0;
    static List<Shark> sharkList = new LinkedList<>();

    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static void move() {                                          // 상어 이동
        Shark[][] matrix = new Shark[R+1][C+1];
        List<Shark> sharks = new LinkedList<>();
        for(Shark shark : sharkList){
            int distance = shark.s;
            int line = shark.d<=2 ? R : C;
            if(distance > line) distance %= 2*(line-1);

            while(shark.d<=2) {
                if (shark.d == 1) {
                    if (shark.r - 1 < distance) {
                        distance -= shark.r - 1;
                        shark.r = 1;
                        shark.d = 2;
                    } else {
                        shark.r -= distance;
                        break;
                    }
                }
                if (shark.d == 2) {
                    if (R - shark.r < distance) {
                        distance -= R - shark.r;
                        shark.r = R;
                        shark.d = 1;
                    } else {
                        shark.r += distance;
                        break;
                    }
                }
            }
            while(shark.d>=3) {
                if (shark.d==3) {
                    if(C-shark.c<distance){
                        distance -= C-shark.c;
                        shark.c=C;
                        shark.d=4;
                    } else {
                        shark.c += distance;
                        break;
                    }
                }
                if (shark.d==4) {
                    if(shark.c-1<distance){
                        distance -= shark.c-1;
                        shark.c=1;
                        shark.d=3;
                    } else {
                        shark.c -= distance;
                        break;
                    }
                }
            }

            if(matrix[shark.r][shark.c]!=null){                      // 이동할 자리에 이미 이동한 상어가 있으면 비교해서 작은 상어 삭제
                Shark ori = matrix[shark.r][shark.c];
                if(ori.z > shark.z){
                    sharks.add(shark);
                } else {
                    sharks.add(ori);
                    matrix[shark.r][shark.c] = shark;
                }
            } else {
                matrix[shark.r][shark.c] = shark;
            }
        }

        for (Shark shark : sharks) {
            sharkList.remove(shark);
        }
    }

    static void fish(int idx) {                            // 같은 열의 가장 위에 있는 물고기 잡기
        sharkList.stream()
                .filter(shark -> shark.c == idx)
                .min(Comparator.comparing(shark -> shark.r))
                .ifPresent(shark -> {
                    answer += shark.z;
                    sharkList.remove(shark);
                });
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sharkList.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 1; i <= C; i++) {
            fish(i);
            move();
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
