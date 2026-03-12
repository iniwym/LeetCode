package T2603;

/**
 * @Description: 二分答案 + 并查集
 * @Author: iniwym
 * @Date: 2026-03-12
 * @Link: https://leetcode.cn/problems/maximize-spanning-tree-stability-with-upgrades/
 */
public class Code3600_MaximizeSpanningTreeStabilityWithUpgrades {

    /**
     * 并查集（Union-Find）数据结构实现
     * 用于高效管理不相交集合的合并与查询操作，支持路径压缩优化
     */
    class UnionFind {
        private final int[] fa; // 代表元数组，fa[i] 存储元素 i 的父节点

        public int cc; // 连通块个数，记录当前不相交集合的数量

        /**
         * 初始化并查集
         *
         * @param n 元素个数，初始时每个元素自成一个集合
         */
        UnionFind(int n) {
            // 初始化代表元数组，每个元素的父节点指向自己
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            // 初始连通块个数等于元素个数
            cc = n;
        }

        /**
         * 查找元素 x 所在集合的代表元（根节点）
         * 使用路径压缩优化，将查找路径上的所有节点直接连接到根节点
         *
         * @param x 要查找的元素
         * @return 返回 x 所在集合的代表元
         */
        public int find(int x) {
            // 递归查找根节点并进行路径压缩
            if (fa[x] != x) {
                // 将 x 的父节点直接设置为根节点
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }

        /**
         * 合并两个元素所在的集合
         *
         * @param from 第一个元素
         * @param to   第二个元素
         * @return 如果两个元素原本不在同一集合则返回 true，否则返回 false
         */
        public boolean merge(int from, int to) {
            // 分别找到两个元素所在集合的代表元
            int x = find(from);
            int y = find(to);

            // 如果代表元相同，说明已在同一集合，无需合并
            if (x == y) {
                return false;
            }

            // 将一个集合的代表元的父节点设为另一个代表元
            fa[x] = y;
            // 连通块个数减一
            cc--;
            return true;
        }
    }


    /**
     * 解决方案类，用于计算最大化生成树稳定性的问题
     */
    class Solution {
        /**
         * 计算最大稳定性值
         *
         * @param n     节点数量
         * @param edges 边的数组，每个元素包含 [起点，终点，边权，是否必选]
         * @param k     可以升级的边的数量
         * @return 返回最大稳定性值，如果无法构成生成树则返回 -1
         */
        public int maxStability(int n, int[][] edges, int k) {
            // 初始化两个并查集：mustUf 用于处理必选边，allUf 用于检查全图连通性
            UnionFind mustUf = new UnionFind(n);
            UnionFind allUf = new UnionFind(n);
            int minS = Integer.MAX_VALUE;
            int maxS = 0;

            // 遍历所有边，处理必选边并构建全图，同时记录边权的最小值和最大值
            for (int[] e : edges) {
                int x = e[0], y = e[1], s = e[2], must = e[3];
                // 如果必选边形成环，则无法构成生成树
                if (must > 0 && !mustUf.merge(x, y)) {
                    return -1;
                }
                // 将所有边加入全图并查集
                allUf.merge(x, y);
                // 更新边权的最小值和最大值
                minS = Math.min(minS, s);
                maxS = Math.max(maxS, s);
            }

            // 检查全图是否连通
            if (allUf.cc > 1) {
                return -1;
            }

            // 二分查找最大稳定性值
            int left = minS;
            int right = maxS * 2 + 1;

            // 在 [minS, maxS*2+1] 范围内二分查找满足条件的最大稳定性值
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                // 检查 mid 是否可行
                if (check(mid, n, edges, k)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        /**
         * 检查给定的稳定性阈值是否可行
         *
         * @param low   稳定性阈值（二分查找的中间值）
         * @param n     节点数量
         * @param edges 边的数组
         * @param k     可以升级的边的数量
         * @return 如果能在给定条件下构成生成树则返回 true，否则返回 false
         */
        private boolean check(int low, int n, int[][] edges, int k) {
            // 初始化并查集用于构建生成树
            UnionFind u = new UnionFind(n);

            // 第一轮：添加所有必选边和边权大于等于 low 的可选边
            for (int[] e : edges) {
                int x = e[0], y = e[1], s = e[2], must = e[3];
                // 必选边的边权必须大于等于 low，否则无法满足条件
                if (must > 0 && s < low) {
                    return false;
                }
                // 添加必选边或边权足够的可选边
                if (must > 0 || s >= low) {
                    u.merge(x, y);
                }
            }

            // 第二轮：使用升级机会添加边权不足但可以升级的边
            for (int[] e : edges) {
                // 如果没有升级次数或已经连通，则提前结束
                if (k == 0 || u.cc == 1) {
                    break;
                }
                int x = e[0], y = e[1], s = e[2], must = e[3];
                // 对于非必选边且边权小于 low 但升级后满足条件的边，使用一次升级机会
                if (must == 0 && s < low && s * 2 >= low && u.merge(x, y)) {
                    k--;
                }
            }

            // 检查最终是否形成了连通图
            return u.cc == 1;
        }
    }

}
