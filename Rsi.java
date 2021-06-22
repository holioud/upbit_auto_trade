package getPrice;

import java.util.ArrayList;
import java.util.List;

public class Rsi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		float rsi;
		int count = 20;
		//임의의 가격 14개
		int[] price = {10,11,8,5,14,15,16,15,14,15,16,18,22,24};
		float upAverage = 0, downAverage = 0;
		List<Integer> up = new ArrayList<Integer>();
		List<Integer> down = new ArrayList<Integer>();
		
		int variable;

		for (int i = 0; i < count; i++) {
			variable = price[i + 1] - price[i];
			variable *= count-i;
			if (variable > 0)
				up.add(variable);
			else
				down.add(variable);
		}
		System.out.println(up);
		System.out.println(down);
		
		for(int i = 0; i < up.size();i++) {
			upAverage += up.get(i);
		}
		upAverage=upAverage/up.size();
		
		for(int i = 0; i < down.size();i++) {
			downAverage += down.get(i);
		}
		downAverage = -downAverage/down.size();
		
		System.out.println("upAverage="+upAverage);
		System.out.println("downAverage="+downAverage);	
		
		rsi = upAverage/(upAverage+downAverage)*100;
		System.out.println("rsi="+rsi);
	}

}
