import selenium
import json

from selenium import webdriver
from selenium.webdriver import ActionChains

from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait

from selenium.webdriver.chrome.service import Service

file_data = {}
count = 1

s = Service('./chromedriver')

driver = webdriver.Chrome(service=s)

URL = 'https://programmers.co.kr/job_positions/9167'
driver.get(url=URL)

company = driver.find_element(By.CLASS_NAME, 'sub-title')

file_data["company"] = company.text

title = driver.find_element(By.CLASS_NAME, 't-content')

file_data["dept"] = title.text

new_list=[]
while(True):
    try:
        tech = driver.find_element(By.XPATH, '/html/body/div[3]/div/div[1]/div/div[1]/section[2]/table/tbody/tr/td/code[' + str(count) +']')
        new_list.append(tech.text)
    except:
        break
    count += 1
    
file_data["tech"] = new_list

with open('test.json', 'w', encoding="utf-8") as make_file:
    json.dump(file_data, make_file, ensure_ascii=False, indent="\t")
driver.close()
