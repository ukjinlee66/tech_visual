import json
import nltk
from konlpy.tag import Okt
import re
# nltk.download('all')    # 최소 한번 실행해야 함
okt = Okt()
fname = 'total'
korean = re.compile('[\u3131-\u3163\uac00-\ud7a3]+')   # 한글 제거

text_file_path = f'/Users/lyk/Downloads/word_count/{fname}.txt'    # txt 파일 위치

m = {}

stopword = ['_id','dept','company','tech','exp','qual','prefer']

with open(text_file_path,'r',encoding='utf-8') as f:
  lines = f.readlines()               # 라인 별로 읽음
  for i, l in enumerate(lines):
    li = l.strip().split('\t')        #  \t 기준으로 split
    li[0] = li[0].replace('\\n','')
    if('ObjectId' in li[0]):
      continue
    if li[0] in stopword:
      continue
    cleaned = re.sub(r'[^\w\d\s]','',li[0])   # 불필요한 기호 제거
    cleaned_content = cleaned.lower()       # 영어는 소문자로 통일

    korean_noun = okt.nouns(cleaned_content)       # 한글중 명사 추출

    english_only = re.sub(korean, '', cleaned_content)         # 한글 제거
    english_word_tokens = nltk.word_tokenize(english_only)     # 영어중 단어만 추출
    for korea in korean_noun:
      if korea:
        if korea.isdigit():         # 숫자는 뺌
          continue
        m.setdefault(korea,0)
        m[korea]+=int(li[len(li)-1])
    for english in english_word_tokens:
      if english:
        if(english.isdigit()):       # 숫자는 뺌
          continue
        m.setdefault(english,0)
        m[english]+=int(li[len(li)-1])

with open(f"/Users/lyk/Downloads/word_count to json/{fname}.json",'w', encoding="utf-8") as outfile:
   json.dump(m, outfile, ensure_ascii=False, indent="\t")