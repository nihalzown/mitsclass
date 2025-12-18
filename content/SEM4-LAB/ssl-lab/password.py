# Aim: Program to perform password strength check (Password Strength Check).
def checkpass(password):
    rate=0
    length=len(password)

    if length >= 8:
        rate += 1
    if any(char.isdigit() for char in password):
        rate += 1
    if any(char.islower() for char in password):
        rate += 1
    if any(char.isupper() for char in password):
        rate += 1
    if any(char in "!@#$%^&*()-_+=<>?{}[]|\/" for char in password):
        rate += 1

    return rate

password = input("Enter a password: ")
strength = checkpass(password)
print(f"Password strength rate: {strength}")