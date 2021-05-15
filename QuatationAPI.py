import requests

MarketCodeurl = "https://api.upbit.com/v1/market/KRW-BTC"

querystring = {"isDetails":"false"}

headers = {"Accept": "application/json"}

response = requests.request("GET", MarketCodeurl, headers=headers, params=querystring)

print(response.text)


MinuteCandleurl = "https://api.upbit.com/v1/candles/minutes/1"

querystring = {"market":"KRW-BTC","count":"1"}

headers = {"Accept": "application/json"}

response = requests.request("GET", MinuteCandleurl, headers=headers, params=querystring)

print(response.text)