num = int(input("Enter a number: "))
string = str(num)
if string == string[::-1]:
    print("The number is a palindrome")
else:
    print("The number is not a palindrome")