package com.java.democollection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class DemoCollectionApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.of(1988, 6, 1, 0, 0, 0);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

}
