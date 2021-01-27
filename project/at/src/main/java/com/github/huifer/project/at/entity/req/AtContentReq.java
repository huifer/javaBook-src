package com.github.huifer.project.at.entity.req;

import java.util.List;

import com.github.huifer.project.at.entity.AtUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class AtContentReq {
	private String content;

	private Integer id;
	private List<AtUserInfo> atUserInfos;
}
