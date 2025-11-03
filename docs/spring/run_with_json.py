import os
import json
import time

from rewrite.ba import gogo


# 获取目录下的所有JSON文件
def get_json_files(directory):
  return [f for f in os.listdir(directory) if f.endswith('.json')]


# 提取所有JSON文件中的url
def extract_urls_from_json(directory):
  urls = []
  json_files = get_json_files(directory)

  for json_file in json_files:
    json_path = os.path.join(directory, json_file)

    # 读取JSON文件内容
    with open(json_path, 'r', encoding='utf-8') as file:
      try:
        data = json.load(file)

        # 如果是列表结构，提取每个对象的url
        if isinstance(data, list):
          for entry in data:
            if 'link' in entry:
              urls.append(entry['link'])
      except json.JSONDecodeError:
        print(f"Error decoding JSON in file {json_file}")

  return urls


# 指定目录路径
directory = '/Users/zhangsan/ai/gupiao/daily-list/public/data/hackernews'  # 这里替换为你的目录路径

# 提取并输出所有的urls
urls = extract_urls_from_json(directory)
unique_urls = list(set(urls))
for url in unique_urls:
  print(url)
  gogo(url)
  time.sleep(1)
