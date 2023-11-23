package com.material.chain.business.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomUtil {

    public static int randomNumberOneToHundred() {
        Random random = new Random();
        // 生成1到100之间的随机数（包括1和100）
        return random.nextInt(100) + 1;
    }

    public static int randomNumberOneToTen() {
        Random random = new Random();
        // 生成1到100之间的随机数（包括1和100）
        return random.nextInt(10) + 1;
    }

    public static char generateRandomLetter() {
        // 生成 A 到 Z 的随机字母
        Random random = new Random();
        char randomLetter = (char) (random.nextInt(26) + 'A');
        return randomLetter;
    }

    public static String generatePurchaseNo() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHssmm");

        // 构建时间部分
        String timePart = now.format(formatter);

        // 生成6位随机数
        Random random = new Random();
        int randomValue = random.nextInt(900000) + 100000; // 生成100000到999999之间的随机数

        // 构建最终字符串
        return  "P" + timePart + randomValue;
    }
}
