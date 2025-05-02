package T2505;

/**
 * @Description: 正序逆序模拟
 * @Author: iniwym
 * @Date: 2025-05-02
 * @Link: https://leetcode.cn/problems/push-dominoes/
 */
public class Code0838_PushDominoes {
    /**
     * 推倒多米诺骨牌
     * <p>
     * 当一个多米诺骨牌被推倒时，它会向一个方向倒下，直到遇到另一个已经倒下的多米诺骨牌或墙壁
     * 这个方法模拟了这一过程，给定一串多米诺骨牌的初始状态，返回所有骨牌静止后的最终状态
     *
     * @param dominoes 多米诺骨牌的初始状态字符串，其中 'R' 表示向右倒，'L' 表示向左倒，'.' 表示直立
     * @return 最终状态的多米诺骨牌字符串
     */
    public String pushDominoes(String dominoes) {
        // 将输入字符串转换为字符数组，便于逐个处理
        char[] chars = dominoes.toCharArray();
        // 获取多米诺骨牌的数量
        int n = chars.length;
        // r数组用于记录每个位置右侧最近的向右倒的骨牌位置
        int[] r = new int[n];
        // l数组用于记录每个位置左侧最近的向左倒的骨牌位置
        int[] l = new int[n];

        // 初始化右侧最近的向右倒的骨牌位置
        for (int i = 0, temp = -1; i < n; i++) {
            if (chars[i] == 'R') {
                temp = i;
            } else if (chars[i] == 'L') {
                temp = -1;
            }
            r[i] = temp;
        }

        // 初始化左侧最近的向左倒的骨牌位置
        for (int i = n - 1, temp = -1; i >= 0; i--) {
            if (chars[i] == 'L') {
                temp = i;
            } else if (chars[i] == 'R') {
                temp = -1;
            }
            l[i] = temp;
        }

        // 根据左右最近的倒下骨牌位置，确定每个直立骨牌的最终状态
        for (int i = 0; i < n; i++) {
            if (chars[i] == '.' && l[i] != -1 || r[i] != -1) {
                if (l[i] == -1) {
                    chars[i] = 'R';
                } else if (r[i] == -1) {
                    chars[i] = 'L';
                } else if (l[i] - i > i - r[i]) {
                    chars[i] = 'R';
                } else if (l[i] - i < i - r[i]) {
                    chars[i] = 'L';
                }
            }
        }
        // 将处理后的字符数组转换回字符串，作为最终的多米诺骨牌状态返回
        return String.valueOf(chars);
    }
}
