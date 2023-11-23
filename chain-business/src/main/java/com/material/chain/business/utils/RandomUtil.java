package com.material.chain.business.utils;

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
}
