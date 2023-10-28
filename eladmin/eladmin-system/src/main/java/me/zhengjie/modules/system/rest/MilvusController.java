package me.zhengjie.modules.system.rest;

import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.MilvusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/milvus")
public class MilvusController {


    final MilvusService milvusService;

    public MilvusController(MilvusService milvusService) {
        this.milvusService = milvusService;
    }

    @GetMapping("/search")
    public List<SimilarityData> search() {
        return milvusService.search(new byte[10]);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        Long aLong = milvusService.countVector();
        return ResponseEntity.ok(aLong);
    }

    @GetMapping("/get")
    public ResponseEntity<MilvusData> getDataById(@RequestParam("id") Long vectorId) {

        MilvusData byId = milvusService.getById(vectorId);

        return ResponseEntity.ok(byId);

    }

    @GetMapping("/hello")
    public String hearbeat() {
        return "hello;";
    }
}
