package T2508;

/**
 * @Description: 进制转换
 * @Author: iniwym
 * @Date: 2025-08-14
 * @Link: https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/
 */
public class Code1780_CheckIfNumberIsASumOfPowersOfThree {

    /**
     * 检查一个整数是否可以表示为不同的3的幂次方的和
     *
     * @param n 待检查的整数
     * @return 如果n可以表示为不同的3的幂次方的和则返回true，否则返回false
     */
    public boolean checkPowersOfThree(int n) {
        // 通过不断除以3来检查每一位的余数
        // 如果余数为2，则说明该位需要两个相同的3的幂次方，违反了"不同"的要求
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }


}
