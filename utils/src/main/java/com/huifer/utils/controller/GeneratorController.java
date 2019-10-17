package com.huifer.utils.controller;

import com.huifer.utils.entity.PageEntity;
import com.huifer.utils.mapper.GeneratorMapper;
import com.huifer.utils.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: wang
 * @description:
 */
@RestController
@RequestMapping("/")
public class GeneratorController {

    @Autowired
    private GeneratorMapper generatorMapper;

    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/list")
    public Object getList(
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    ) {
        List<Map<String, Object>> data = generatorMapper.findTableDesc("", offset, limit);

        int total = generatorMapper.findTableDescCount("");
        PageEntity pageEntity = new PageEntity(total, offset, limit, data);
        return pageEntity;

    }


    @GetMapping("/goto")
    public void oo(
            @RequestParam("tables") String[] tables, HttpServletResponse response
    ) throws IOException {

        byte[] data = generatorService.generatorCode(tables);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"framework.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}