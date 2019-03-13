package com.huifer.ssm.controller;

import com.huifer.ssm.exception.MyException;
import com.huifer.ssm.pojo.Item;
import com.huifer.ssm.pojo.QueryModel;
import com.huifer.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-11
 */
@RestController
//@RequestMapping(value = "item", produces = "application/json;charset=utf8")// 这个地方直接修改了编码格式
@RequestMapping(value = "item")
public class TestController {
    @Autowired
    private ItemService service;

    @GetMapping("queryItem")
    public ResponseEntity<List<Item>> queryItem() {
        List<Item> items = service.queryItemList();

        return ResponseEntity.ok().body(items);
    }

    @GetMapping("query")
    public ResponseEntity query(Integer id, @RequestParam(value = "name", required = false, defaultValue = "awsl") String name) {
        //http://localhost:8082/item/query?id=1&name=%E7%8E%8B
        HashMap<Integer, String> has = new HashMap<>();
        has.put(id, name);
        return ResponseEntity.ok(has);

    }

    @GetMapping("pojo")
    public ResponseEntity queryPojo(Integer id, String name, Item item) {
        //http://localhost:8082/item/pojo?id=3&name=lkjl
        return ResponseEntity.ok(item);

    }

    @GetMapping("tm")
    public ResponseEntity tm(QueryModel queryModel) {
        //http://localhost:8082/item/tm?item.id=1&item.name=abc&user.id=3&user.name=lkjljljlk
        return ResponseEntity.ok(queryModel);
    }


    @GetMapping("double")
    public ResponseEntity array(String[] id) {
        //http://localhost:8082/item/double?id=1&id=2
        return ResponseEntity.ok(id);
    }

    @GetMapping("date")
    public ResponseEntity date(String d) {
//        http://localhost:8082/item/date?d=2019-1-1
        return ResponseEntity.ok(d);
    }

    @GetMapping("/excetion")
    public ResponseEntity excetion(Integer id) throws MyException {
        if (id > 1) {
            throw new MyException("自定义异常");
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @PostMapping("file")
    public void file(MultipartFile picFile) throws IOException {
        if (picFile != null) {
            // 保存到本地
            String uploadFile = picFile.getOriginalFilename();
            if (uploadFile != null && !"".contentEquals(uploadFile)) {
                String ext = uploadFile.substring(uploadFile.lastIndexOf("."));
                String bf = UUID.randomUUID().toString() + ext;
                String baseDir = "E:\\work-java\\2019-java\\ssm\\upload\\";
                File dirFile = new File(baseDir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
                picFile.transferTo(new File(baseDir + bf));

            }

        }
        return;
    }

    @GetMapping("/str")
    public String s() {
        return "中文";
    }

}
