import datetime
import random
import string
import time
import requests
import json


def generate_syslogs():
    syslog_msgs = [
        "Unable to login, invalid credentials",
        "User xyz logged in at 07:08:35",
        "User xyz executed 'config term' command",
        "Login Success [user: dnac] [Source: 10.65.72.187] [localport: 23] at 07:49:51 UTC Fri Feb 7 2020",
        "abcded su: 'su root' failed for lonvick on /dev/pts/8",
        "abcded.example.com su - - - 'su root' failed for lonvick on /dev/pts/8",
        "abcded.example.com - ID47 [exampleSDID@32473 iut=\"3\" eventSource=",
        "User xyz executed shut down the port Gi0/0/1",
        "User abc executed shut down the port Gi0/0/2",
        "User user1 executed shut down the port Gi0/0/3",
        "User abc is not authorized to chnage the port administrative status"
    ]
    device_names = [
        "device1", "device2", "device3", "device4", "device5"
    ]
    syslog = {"timestamp": "", "severity": "", "facility": "", "message": ""}
    counter = 0
    while True:
        ts = datetime.datetime.utcnow()
        syslog["timestamp"] = ts.isoformat().split('.')[0]
        syslog["severity"] = random.randint(0, 7)
        character_set = string.ascii_letters + string.digits
        syslog["facility"] = random_string_generator(character_set, 3)
        syslog["message"] = random.choice(syslog_msgs)
        syslog["deviceName"] = random.choice(device_names)
        counter += 1
        print(syslog)
        post_syslog(syslog)
        time.sleep(1)

def post_syslog(syslog):
    data = json.dumps(syslog)
    url = "http://localhost:8090/log"
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


