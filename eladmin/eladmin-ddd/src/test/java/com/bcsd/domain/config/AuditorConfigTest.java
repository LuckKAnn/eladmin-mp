package com.bcsd.domain.config;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 22:06
 * @PackageName: com.bcsd.domain.config
 * @ClassName: AuditorConfigTest
 * @Version 1.0
 */
class AuditorConfigTest {

    @Test
    void getCurrentAuditor() {
        AuthorityConfig authorityConfig = new AuthorityConfig();
        authorityConfig.check("");
    }

    @Test
    void testGetCurrentAuditor() {
    }
}