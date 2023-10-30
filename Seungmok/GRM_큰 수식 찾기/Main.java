import java.io.*;
import java.util.*;

class Main {
	static List<Long> num;
	static List<Character> cal;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.print(Math.max(math(st.nextToken()), math(st.nextToken())));
	}
	
	static Long math(String expre) {
		num = new ArrayList<>();
		cal = new ArrayList<>();
		StringBuilder temp = new StringBuilder();
		for(int i=0;i<expre.length();i++) {
			char cur = expre.charAt(i);
			if(cur>='0' && cur<='9') {
				temp.append(cur);
			} else {
				num.add(Long.parseLong(temp.toString()));
				temp = new StringBuilder();
				cal.add(cur);
			}
		}
		num.add(Long.parseLong(temp.toString()));
		return calculate();
	}
	
	static Long calculate() {
		while(!cal.isEmpty()){
			if(cal.contains('*')){
				int idx = cal.indexOf('*');
				num.set(idx, num.get(idx) * num.remove(idx+1));
				cal.remove(idx);
			} else if(cal.get(0)=='+') {
				num.set(0, num.get(0) + num.remove(1));
				cal.remove(0);
			} else {
				num.set(0, num.get(0) - num.remove(1));
				cal.remove(0);
			}
		}
		return num.get(0);
	}
}
