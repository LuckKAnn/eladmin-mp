package me.zhengjie.web;

import me.zhengjie.service.MilvusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/milvus")
public class MilvusController {


    final MilvusService milvusService;

    public MilvusController(MilvusService milvusService) {
        this.milvusService = milvusService;
    }

    @GetMapping("/search")
    public void search(){



    }

    @GetMapping("/hello")
    public String hearbeat(){
        return "hello;";
    }
}
