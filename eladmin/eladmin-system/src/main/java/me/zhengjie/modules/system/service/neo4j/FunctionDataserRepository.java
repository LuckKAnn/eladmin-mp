package me.zhengjie.modules.system.service.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 19:57
 * @PackageName: me.zhengjie.modules.system.service.neo4j
 * @ClassName: FunctionDataserRepository
 * @Version 1.0
 */
@Repository
public interface FunctionDataserRepository extends Neo4jRepository<FuncNode, Long> {

    /**
     * 自定义查询方式：根据电影名称查询
     *
     * @param title 电影名称
     * @return 电影集合
     */
//    @Query("MATCH (n:Movie) where n.title contains $title RETURN n.title,n.description LIMIT 1")
//    @Query("MATCH (n:Movie) where n.title contains $title RETURN n LIMIT 1")
    @Query("MATCH (n:test1_Func)-[r]->{} RETURN n,r")
    List<FuncNode> findByTitle();

}
