package T2507;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 排序
 * @Author: iniwym
 * @Date: 2025-07-19
 * @Link: https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
 */
public class Code1233_RemoveSubFoldersFromTheFilesystem {

    /**
     * 从给定的文件夹路径数组中移除所有子文件夹，返回不包含子文件夹的路径列表。
     * <p>
     * 算法思路：
     * 1. 首先对文件夹路径数组进行字典序排序，这样父文件夹会自然排在子文件夹前面
     * 2. 遍历排序后的数组，检查当前路径是否是结果列表中最后一个路径的子文件夹
     * 3. 如果不是子文件夹，则将其加入结果列表
     *
     * @param folder 包含文件夹路径的字符串数组，路径格式为以'/'分隔的字符串
     * @return 不包含子文件夹的路径列表，列表中每个路径都不是其他路径的子文件夹
     */
    public List<String> removeSubfolders(String[] folder) {
        // 对文件夹路径进行字典序排序
        Arrays.sort(folder);

        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);

        // 遍历排序后的文件夹路径
        for (int i = 1; i < folder.length; i++) {
            String s = folder[i];
            String last = ans.get(ans.size() - 1);

            /**
             * 检查当前路径是否是最后一个添加路径的子文件夹
             * 条件1: 不以最后一个路径开头
             * 条件2: 或者虽然是前缀但在后面没有紧跟'/'(确保是真正的父文件夹)
             */
            if (!s.startsWith(last) || s.charAt(last.length()) != '/') {
                ans.add(s);
            }
        }

        return ans;
    }

}
