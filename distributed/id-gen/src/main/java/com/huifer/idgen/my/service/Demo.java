package com.huifer.idgen.my.service;

import com.huifer.idgen.my.service.bean.Id;

/**
 * @author: wang
 * @description:
 */
public class Demo {


	public static void main(String[] args) {
		GenIdService genIdService = new GenIdServiceImpl();
		long l = genIdService.genId();
		Id id = genIdService.expId(l);
		System.out.println(l);
		System.out.println(id);
	}

}