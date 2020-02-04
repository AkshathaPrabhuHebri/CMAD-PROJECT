import datetime
import random
import string
import time
import requests
import json


def generate_syslogs():
    syslog = {"timestamp": "", "severity": "", "facility": "", "message": ""}
    counter = 0
    while True:
        ts = datetime.datetime.now()
        #convert datetime string to "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\"
        syslog["timestamp"] = str(ts).replace(" ", 'T')[:-3] + 'Z'
        syslog["severity"] = random.randint(0, 7)
        character_set = string.ascii_letters + string.digits
        syslog["facility"] = random_string_generator(character_set, 3)
        character_set = string.ascii_letters + " .:"
        syslog["message"] = random_string_generator(character_set, 100)
        counter += 1
        print(syslog)
        post_syslog(syslog)
        time.sleep(1)

def post_syslog(syslog):
    data = json.dumps(syslog)
    url = "http://localhost:8080/log"
    headers = {"Content-Type": "application/json"}
    response = requests.request("POST", url, headers=headers, data=data)
    print(response.status_code)


def random_string_generator(character_list, msg_len):
    random_string = ""
    for i in range(msg_len):
        random_string = random_string + random.choice(character_list)
    # print(random_string)
    return random_string

if __name__ == "__main__":
    generate_syslogs()


