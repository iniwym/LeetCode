package T2508;

/**
 * @Description: 取模
 * @Author: iniwym
 * @Date: 2025-08-13
 * @Link: https://leetcode.cn/problems/power-of-three/
 */
public class Code0326_PowerOfThree {
    /**
     * 判断一个整数是否为3的幂次方
     *
     * @param n 待判断的整数
     * @return 如果n是3的幂次方则返回true，否则返回false
     */
    public boolean isPowerOfThree(int n) {
        // 通过不断除以3来判断是否为3的幂次方
        // 如果n能被3整除，则继续除以3，直到不能整除为止
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        // 最终如果结果为1，则说明原数是3的幂次方
        return n == 1;
    }

}
