package getPrice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Coin {
	float[] bollingerValue = new float[3]; //볼린저 밴드 상단, 중단, 하단
	float rsi;
	int count;

	float[] price;
	String marketCode;

	public Coin(String marketCode,int count) { //가져올 종목코드와 5분봉의 개수
		price = new float[count];
		this.marketCode = marketCode;
		this.count = count;
		setPrice();
		setRsi();
		//setBollingerValue();

	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float[] getBollingerValue() {
		
		return bollingerValue;
	}

	public void setBollingerValue() {
		int countSum = 0;
		float avg = 0.f;
		float sum = 0;
		float highValue, lowValue;
		float devSum = 0; // 분산
		
		for(int i = 0; i < price.length; i++ ) {
			sum += price[i];
					}
		avg = sum / price.length;
		for(int i = 0; i < price.length-1; i++ ) {
			countSum += i;
			// 현재 변화량을 더 크게 쳐서 계산
			devSum += Math.pow((price[i] - price[i+1]),2)*(price.length-1-i);
		}
		devSum /= countSum;  // 최종 분산(19개의 평균)
		devSum = (float) Math.sqrt(devSum); //표준편차
		highValue = avg + devSum*2.5f;
		lowValue = avg - devSum*2.5f;
		bollingerValue[0] = highValue;
		bollingerValue[1] = avg;
		bollingerValue[2] = lowValue;
	}

	public float getRsi() {
		return rsi;
	}

	public void setRsi() {
		float sum = 0f;
		float upAverage = 0, downAverage = 0;
		List<Float> up = new ArrayList<Float>();
		List<Float> down = new ArrayList<Float>();
		int count = 15; //14개의 차이값으로  Rsi값 설정
		float variable;

		for (int i = 0; i < count; i++) {  
			variable = price[i] - price[i+1];
			variable *= count-i;
			if (variable > 0)
				up.add(variable);
			else
				down.add(variable);
			sum += i;
		}
		
		for(int i = 0; i < up.size();i++) {
			upAverage += up.get(i);
		}
		upAverage=upAverage/sum;
		
		for(int i = 0; i < down.size();i++) {
			downAverage += down.get(i);
		}
		downAverage = -downAverage/sum;
		
		rsi = (int) upAverage/(upAverage+downAverage)*100;
	}

	public float[] getPrice() {
		return price;
	}

	public void setPrice() {
		char char1 = '"';
		
		String candleUrl = "https://api.upbit.com/v1/candles/minutes/5?market=";
		URL url = null;
		try {
			url = new URL(candleUrl + marketCode + "&count=" + count);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		InputStream in = null;
		try {
			in = url.openStream();
			byte[] buffer = new byte[128];
			int readCount = 0;
			StringBuilder result = new StringBuilder();
			while ((readCount = in.read(buffer)) != -1) {
				String part = new String(buffer, 0, readCount);
				result.append(part);
			}
			String resultString = result.toString();
			String splitStr = null;
			for(int i = 0; i < count; i++) {
				splitStr = resultString.split(char1+"trade_price" + char1 + ":")[i+1];
				splitStr = splitStr.split("," + char1 + "timestamp")[0];
				price[i] = Float.parseFloat(splitStr);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

}
