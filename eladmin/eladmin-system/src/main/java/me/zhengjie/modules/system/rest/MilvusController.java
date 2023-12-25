package me.zhengjie.modules.system.rest;

import com.google.common.collect.Lists;
import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.SearchResultDTO;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.MilvusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
        return milvusService.search(new byte[10]).getSimilarities();
    }

    @GetMapping("/v2/search")
    public SearchResultDTO searchV2() {
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

    @GetMapping("/batch")
    public ResponseEntity<List<MilvusData>> getDataById(@RequestParam("cid1") Long vectorId, @RequestParam("cid2") Long v2) {

        List<MilvusData> byId = milvusService.getByIds(Lists.newArrayList(vectorId, 445177401023664724L));
        return ResponseEntity.ok(byId);

    }

    @GetMapping("/hello")
    public String hearbeat() {
        return "hello;";
    }
}
