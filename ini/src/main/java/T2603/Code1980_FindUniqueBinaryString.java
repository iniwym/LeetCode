package T2603;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 暴力枚举
 * @Author: iniwym
 * @Date: 2026-03-08
 * @Link: https://leetcode.cn/problems/find-unique-binary-string/
 */
public class Code1980_FindUniqueBinaryString {

    /**
     * 查找一个不在给定数组中的二进制字符串
     *
     * @param nums 包含 n 个互不相同的二进制字符串的数组，每个字符串长度为 n
     * @return 返回一个长度为 n 的二进制字符串，该字符串不在数组 nums 中
     * 解题思路：将所有二进制字符串转换为整数存入集合，然后从 0 开始枚举找到第一个未出现的数字
     */
    public String findDifferentBinaryString(String[] nums) {
        // 使用集合存储所有已存在的二进制字符串对应的十进制数值
        Set<Integer> set = new HashSet<>();
        // 遍历数组，将二进制字符串转换为十进制整数并加入集合
        for (String s : nums) {
            set.add(Integer.parseInt(s, 2));
        }

        // ans 记录第一个未在集合中出现的非负整数
        int ans = 0;
        // 循环递增直到找到一个不在集合中的数字
        while (set.contains(ans)) {
            ans++;
        }

        // 将找到的数字转换回二进制字符串形式
        String bin = Integer.toBinaryString(ans);
        // 使用 StringBuilder 构建最终结果，需要在前面补零以保证长度为 n
        StringBuilder sb = new StringBuilder();
        // 在二进制字符串前补充足够的 '0'，使其总长度等于 nums.length
        for (int i = 0; i < nums.length - bin.length(); i++) {
            sb.append('0');
        }
        sb.append(bin);
        return sb.toString();
    }
}
