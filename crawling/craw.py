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

s = Service('./chromedriver')

driver = webdriver.Chrome(service=s)

URL = 'https://programmers.co.kr/job_positions/9167'
driver.get(url=URL)

company = driver.find_element(By.CLASS_NAME, 'sub-title')
print(company.text)

file_data["company"] = company.text

title = driver.find_element(By.CLASS_NAME, 't-content')
print(title.text)

file_data["dept"] = title.text

search_box = driver.find_elements(By.TAG_NAME, 'code')

new_list=[]
for box in search_box:
    new_list.append(box.text)
    print(box.text)
file_data["tech"] = new_list

with open('test.json', 'w', encoding="utf-8") as make_file:
    json.dump(file_data, make_file, ensure_ascii=False, indent="\t")
driver.close()
