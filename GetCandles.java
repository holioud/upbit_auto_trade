package getPrice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GetCandles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 5분봉 종가 4개 추출해보기
		char char1 = '"';

		System.out.println("조회할 5분봉의 마켓코드와 개수를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String marketCode = sc.next();	
		int count = sc.nextInt();
//		String marketCode = "KRW-BTC";

		String candleUrl = "https://api.upbit.com/v1/candles/minutes/5?market=";
		URL url = null;
		try {
			// url = new URL(candleUrl+marketCode+"&count="+count);
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
			System.out.println(resultString);
			System.out.println();
			String splitStr = null;
			
			for(int i = 0; i < count; i++) {
				splitStr = resultString.split(char1+"trade_price" + char1 + ":")[i+1];
				splitStr = splitStr.split("," + char1 + "timestamp")[0];
				System.out.println(splitStr);
			}
			
			
			
			

		}

//		System.out.println(candleUrl+marketCode+"&count="+count);
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
