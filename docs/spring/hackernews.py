import asyncio
import aiohttp
from bs4 import BeautifulSoup
import json
from pathlib import Path
from datetime import date

from rewrite.ba import gogo


async def fetch_data(url):
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get(url) as response:
                response.raise_for_status()  # Raise HTTPError for bad responses (4xx or 5xx)
                return await response.text()
    except aiohttp.ClientError as e:
        print(f"Error fetching data: {e}")
        return None

async def parse_data(html):
    if not html:
        return []
    soup = BeautifulSoup(html, 'html.parser')
    links_with_text = []
    for a in soup.find_all('a', href=True):
        link = a['href']
        text = a.get_text(strip=True)
        if (link.startswith('https://') and
            'ycombinator.com' not in link and
            link != "https://github.com/HackerNews/API"):
            links_with_text.append({'link': link, 'text': text})
    return links_with_text

async def extract_metadata(url):
    try:
      async with aiohttp.ClientSession() as session:
        async with session.get(url) as response:
            response.raise_for_status()
            content = await response.text()
            soup = BeautifulSoup(content, 'html.parser')
            author_meta = soup.find('meta', attrs={'name': 'author'})
            author = author_meta['content'] if author_meta else ''
            description_meta = soup.find('meta', attrs={'name': 'description'})
            description = description_meta['content'] if description_meta else ''
            return {'author': author, 'description': description}
    except aiohttp.ClientError as e:
        print(f"request url error {url}")
        print(f"Error fetching metadata: {e}")
        return {'author': '', 'description': ''}



async def main():
    url = 'https://news.ycombinator.com/best?p=1'
    html = await fetch_data(url)
    links_with_text = await parse_data(html)
    results = []

    for item in links_with_text:
        link = item['link']
        text = item['text']
        if not link.endswith('.mp4'):
            metadata = await extract_metadata(link)
            results.append({'link': link, 'text': text, **metadata})
            try:
                gogo(link)
            except Exception as e:
                continue
        else:
            print(f"Skipping MP4 link: {link}")

    today = date.today().isoformat()
    file_path = Path(__file__).parent / f"{today}.json"
    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(results, f, indent=4, ensure_ascii=False)
    print(f"Data saved to {file_path}")

if __name__ == "__main__":
    asyncio.run(main())
