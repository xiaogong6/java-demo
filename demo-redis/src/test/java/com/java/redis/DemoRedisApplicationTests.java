package com.java.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DefaultRedisScript<Long> generateSerialNumber;

    @Test
    public void printCode() {
        System.out.println(generateOrderCode(66624L));
    }

    @Test
    void contextLoads() {
        System.out.println(generateOrderCode(66624L));
    }

    public String generateOrderCode(Long tenantId) {
        List<String> keys = new ArrayList<>();
        keys.add(MessageFormat.format("order:generatenumber:{0}", tenantId));
        Object result = redisTemplate.execute(generateSerialNumber, keys, String.valueOf(1000000));
        return generateOrderCodeV2(result);
    }

    /**
     * 单号生产规则；
     *
     * @param generateNumber
     * @return
     */
    private String generateOrderCodeV2(Object generateNumber) {
        // D YYMMDD 流水号（6位） 随机数（4位）
        return getRandomCodeV2((Long) generateNumber);
    }

    /**
     * 雅戈尔版本 单号生产规则；
     * 前缀+6位日期+流水号+随机数+后缀
     */
    private static String getRandomCodeV2(long generateNumber) {

        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        dateStr = sdf.format(new Date());
        // 随机数生成
        String random = new Random().nextInt(1000) + "";
        int times = Integer.toString(1000).length() - random.length();
        for (int i = 0; i < times; i++) {
            random = "0" + random;
        }
        // 流水号格式处理
        StringBuilder generateNumberStr = new StringBuilder(String.valueOf(generateNumber));
        int complementLength = 6 - generateNumberStr.length();
        for (int i = 0; i < complementLength; i++) {
            generateNumberStr.insert(0, "0");
        }
        return "D" + dateStr + generateNumberStr + random;

    }

}
