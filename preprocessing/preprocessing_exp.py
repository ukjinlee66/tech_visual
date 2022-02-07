import json
import nltk
from konlpy.tag import Okt
import re
# nltk.download('all')    # 최소 한번 실행해야 함
okt = Okt()
fname = 'SSE'
korean = re.compile('[\u3131-\u3163\uac00-\ud7a3]+')   # 한글 제거

text_file_path = f'/Users/lyk/Downloads/word_count/{fname}.txt'    # txt 파일 위치

m = {}

with open(text_file_path,'r',encoding='utf-8') as f:
  lines = f.readlines()               # 라인 별로 읽음
  for i, l in enumerate(lines):
    li = l.strip().split('\t')        #  \t 기준으로 split
    li[0] = li[0].replace('\\n','')
    
    cleaned = re.sub(r'[^\w\d\s]','',li[0])   # 불필요한 기호 제거
    
    if(li[0].isdigit()):
      m.setdefault(li[0],0)
      m[li[0]]+=int(li[len(li)-1])

rev = sorted(m.items(), reverse=True, key=lambda item: item[1])

m = []

for word, count in rev:
  temp={'word':word, 'count':count}
  m.append(temp)

with open(f"/Users/lyk/Downloads/word_count to json/{fname}.json",'w', encoding="utf-8") as outfile:
   json.dump(m, outfile, ensure_ascii=False, indent="\t")