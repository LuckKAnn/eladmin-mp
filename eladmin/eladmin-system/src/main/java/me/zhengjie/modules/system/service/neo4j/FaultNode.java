package me.zhengjie.modules.system.service.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 19:56
 * @PackageName: me.zhengjie.modules.system.service.neo4j
 * @ClassName: FaultNode
 * @Version 1.0
 */
@Data

@NoArgsConstructor
@Node("test7_Fault")
public class FaultNode {

    @Id
    private Long ids;

    @Property("name")
    private String name;

    // @Relationship(type = "Belongs", direction = Relationship.Direction.OUTGOING)
    // private List<DatasetFuncNode> datasetFuncNodes;

    @Relationship(type = "Belongs", direction = Relationship.Direction.INCOMING)
    private List<FuncNode> functionNodes;
}
