# Aim: Program to perform prime number check (Prime Number Check).
num = int(input("Enter a number: "))
if num < 2:
    print(num, "is not a prime number")
    exit()
for i in range(2, num):
    if num % i == 0:
        print(num, "is not a prime number")
        break
else:
    print(num, "is a prime number")