package com.appleye.question;

import com.appleye.utils.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 角谷猜想：
 * 一个正整数x，如果是奇数就乘以3再加1，如果是偶数除以2，这样经过若干个次数，最终回到1
 * @author Appleye
 * @time 2020-09-03 11:18
 */
public class Collatz {


    static Map<String, Integer> record(int x) {
        Map<String, Integer> map = new HashMap<>(3);
        int times = 0;
        int maxValue = 0;
        map.put("maxValue", 0);
        map.get("maxValue");
        while (x != 1) {
            if (Tools.isEven(x)) {
                x = handleEven(x);
            } else {
                x = handleOdd(x);
            }
            times++;
            map.put("maxValue", Math.max(map.get("maxValue"), x));
            map.put("times", times);
        }

        return map;
    }


    static int handleEven(int even) {
        return even / 2;
    }

    static int handleOdd(int odd) {
        return 3 * odd + 1;
    }
}
