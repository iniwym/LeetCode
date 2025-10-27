package T2510;

/**
 * @Description: 设计题
 * @Author: iniwym
 * @Date: 2025-10-27
 * @Link: https://leetcode.cn/problems/number-of-laser-beams-in-a-bank/
 */
public class Code2125_NumberOfLaserBeamsInABank {

    /**
     * 计算银行中激光束的数量
     * 激光束只能在两行之间形成，且这两行之间不能有其他设备行
     *
     * @param bank 表示银行楼层平面图的字符串数组，每个字符为'0'或'1'
     *             '1'表示该位置有安全设备，'0'表示没有
     * @return 返回银行中激光束的总数量
     */
    public int numberOfBeams(String[] bank) {
        int ans = 0;
        int preCnt = 0;

        // 遍历每一行
        for (String row : bank) {
            int cnt = 0;

            // 统计当前行中安全设备的数量
            for (char ch : row.toCharArray()) {
                cnt += ch - '0';
            }

            // 如果当前行有安全设备，则与上一个有设备的行形成激光束
            if (cnt > 0) {
                ans += preCnt * cnt;
                preCnt = cnt;
            }
        }
        return ans;
    }

}

