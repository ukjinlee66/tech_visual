import glob
import json

data = []

# 해당 경로의 모든 json 파일 지정 (같은 폴더일 경우 *.json)
# json파일 중 비어 있는 파일이 있을 경우 오류 발생
for f in glob.glob("C:\pythonPrj\selenium\*.json"):
    with open(f, encoding="utf-8") as infile:
        data.append(json.load(infile))

with open("merged_file.json",'w', encoding="utf-8") as outfile:
  json.dump(data, outfile, ensure_ascii=False, indent="\t")