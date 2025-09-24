package T2509;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 除法
 * @Author: iniwym
 * @Date: 2025-09-24
 * @Link: https://leetcode.cn/problems/fraction-to-recurring-decimal/
 */
public class Code0166_FractionToRecurringDecimal {

    /**
     * 将分数转换为小数字符串表示
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 分数的小数表示，如果是有限小数则返回普通小数形式，如果是无限循环小数则将循环节用括号括起来
     */
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator;
        long b = denominator;
        String sign = a * b < 0 ? "-" : "";
        a = Math.abs(a); // 保证下面的计算过程不产生负数
        b = Math.abs(b);

        // 计算整数部分 q 和初始余数 r
        long q = a / b;
        long r = a % b;
        if (r == 0) { // 没有小数部分
            return sign + q;
        }

        StringBuilder ans = new StringBuilder(sign).append(q).append('.');
        Map<Long, Integer> rToPos = new HashMap<>();
        rToPos.put(r, ans.length()); // 记录初始余数对应位置

        // 处理小数部分，通过不断除法计算各位数字，同时检测循环节
        while (r > 0) {
            // 计算小数点后的数字 q，更新 r
            r *= 10;
            q = r / b;
            r %= b;
            ans.append(q);
            if (rToPos.containsKey(r)) { // 有循环节
                int pos = rToPos.get(r); // 循环节的开始位置
                return ans.substring(0, pos) + "(" + ans.substring(pos) + ")";
            }
            rToPos.put(r, ans.length()); // 记录余数对应位置
        }
        return ans.toString(); // 有限小数
    }


}
