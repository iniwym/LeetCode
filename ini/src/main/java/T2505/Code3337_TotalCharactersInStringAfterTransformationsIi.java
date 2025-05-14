package T2505;

import java.util.List;

/**
 * @Description: 矩阵快速幂
 * @Author: iniwym
 * @Date: 2025-05-14
 * @Link: https://leetcode.cn/problems/total-characters-in-string-after-transformations-ii/
 */
public class Code3337_TotalCharactersInStringAfterTransformationsIi {
    private static final int MOD = 1000000007;

    /**
     * 计算字符串经过一系列转换后的长度
     * 该方法首先统计字符串s中每个字母的出现次数，然后通过矩阵运算应用转换规则，
     * 最后计算转换后的字符串长度这种转换基于给定的转换规则（nums）和转换次数（t）
     *
     * @param s    输入的字符串，仅包含小写字母
     * @param t    转换的次数
     * @param nums 转换规则，每个元素表示一个字母转换到另一个字母的概率
     * @return 返回转换后的字符串长度
     */
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // 统计字符串s中每个字母的出现次数
        long[] count = new long[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // 构建转换规则的矩阵表示
        long[][] matrix = buildMatrix(nums);
        // 计算转换规则矩阵的t次幂，表示t次转换后的结果
        long[][] mt = matrixPower(matrix, t);

        // 将字母出现次数与转换后的矩阵相乘，得到转换后的字母出现次数
        long[] finalCount = multiply(count, mt);

        // 计算转换后的字符串长度
        long result = 0;
        for (int i = 0; i < 26; i++) {
            result = (result + finalCount[i]) % MOD;
        }
        return (int) result;
    }

    /**
     * 根据给定的整数列表构建一个26x26的矩阵
     * 该矩阵表示从一个字母通过一定步骤转换到另一个字母的次数
     * 每个字母由0到25的整数表示，对应于'a'到'z'
     *
     * @param nums 包含26个整数的列表，每个整数代表相应字母转换的步数
     * @return 返回一个26x26的矩阵，其中的值表示转换的次数
     */
    private long[][] buildMatrix(List<Integer> nums) {
        // 初始化一个26x26的矩阵，用于表示字母间的转换次数
        long[][] matrix = new long[26][26];

        // 遍历每个字母，计算其转换到其他字母的次数
        for (int c = 0; c < 26; c++) {
            // 获取当前字母的转换步数
            int numSteps = nums.get(c);

            // 根据步数计算当前字母可以转换到的其他字母
            for (int step = 1; step <= numSteps; step++) {
                // 计算转换后字母的索引，使用模运算确保结果仍在0到25之间
                int d = (c + step) % 26;

                // 更新矩阵中当前字母到转换后字母的次数，使用模运算避免溢出
                matrix[c][d] = (matrix[c][d] + 1) % MOD;
            }
        }

        // 返回构建完成的矩阵
        return matrix;
    }

    /**
     * 计算矩阵的幂次方
     * 该方法使用快速幂算法来高效地计算矩阵的幂次方
     * 快速幂算法通过将幂次方分解为二进制形式，从而减少乘法操作的次数
     *
     * @param base  基础矩阵，即需要被乘以自身power次的矩阵
     * @param power 幂次方，表示矩阵需要被乘以自身的次数
     * @return 计算得到的矩阵的幂次方结果
     */
    private long[][] matrixPower(long[][] base, int power) {
        // 初始化结果矩阵为单位矩阵，以便进行矩阵乘法操作
        long[][] result = new long[26][26];
        for (int i = 0; i < 26; i++) {
            result[i][i] = 1;
        }

        // 使用快速幂算法计算矩阵的幂次方
        while (power > 0) {
            // 如果当前幂次方的二进制表示的最低位为1，则将当前结果与基础矩阵相乘
            if ((power & 1) == 1) {
                result = multiplyMatrix(result, base);
            }
            // 将基础矩阵平方，相当于将幂次方除以2
            base = multiplyMatrix(base, base);
            // 将幂次方右移一位，相当于除以2，为下一轮迭代做准备
            power >>= 1;
        }
        // 返回计算得到的矩阵的幂次方结果
        return result;
    }

    /**
     * 乘法矩阵
     * 该方法用于计算两个矩阵的乘积
     * 在这个实现中，它专门用于处理26x26的矩阵，并且结果矩阵中的每个元素都是原始矩阵中对应元素乘积的模
     *
     * @param a 第一个矩阵，包含长整型元素
     * @param b 第二个矩阵，包含长整型元素
     * @return 返回两个输入矩阵乘积的结果矩阵，也是一个26x26的矩阵
     */
    private long[][] multiplyMatrix(long[][] a, long[][] b) {
        // 初始化结果矩阵，大小为26x26，所有元素默认初始化为0
        long[][] res = new long[26][26];

        // 遍历第一个矩阵的行
        for (int i = 0; i < 26; i++) {
            // 遍历第一个矩阵的列（同时也是第二个矩阵的行）
            for (int k = 0; k < 26; k++) {
                // 如果当前元素为0，则跳过当前循环，不进行无意义的乘法计算
                if (a[i][k] == 0) continue;

                // 遍历第二个矩阵的列，计算结果矩阵中每个元素的值
                for (int j = 0; j < 26; j++) {
                    // 计算并更新结果矩阵中的元素值，使用模运算防止溢出
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        // 返回计算得到的结果矩阵
        return res;
    }

    /**
     * 将一个向量与一个矩阵相乘
     * 此方法用于执行向量和矩阵的乘法运算，常用于线性代数或图论算法中
     * 它通过将向量中的每个非零元素与矩阵的对应行相乘，并将结果累加到结果向量中
     * 由于操作涉及大数，结果需要对MOD取模以防止溢出
     *
     * @param vec 一个长整型数组，代表输入的向量
     * @param mat 一个26x26的长整型二维数组，代表输入的矩阵
     * @return 返回一个长整型数组，代表向量与矩阵乘积的结果
     */
    private long[] multiply(long[] vec, long[][] mat) {
        // 初始化结果向量，长度为26，用于存放乘法结果
        long[] res = new long[26];

        // 遍历输入向量
        for (int i = 0; i < 26; i++) {
            // 如果当前向量元素为0，则跳过，不参与计算
            if (vec[i] == 0) continue;

            // 遍历矩阵的列
            for (int j = 0; j < 26; j++) {
                // 计算向量元素与矩阵对应行元素的乘积，并累加到结果向量中
                // 结果对MOD取模以防止溢出
                res[j] = (res[j] + vec[i] * mat[i][j]) % MOD;
            }
        }

        // 返回计算得到的结果向量
        return res;
    }

}
