import selenium
import json 

from selenium import webdriver
from selenium.webdriver import ActionChains

from selenium.webdriver.common.keys import Keys

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait

from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By

print(selenium.__version__)

s = Service('./chromedriver')

driver = webdriver.Chrome(service=s)

file_data = {}
count = 1

for page_index in range(1,91):
    URL = f'https://programmers.co.kr/job?page={page_index}'
    for j in range(1,21):
        driver.get(url=URL)
        driver.implicitly_wait(3)
        element = driver.find_element(By.XPATH, f'/html/body/div[3]/div/section[2]/div/ul/li[{j}]/div[2]/h5/a')
        element.click()
        
        ### 페이지 내 긁을걸 코딩하면 된다.
        company = driver.find_element(By.CLASS_NAME, 'sub-title')

        file_data["company"] = company.text

        title = driver.find_element(By.CLASS_NAME, 't-content')

        file_data["dept"] = title.text

        new_list=[]
        while(True):
            try:
                tech = driver.find_element(By.XPATH, f'/html/body/div[3]/div/div[1]/div/div[1]/section[2]/table/tbody/tr/td/code[{count}]')
                new_list.append(tech.text)
            except:
                break
            count += 1
    
        file_data["tech"] = new_list

        with open('test.json', 'w', encoding="utf-8") as make_file:
            json.dump(file_data, make_file, ensure_ascii=False, indent="\t")
driver.close()
