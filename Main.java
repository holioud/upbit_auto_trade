package getPrice;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> goodCoin = new ArrayList<String>();
		String[] marketCode = {"KRW-DKA","KRW-BTC","KRW-EOS","KRW-XRP","KRW-MED"};
		String[] bandName = {"상","중","하"};
		float[] bollingerValue = new float[3];
		
		for(int i = 0; i < marketCode.length; i++) {
			Coin coin = new Coin(marketCode[i],20);	//마켓코드, 가져올 분봉의 개수 입력
			bollingerValue = coin.getBollingerValue();
			System.out.print(coin.getMarketCode()+" ");
			System.out.printf("Rsi:%.1f\t",coin.getRsi());
			for(int j = 0; j <3; j++) {
				System.out.print(bandName[j]+":");
				System.out.printf("%.1f\t",bollingerValue[j]);
				if(bollingerValue[j] < 1000)
					System.out.print("\t");
			}
			
			if(bollingerValue[2] > coin.getRecentPrice() && coin.getRsi() < 20) // 매매기법
				System.out.print("발견");
			else
				System.out.print("실패");
			System.out.println();
			
			
		}
		
		
		
		
	}

}
