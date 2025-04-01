import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        
        String[] strArr = new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            strArr[i] = numbers[i] + "";
        }
        
        Arrays.sort(strArr, (a,b) -> (b + a).compareTo(a + b));
        
        if (strArr[0].equals("0")) return "0";
        
        StringBuilder result = new StringBuilder();
        for(String str : strArr) {
            result.append(str);
        }
        
        return result.toString();
    }
}

// https://school.programmers.co.kr/learn/courses/30/lessons/42746
