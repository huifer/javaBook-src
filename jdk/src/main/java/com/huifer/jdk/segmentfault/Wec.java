package com.huifer.jdk.segmentfault;

import java.util.*;
//https://segmentfault.com/q/1010000019961989/a-1020000019998424
public class Wec {
//	需要写一个方法实现将一个String表达式按下面的要求转换。
//
//说明：1.表达式里有三种符号 ：+ , - , /。
//
//要求：实现 / 符号优先级最高，在 / 符号相关的字符两边加上括号 （ + , -优先级一致）
//
//例如：原表达式如下：
//
//var keyword = "AA + BB/CC/CD + DD - EE + (FF-GG/HH)/JJ + KK"
//要求转换表达式如下：
//
//var keyword = "AA + (BB/CC/CD) + DD - EE + ((FF-(GG/HH))/JJ) + KK"


	public static void main(String[] args) {
//		+ E - F + (G-H/I)/J + K
		String sv = "A+B/C/D+E-F+(g-h/i)/j+k-a/c";
		String sv2 = sv.replace(" ", "");
		char[] chars = sv2.toCharArray();
		int length = chars.length;


		ArrayList<String> sc = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			sc.add(String.valueOf(chars[i]));
		}
		ffff(chars, length);
	}


	private static void ffff(char[] chars, int length) {
		// 是否指向 "/"
		boolean flag = false;
		int i = 0;
		ArrayList<String> ssss = new ArrayList<>();
		ArrayList<Integer> iList = new ArrayList<>();

		for (int iii = 0; iii < chars.length; iii++) {
			ssss.add(String.valueOf(chars[iii]));
		}
		for (int j = 0; j < length; j++) {
			// 指针j 向后一直移动
			if (chars[j] == '/') {
				if (flag == true) {
				} else {
					flag = true;
				}

			}
			if (flag == true) {
				if (chars[j] == '+' || chars[j] == '-') {
					i = j - 1;
					flag = false;
				}
				if (chars[j] == '/') {
				}
				if (chars[j] != '/') {
				}
			}

			if (flag == false) {
				if (j == 0) {
				}
				// j = 0 时 , i 也等于0  , i 不增加
				if (j != 0 && flag == false) {
					i++;
				}
			}
			System.out.println(i);
			System.out.println(j);
			System.out.println("=================");

			iList.add(i);

		}

		if (chars[chars.length - 2] == '/') {
			iList.add(chars.length + 1);
		}

		iList.forEach(System.out::println);

		ArrayList<Integer> ac = same(iList);

		for (int i1 = 0; i1 < ac.size(); i1++) {
			if (i1 % 2 == 0) {

				ssss.add(ac.get(i1) + i1-1, "(");
			} else {

				ssss.add(ac.get(i1) + i1, ")");
			}
		}

		print(ssss);
	}

	public static ArrayList<Integer> same(List<Integer> list) {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			Integer key = list.get(i);
			String old = map.get(key);
			if (old != null) {
				map.put(key, old + "," + (i + 1));
			} else {
				map.put(key, "" + (i + 1));
			}
		}
		Iterator<Integer> it = map.keySet().iterator();
		ArrayList<Integer> res = new ArrayList<>();
		while (it.hasNext()) {
			Integer key = it.next();
			String value = map.get(key);
			if (value.indexOf(",") != -1) {
//				System.out.println(key + " 重复,行： " + value);
				String[] split = value.split(",");

				ArrayList<Integer> ac = new ArrayList<>();

				for (String s : split) {
					ac.add(Integer.valueOf(s));
				}
				Optional<Integer> min = ac.stream().min(Integer::compareTo);
				Optional<Integer> max = ac.stream().max(Integer::compareTo);
				res.add(min.get());
				res.add(max.get());

			}
		}
		return res;

	}

	private static void print(ArrayList<String> ssss) {
		StringBuilder sb = new StringBuilder();
		for (String s : ssss) {
			sb.append(s);
		}
		System.out.println(sb);
	}
}
