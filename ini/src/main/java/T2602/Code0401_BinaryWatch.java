package T2602;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-19
 * @Link: https://leetcode.cn/problems/binary-watch/
 */
public class Code0401_BinaryWatch {

    /**
     * 读取二进制手表的所有可能时间。
     * <p>
     * 该函数根据给定的亮灯数量（turnedOn），计算所有可能的时间表示。
     * 手表由4个LED表示小时（0-11）和6个LED表示分钟（0-59）组成，
     * 每个LED代表一个二进制位。函数遍历所有合法的小时和分钟组合，
     * 并筛选出其二进制表示中1的总数等于turnedOn的时间。
     *
     * @param turnedOn 表示当前亮着的LED数量
     * @return 返回所有符合条件的时间字符串列表，格式为 "H:MM"
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();

        // 遍历所有可能的小时（0-11）和分钟（0-59）
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                // 判断当前小时和分钟的二进制中1的总个数是否等于turnedOn
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    // 将符合条件的时间格式化并加入结果列表
                    ans.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return ans;
    }

}
