package com.huifer.idgen.my.service.bean.enums;

public enum IdType {
	TYPE_ONE("type-one"),
	TYPE_TWO("type-two"),
	;
	private String typeName;

	IdType(String typeName) {
		this.typeName = typeName;
	}

	public static IdType parse(String typeName) {
		if ("type-one".equalsIgnoreCase(typeName)) {
			return TYPE_ONE;
		} else if ("type-two".equalsIgnoreCase(typeName)) {
			return TYPE_TWO;
		}
		return null;
	}

	public static IdType parse(long typeNumb) {
		if (typeNumb == 1) {
			return TYPE_ONE;
		} else if (typeNumb == 2) {
			return TYPE_TWO;
		}
		return null;
	}

	public long value() {
		switch (this) {
			case TYPE_ONE:
				return 1;
			case TYPE_TWO:
				return 2;
			default:
				return 1;
		}
	}
}
