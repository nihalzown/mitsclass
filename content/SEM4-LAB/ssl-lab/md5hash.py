# Aim: Program to perform MD5 hash calculation (MD5 Hash Calculation).
import hashlib
message = "hello world".encode()
md5=hashlib.md5()
md5.update(message)
digest = md5.digest()
hexdigest = digest.hex()
print(hexdigest)
