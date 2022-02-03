import selenium
import json 
import time

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

data = []

for page_index in range(1,91):
    URL = f'https://programmers.co.kr/job?page={page_index}'
    pCount = 1
    while(True):
        file_data = {}
        driver.get(url=URL)
        driver.implicitly_wait(3)
        try:
            element = driver.find_element(By.XPATH, f'/html/body/div[3]/div/section[2]/div/ul/li[{pCount}]/div[2]/h5/a')
            element.click()
        except:
            break
        pCount += 1
        
        time.sleep(0.3)
        ### 페이지 내 긁을걸 코딩하면 된다.
        company = driver.find_element(By.CLASS_NAME, 'sub-title')

        file_data["company"] = company.text

        title = driver.find_element(By.CLASS_NAME, 't-content')

        file_data["dept"] = title.text

        new_list=[]
        count = 1
        while(True):
            try:
                tech = driver.find_element(By.XPATH, f'/html/body/div[3]/div/div[1]/div/div[1]/section[2]/table/tbody/tr/td/code[{count}]')
                new_list.append(tech.text)
            except:
                break
            count += 1
    
        file_data["tech"] = new_list
        
        q_list = ""
        qCount = 1
        while(True):
            try:
                cut = driver.find_element(By.XPATH, f'/html/body/div[3]/div/div[1]/div/div[1]/section[4]/div/div/div/ul/li[{qCount}]')
                q_list += cut.text + " "
            except:
                break
            qCount += 1
        
        file_data["qual"] = q_list
        
        prefer_list = ""
        preferCount = 1
        while(True):
            try:
                prefer = driver.find_element(By.XPATH, f'/html/body/div[3]/div/div[1]/div/div[1]/section[5]/div/div/div/ul/li[{preferCount}]')
                prefer_list += prefer.text + " "
            except:
                break
            preferCount += 1
        
        file_data["prefer"] = prefer_list
        
        data.append(file_data)
            
with open("merged_file.json",'w', encoding="utf-8") as outfile:
  json.dump(data, outfile, ensure_ascii=False, indent="\t")
  
driver.close()
