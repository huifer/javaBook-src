package com.huifer.idgen.my.service.bean;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: wang
 * @description: id
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Id implements Serializable {

	private static final long serialVersionUID = 6365925432568987922L;
	/**
	 * 机器id
	 */
	private long machine;
	/**
	 * 步长
	 */
	private long seq;
	/**
	 * 时间戳
	 */
	private long time;
	/**
	 * 生产方法
	 */
	private long genMethod;
	/**
	 * 类型
	 */
	private long type;
	/**
	 * 版本号
	 */
	private long version;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"machine\":")
				.append(machine);
		sb.append(",\"seq\":")
				.append(seq);
		sb.append(",\"time\":")
				.append(time);
		sb.append(",\"genMethod\":")
				.append(genMethod);
		sb.append(",\"type\":")
				.append(type);
		sb.append(",\"version\":")
				.append(version);
		sb.append('}');
		return sb.toString();
	}
}