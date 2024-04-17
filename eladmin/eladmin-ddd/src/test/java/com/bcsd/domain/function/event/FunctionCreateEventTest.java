package com.bcsd.domain.function.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 21:49
 * @PackageName: com.bcsd.domain.function.event
 * @ClassName: FunctionCreateEventTest
 * @Version 1.0
 */
class FunctionCreateEventTest {

    @Test
    void process() {
        FunctionCreateEvent functionCreateEvent = new FunctionCreateEvent();
        functionCreateEvent.process();
    }
}