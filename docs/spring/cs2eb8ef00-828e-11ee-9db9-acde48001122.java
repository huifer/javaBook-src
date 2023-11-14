package com.github.huifer.project.at.ctr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.huifer.project.at.entity.AtUserInfo;
import com.github.huifer.project.at.entity.req.AtContentReq;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AtController {
	/**
	 * key:user_id
	 * value: at message id
	 */
	Map<Integer, List<Integer>> userMappingAtId = new HashMap<>(64);

	/**
	 * key: at message id
	 * value: at message object
	 */
	Map<Integer, AtContentReq> atContentReqMap = new HashMap<>(64);

	AtomicInteger atomicInteger = new AtomicInteger(0);

	/**
	 * 已读
	 * key: user id
	 * value: at message id
	 */
	Map<Integer, List<Integer>> read = new HashMap<>(65);

	/**
	 * 未读
	 * key: user id
	 * value: at message id
	 */
	Map<Integer, List<Integer>> unRead = new HashMap<>(65);

	@GetMapping("/user")
	public ResponseEntity<List<AtUserInfo>> user() {

		List<AtUserInfo> userInfos = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			AtUserInfo atUserInfo = new AtUserInfo();
			String name = UUID.randomUUID().toString();
			atUserInfo.setAliasName(name);
			atUserInfo.setName(name);
			atUserInfo.setUid(i);
			userInfos.add(atUserInfo);
		}
		return ResponseEntity.ok(userInfos);
	}

	@PostMapping("/at")
	public ResponseEntity<Boolean> at(
			@RequestBody AtContentReq req
	) {
		// 放入消息列表
		int atId = atomicInteger.incrementAndGet();
		req.setId(atId);

		atContentReqMap.put(atId, req);
		// 放入未读
		for (AtUserInfo atUserInfo : req.getAtUserInfos()) {
			List<Integer> atIds = userMappingAtId.get(atUserInfo.getUid());

			if (CollectionUtils.isEmpty(atIds)) {
				atIds = new ArrayList<>();
			}
			atIds.add(atId);
			userMappingAtId.put(atUserInfo.getUid(), atIds);
			List<Integer> unReadId = unRead.get(atUserInfo.getUid());
			if (CollectionUtils.isEmpty(unReadId)) {
				unReadId = new ArrayList<>();
			}
			unReadId.add(atId);
			unRead.put(atUserInfo.getUid(), unReadId);
		}
		return ResponseEntity.ok(true);
	}

	@GetMapping("/read/{at_id}/{user_id}")
	public ResponseEntity<Boolean> read(
			@PathVariable("at_id") Integer atId,
			@PathVariable("user_id") Integer userId
	) {
		List<Integer> unReadAtIds = unRead.get(userId);
		boolean remove = unReadAtIds.remove(atId);
		if (remove) {
			List<Integer> readAtIds = read.get(userId);
			if (CollectionUtils.isEmpty(readAtIds)) {
				readAtIds = new ArrayList<>();
			}
			readAtIds.add(atId);
			read.put(userId, readAtIds);
		}
		return ResponseEntity.ok(true);
	}

	@GetMapping("/read/{user_id}")
	public ResponseEntity<List<AtContentReq>> readList(
			@PathVariable("user_id") Integer userId
	) {
		List<Integer> readAtIds = read.get(userId);

		if (CollectionUtils.isEmpty(readAtIds)) {
			return ResponseEntity.ok().build();
		}
		List<AtContentReq> res = new ArrayList<>();

		for (Integer readAtId : readAtIds) {
			res.add(atContentReqMap.get(readAtId));
		}
		return ResponseEntity.ok(res);
	}

	@GetMapping("/un_read/{user_id}")
	public ResponseEntity<List<AtContentReq>> unReadList(
			@PathVariable("user_id") Integer userId
	) {
		List<Integer> unReadIds = unRead.get(userId);
		if (CollectionUtils.isEmpty(unReadIds)) {
			return ResponseEntity.ok().build();
		}
		List<AtContentReq> res = new ArrayList<>();
		for (Integer readAtId : unReadIds) {
			res.add(atContentReqMap.get(readAtId));
		}
		return ResponseEntity.ok(res);
	}

}
