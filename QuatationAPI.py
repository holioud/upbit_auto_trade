import requests

#마켓코드
marketCodeUrl = "https://api.upbit.com/v1/market/all"

querystring = {"isDetails":"false"}

headers = {"Accept": "application/json"}

response = requests.request("GET", marketCodeUrl, headers=headers, params=querystring)

print("마켓코드 : " + response.text)

#1분봉
minuteCandleUrl = "https://api.upbit.com/v1/candles/minutes/1"

querystring = {"market":"KRW-ADA","count":"2"}

headers = {"Accept": "application/json"}

response = requests.request("GET", minuteCandleUrl, headers=headers, params=querystring)

print("1분봉 : " + response.text)

#현재가
priceUrl = "https://api.upbit.com/v1/ticker"

querystring = {"markets": "KRW-BTC"}

headers = {"Accept": "application/json"}
'''
어ㅣ
'''
response = requests.request("GET", priceUrl, headers=headers, params=querystring)
#row = { 'category' : 'food', 'name' : 'lotteria', 'price' : '1500'};
#response = [response,row];
#trade_price가 현재가격
print("현재가 : " + response.text)
