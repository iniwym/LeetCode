package T2507;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 字典树(Trie)
 * @Author: iniwym
 * @Date: 2025-07-20
 * @Link: https://leetcode.cn/problems/delete-duplicate-folders-in-system/
 */
public class Code1984_DeleteDuplicateFoldersInSystem {
    /**
     * 字典树节点类
     */
    class Trie {
        String serial; // 当前节点结构的序列化表示（用于比较子树结构是否相同）
        Map<String, Trie> children = new HashMap<>(); // 存储子节点，key为文件夹名
    }

    /**
     * 删除重复文件夹的主方法
     *
     * @param paths 输入的文件夹路径列表，每个路径是一个字符串列表
     * @return 删除重复文件夹后剩余的路径列表
     */
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie(); // 创建字典树的根节点

        // 构建字典树：将每个路径插入到字典树中
        for (List<String> path : paths) {
            Trie cur = root;
            for (String node : path) {
                cur.children.putIfAbsent(node, new Trie());
                cur = cur.children.get(node);
            }
        }

        Map<String, Integer> freq = new HashMap<>(); // 记录每个序列化表示的出现频率
        construct(root, freq); // 后序遍历生成每个节点的序列化表示

        List<List<String>> ans = new ArrayList<>();
        operate(root, freq, new ArrayList<>(), ans); // 遍历字典树，收集非重复路径
        return ans;
    }

    /**
     * 后序遍历字典树，生成每个节点的序列化表示
     * 序列化规则：将子节点的键和序列化值组合并排序，形成唯一标识
     *
     * @param node 当前处理的字典树节点
     * @param freq 用于记录序列化出现次数的哈希表
     */
    private void construct(Trie node, Map<String, Integer> freq) {
        if (node.children.isEmpty()) return; // 叶节点无需序列化

        List<String> v = new ArrayList<>();
        // 递归处理所有子节点
        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            construct(entry.getValue(), freq);
            v.add(entry.getKey() + "(" + entry.getValue().serial + ")");
        }

        Collections.sort(v); // 排序以保证相同结构生成相同序列
        StringBuilder sb = new StringBuilder();
        for (String s : v) {
            sb.append(s);
        }
        node.serial = sb.toString();
        freq.put(node.serial, freq.getOrDefault(node.serial, 0) + 1); // 更新频率计数
    }

    /**
     * 遍历字典树，收集未被标记为重复的路径
     *
     * @param node 当前处理的字典树节点
     * @param freq 序列化出现次数的哈希表
     * @param path 当前路径的累积
     * @param ans  存储最终结果的列表
     */
    private void operate(Trie node, Map<String, Integer> freq, List<String> path, List<List<String>> ans) {
        // 如果当前节点的序列化出现超过1次，跳过其所有子节点
        if (freq.getOrDefault(node.serial, 0) > 1) return;

        if (!path.isEmpty()) {
            ans.add(new ArrayList<>(path)); // 将当前路径加入结果
        }

        // 递归处理子节点
        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            path.add(entry.getKey());
            operate(entry.getValue(), freq, path, ans);
            path.remove(path.size() - 1); // 回溯
        }
    }
}

