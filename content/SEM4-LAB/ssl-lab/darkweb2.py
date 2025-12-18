import requests
import tldextract

url = 'https://www.google.onion'

try:
    ext = tldextract.extract(url)
    tld = ext.suffix

    if tld in ('onion', 'i2p', 'bit'):
        print(f'{url} is a dark web site')
    else:
        print(f'{url} is not a dark web site')
except requests.exceptions.RequestException as e:
    print(f'could not connect to {url}')