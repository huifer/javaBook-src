package com.github.huifer.project.at.ctr;

import java.util.List;

import com.github.huifer.project.at.entity.req.AtContentReq;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.objects.annotations.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private AtController atController;

	@GetMapping("/")
	public String index(Model model) {
		ResponseEntity<List<AtContentReq>> unReadList = atController.unReadList(1);
		ResponseEntity<List<AtContentReq>> readList = atController.readList(1);

		model.addAttribute("unReadList", unReadList.getBody());
		model.addAttribute("readList", readList.getBody());
		model.addAttribute("cur_user_id", 1);
		return "index";
	}
}
