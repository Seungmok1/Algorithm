import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0;
        int maxH = 0;
        int select = -1;
        
        for(int i=0;i<sizes.length;i++) {
            if(maxW < sizes[i][0]) {
                maxW = sizes[i][0];
                select = i;
            }
            if(maxW < sizes[i][1]) {
                maxW = sizes[i][1];
                select = i;
            }
        }
        
        for(int i=0;i<sizes.length;i++) {
            maxH = Math.max(maxH, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        return maxW * maxH;
    }
}
