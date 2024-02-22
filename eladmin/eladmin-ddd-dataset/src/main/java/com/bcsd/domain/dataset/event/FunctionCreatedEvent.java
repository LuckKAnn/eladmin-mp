package com.bcsd.domain.dataset.event;

import com.bcsd.domain.dataset.entity.FunctionInfo;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 19:20
 * @PackageName: com.bcsd.domain.dataset.event
 * @ClassName: FunctionCreatedEvent
 * @Version 1.0
 */
public class FunctionCreatedEvent extends DomainEvent<FunctionInfo> {
    public FunctionCreatedEvent(FunctionInfo data) {
        super(data);
    }

    public FunctionCreatedEvent(String source, FunctionInfo data) {
        super(source, data);
    }

    public void functionCreatedProcess() {

    }
}
