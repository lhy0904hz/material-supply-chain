package com.material.chain.logistics.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateNoUtil {

    public static String generatePurchaseNo() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHssmm");

        // 构建时间部分
        String timePart = now.format(formatter);

        // 生成5位随机数
        Random random = new Random();
        int randomValue = random.nextInt(90000) + 10000; // 生成100000到999999之间的随机数

        // 构建最终字符串
        return  "L" + timePart + randomValue;
    }
}
