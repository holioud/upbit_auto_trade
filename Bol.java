package getPrice;

public class Bol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coin coin = new Coin("KRW-CHZ",20);
		int countSum = 0;
		float avg = 0.f;
		float sum = 0;
		float devSum = 0; // 분산
		float highValue, lowValue; //볼린저 밴드 상단과 하단 정의
		float[] price = coin.getPrice();
		
		for(int i = 0; i < price.length; i++ ) {
			sum += price[i];
					}
		avg = sum / price.length;
		System.out.println(avg);
		for(int i = 0; i < price.length-1; i++ ) {
			System.out.print(price[i]-price[i+1]+"\t");
			countSum += i;
			// 현재 변화량을 더 크게 쳐서 계산
			devSum += Math.pow((price[i] - price[i+1]),2)*(price.length-1-i);
		}
		System.out.println();
		devSum /= countSum;  // 최종 분산(19개의 평균)
		devSum = (float) Math.sqrt(devSum); //표준편차
		highValue = avg + devSum*2.5f;
		lowValue = avg - devSum*2.5f;
		
		System.out.println("상단:"+highValue);
		System.out.println("중단:"+avg);
		System.out.println("하단:"+lowValue);
		
		
		
	}

}
