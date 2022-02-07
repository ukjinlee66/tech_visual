from tkinter import E
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

start_time = time.time()

for page_index in range(1,94):
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

        time.sleep(0.2)
        
        ## 페이지 내 긁을걸 코딩하면 된다.

        #회사
        company = driver.find_element(By.CLASS_NAME, 'sub-title')
        file_data["company"] = company.text

        #요약
        labels = driver.find_elements(By.CLASS_NAME, 't-label')
        contents = driver.find_elements(By.CLASS_NAME, 't-content')

        file_data["dept"] = ""
        file_data["exp"] = 0
        for i in range(len(contents)):
            label = labels[i].text.strip()
            content = contents[i].text.strip()
            if(label=="직무"):
                file_data["dept"] = content
            elif(label=="경력"):
                if(content!="신입" and content!="경력 무관"):
                    file_data["exp"] = int(content.split()[0])

        # tech
        tech_list = []
        try:
            tech = driver.find_elements(By.TAG_NAME, 'code')
            for i in tech:
                tech_list.append(i.text)
        except:
            print("error")
        file_data["tech"] = tech_list

        # 자격 요건
        q_list = ""
        try:
            req = driver.find_element(By.CLASS_NAME, 'section-requirements')
            q_list = req.text.replace('\n',' ')
        except:
            print('error')
        file_data["qual"] = q_list

        # 우대 사항
        prefer_list = ""
        try:
            prefer = driver.find_element(By.CLASS_NAME, 'section-preference')
            prefer_list = prefer.text.replace('\n',' ')
        except:
            print('error')
        file_data["prefer"] = prefer_list
        
        data.append(file_data)
            
with open("merged_file.json",'w', encoding="utf-8") as outfile:
  json.dump(data, outfile, ensure_ascii=False, indent="\t")
  
driver.close()
print("--- %s seconds ---" % (time.time() - start_time))