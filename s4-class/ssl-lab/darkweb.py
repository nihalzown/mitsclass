import requests
import tldextract

url = 'https://www.google.onion'

try:
    response = requests.get(url, timeout=5)
    if response.status_code == 200:
        print(f'{url} is accessible on regular web')
        ext = tldextract.extract(url)
        tld = ext.suffix

        if tld in ('onion', 'i2p', 'bit'):
            print(f'{url} is a dark web site')
        else:
            print(f'{url} is not a dark web site')
    else:
        print(f'{url} is not accessible on regular web')
except requests.exceptions.RequestException as e:
    print(f'could not connect to {url}')