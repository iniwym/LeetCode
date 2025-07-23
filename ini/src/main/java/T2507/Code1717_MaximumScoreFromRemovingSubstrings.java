package T2507;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-07-23
 * @Link: https://leetcode.cn/problems/maximum-score-from-removing-substrings/
 */
public class Code1717_MaximumScoreFromRemovingSubstrings {

    /**
     * 使用贪心算法计算字符串中通过交换字符'a'和'b'所能获得的最大收益
     *
     * @param s 输入的字符串，只包含小写字母
     * @param x 交换'a'和'b'的收益值
     * @param y 交换'b'和'a'的收益值
     * @return 返回通过交换字符所能获得的最大收益
     */
    public int maximumGain(String s, int x, int y) {
        // 获取字符串长度
        int n = s.length();
        // 将字符串转换为字符数组，便于处理
        char[] chars = s.toCharArray();

        // 如果x小于y，则交换x和y，确保总是先移除'a'再移除'b'，以最大化收益
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;

            // 交换字符串中的'a'和'b'，以简化后续的处理逻辑
            for (int i = 0; i < n; i++) {
                if (chars[i] == 'a') {
                    chars[i] = 'b';
                } else if (chars[i] == 'b') {
                    chars[i] = 'a';
                }
            }
        }

        // 初始化指针i和总收益ret
        int i = 0;
        int ret = 0;
        // 遍历字符数组
        while (i < n) {
            // 跳过所有非'a'和非'b'的字符
            while (i < n && (chars[i] != 'a' && chars[i] != 'b')) {
                i++;
            }

            // 初始化'a'和'b'的计数器
            int ca = 0, cb = 0;
            // 统计连续的'a'和'b'字符
            while (i < n && (chars[i] == 'a' || chars[i] == 'b')) {
                if (chars[i] == 'a') {
                    ca++;
                } else {
                    // 如果前面有'a'，则与当前的'b'配对，增加收益
                    if (ca > 0) {
                        ca--;
                        ret += x;
                    } else {
                        // 否则，单独计数'b'
                        cb++;
                    }
                }
                i++;
            }

            // 将剩余未配对的'a'和'b'中较小的数量乘以y，加入总收益
            ret += Math.min(ca, cb) * y;
        }
        // 返回最大收益
        return ret;
    }
}
