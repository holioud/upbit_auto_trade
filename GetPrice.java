package upbit;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] marketCode = new String[] { "KRW-BTC", "KRW-ETH", "KRW-EOS", "KRW-XRP" };
		URL url = null;
		String urlString = "https://api.upbit.com/v1/trades/ticks?market=";

		char char1 = '"';
		char char2 = ':';
		while (true) {
			for (int i = 0; i < marketCode.length; i++) {
				String str = String.format(urlString + "%s", marketCode[i]);

				try {
					url = new URL(str);
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
					resultString = resultString.split("trade_price" + char1 + char2)[1];
					resultString = resultString.split(",")[0];
					float price = Float.parseFloat(resultString);

					System.out.println(marketCode[i] + " : " + (int) price);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("***************");
		}
	}

}
