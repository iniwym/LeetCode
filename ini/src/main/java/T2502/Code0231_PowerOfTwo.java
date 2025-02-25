package T2502;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2025-02-20
 * @Link: https://leetcode.cn/problems/power-of-two/
 */
public class Code0231_PowerOfTwo {
    /**
     * 判断一个整数是否是2的幂次方
     * 
     * @param n 待判断的整数
     * @return 如果n是2的幂次方，则返回true；否则返回false
     */
    public boolean isPowerOfTwo(int n) {
        // 利用位运算特性，2的幂次方与它的负数按位与的结果是它本身
        return n > 0 && n == (n & -n);
    }
}
