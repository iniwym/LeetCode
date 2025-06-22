package T2506;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-06-22
 * @Link: https://leetcode.cn/problems/divide-a-string-into-groups-of-size-k/
 */
public class Code2138_DivideAStringIntoGroupsOfSizeK {

    /**
     * 将字符串分割成等长的子串数组
     * 如果字符串不能被等分，使用指定字符填充末尾
     *
     * @param s    待分割的字符串
     * @param k    每个子串的长度
     * @param fill 用于填充的字符
     * @return 分割后的子串数组
     */
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        // 计算分组数量，确保所有字符都被分割
        int length = (n + k - 1) / k;
        String[] ans = new String[length];

        // 遍历字符串，每k个字符分割一次
        for (int i = 0; i < n; i += k) {
            StringBuilder sb = new StringBuilder();
            // 每次分割出k个字符，不足k个时用fill字符填充
            for (int j = 0; j < k; j++) {
                if (i + j < n) {
                    // 当前位置有字符时，追加到子串中
                    sb.append(s.charAt(i + j));
                } else {
                    // 当前位置无字符时，用fill字符填充
                    sb.append(fill);
                }
            }
            // 将构建好的子串放入结果数组中
            ans[i / k] = sb.toString();
        }

        // 返回分割后的子串数组
        return ans;
    }
}
