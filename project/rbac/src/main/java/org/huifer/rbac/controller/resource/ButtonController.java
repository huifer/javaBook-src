package org.huifer.rbac.controller.resource;

import org.huifer.rbac.entity.res.Result;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/button")
public class ButtonController {
    @PostMapping("/add")
    public Result add() {
        return null;
    }

    @PostMapping("/query")
    public Result query() {
        return null;
    }

    @PostMapping("/editor")
    public Result editor() {
        return null;
    }

    @PostMapping("/delete")
    public Result delete() {
        return null;
    }
}
