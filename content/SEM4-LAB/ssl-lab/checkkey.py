# Aim: Program to perform keylogger detection (Keylogger Detection).
import os
def check_key():
    try:
        with open('log.txt', 'r') as f:
            data = f.read()

        if "Key" in data:
            print("Keylogger detected")
        else:
            print("Keylogger not detected")
    except FileNotFoundError:
        print("log file not found")
check_key()