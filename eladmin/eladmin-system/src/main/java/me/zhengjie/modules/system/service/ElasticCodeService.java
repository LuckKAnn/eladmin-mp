package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.EsCode;

import java.util.*;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:19
 * @PackageName: me.zhengjie.service
 * @ClassName: ElasticCodeService
 * @Version 1.0
 */
public interface ElasticCodeService {

    List<EsCode> getAll(int pageId, int pageSize);

    EsCode getFunctionById(String id);

    List<EsCode> getFunctionByName(String functionName);

    Long getCount();
}
