package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.EsCode;
import me.zhengjie.modules.system.service.ElasticCodeService;
import me.zhengjie.utils.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:17
 * @PackageName: me.zhengjie.web
 * @ClassName: ElasticController
 * @Version 1.0
 */
@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class ElasticController {

    final ElasticCodeService elasticCodeService;

    @ApiOperation("分页查询函数列表")
    @GetMapping("/list")
    public ResponseEntity<PageResult<EsCode>> getFunByPage(@RequestParam("pageId") Long pageId, @RequestParam("pageSize") Integer size) {

        return ResponseEntity.ok(null);
    }

    @ApiOperation("根据Id查询函数的代码")
    @GetMapping("/code")
    public ResponseEntity<EsCode> getFuncCodeById(@RequestParam("id") Long id) {

        return ResponseEntity.ok(null);
    }


    @ApiOperation("根据函数名称查询函数代码")
    @GetMapping("/name")
    public ResponseEntity<List<EsCode>> getFunctionByName(@RequestParam("name") String functionName) {

        List<EsCode> functionByName = elasticCodeService.getFunctionByName(functionName);

        return ResponseEntity.ok(functionByName);
    }

}
