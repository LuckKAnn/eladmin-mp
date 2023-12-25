package me.zhengjie.modules.system.service.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 19:55
 * @PackageName: me.zhengjie.modules.system.service.neo4j
 * @ClassName: DatasetFuncNode
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node("test7_Dataset")
public class DatasetFuncNode {
    @Id
    private Long identity;
    @Property("name")
    private String name;
    @Relationship(type = "Belongs", direction = Relationship.Direction.INCOMING)
    private List<FaultNode> directors;


}
