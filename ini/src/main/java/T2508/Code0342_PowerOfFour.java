package T2508;

/**
 * @Description: 取模
 * @Author: iniwym
 * @Date: 2025-08-15
 * @Link: https://leetcode.cn/problems/power-of-four/
 */
public class Code0342_PowerOfFour {
    /**
     * 判断一个整数是否为4的幂次方
     *
     * @param n 待判断的整数
     * @return 如果n是4的幂次方返回true，否则返回false
     */
    public boolean isPowerOfFour(int n) {
        // 通过不断除以4来判断是否为4的幂次方
        // 如果n能被4整除，则继续除以4，直到不能整除为止
        while (n != 0 && n % 4 == 0) {
            n /= 4;
        }
        // 最终如果结果为1，则说明原数是4的幂次方
        return n == 1;
    }

}
