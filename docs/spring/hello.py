import yfinance as yf
import akshare as ak
import pandas as pd
import requests
from bs4 import BeautifulSoup
from datetime import datetime


def format_number(value):
    """将数值格式化为带千位分隔符的字符串"""
    return f"{value:,.2f}"

def format_change(value):
    """将涨跌幅格式化为带正负号的百分比字符串"""
    return f"+{value:.2f}%" if value >= 0 else f"{value:.2f}%"

def get_market_indices():
    """获取三大市场指数数据"""
    market_data = []
    
    # 获取A股指数数据
    try:
        stock_zh_index_spot_em_df = ak.stock_zh_index_spot_sina()
        sh_index = stock_zh_index_spot_em_df[stock_zh_index_spot_em_df['代码'] == 'sh000001'].iloc[0]
        ssz_index = stock_zh_index_spot_em_df[stock_zh_index_spot_em_df['代码'] == 'sz399001'].iloc[0]
        ssz_index1 = stock_zh_index_spot_em_df[stock_zh_index_spot_em_df['代码'] == 'sz399006'].iloc[0]
        
        # 添加A股指数数据
        market_data.extend([
            {
                'name': '上证指数',
                'value': format_number(sh_index['最新价']),
                'change': format_change(sh_index['涨跌幅'])
            },
            {
                'name': '深证成指',
                'value': format_number(ssz_index['最新价']),
                'change': format_change(ssz_index['涨跌幅'])
            },
            {
                'name': '创业板指',
                'value': format_number(ssz_index1['最新价']),
                'change': format_change(ssz_index1['涨跌幅'])
            }
        ])
    except Exception as e:
        print(f'获取A股指数数据失败：{str(e)}')

    # 获取美股指数数据
    try:
        dow = yf.Ticker('^DJI').history(period='1d')
        sp500 = yf.Ticker('^GSPC').history(period='1d')
        nasdaq = yf.Ticker('^IXIC').history(period='1d')
        
        # 计算美股涨跌幅
        dow_change = ((dow['Close'].iloc[-1]/dow['Open'].iloc[-1]-1)*100)
        sp500_change = ((sp500['Close'].iloc[-1]/sp500['Open'].iloc[-1]-1)*100)
        nasdaq_change = ((nasdaq['Close'].iloc[-1]/nasdaq['Open'].iloc[-1]-1)*100)
        
        # 添加美股指数数据
        market_data.extend([
            {
                'name': '道琼斯',
                'value': format_number(dow['Close'].iloc[-1]),
                'change': format_change(dow_change)
            },
            {
                'name': '纳斯达克',
                'value': format_number(nasdaq['Close'].iloc[-1]),
                'change': format_change(nasdaq_change)
            },
            {
                'name': '标普500',
                'value': format_number(sp500['Close'].iloc[-1]),
                'change': format_change(sp500_change),
               
            }
        ])
    except Exception as e:
        print(f'获取美股指数数据失败：{str(e)}')

    return market_data


def  ff():
    try:
            # 获取板块数据并按涨跌幅降序排序，获取前两个板块
        stock_board_industry_name_em_df = ak.stock_board_industry_name_em()
        top_sectors = stock_board_industry_name_em_df.sort_values('涨跌幅', ascending=False).head(6)
        
        print('\n涨幅最大的两个板块：')
        for _, sector in top_sectors.iterrows():
            print(f"{{ name: '{sector['板块名称']}', change: '{format_change(sector['涨跌幅'])}' }},")
    except Exception as e:
        print(f'获取板块数据失败：{str(e)}')





if __name__ == '__main__':
    market_data = get_market_indices()
    for index in market_data:
        print(f"{{ name: '{index['name']}', value: '{index['value']}', change: '{index['change']}' }},")
    ff()