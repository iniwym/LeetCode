package T2509;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2025-09-21
 * @Link: https://leetcode.cn/problems/design-movie-rental-system/
 */
public class Code1912_DesignMovieRentalSystem {

    /**
     * 电影租赁系统类，支持查询未借出电影、借电影、还电影和报告已借出电影等功能。
     */
    class MovieRentingSystem {
        // (shop, movie) -> price
        private final Map<Long, Integer> shopMovieToPrice = new HashMap<>();

        // movie -> {(price, shop)}
        private final Map<Integer, TreeSet<int[]>> unrentedMovieToPriceShop = new HashMap<>();

        // {(price, shop, movie)}
        private final TreeSet<int[]> rentedMovies = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });

        /**
         * 构造函数，初始化电影租赁系统。
         *
         * @param n       商店数量（未使用，但保留用于扩展）
         * @param entries 电影信息数组，每个元素为 [shop, movie, price]
         */
        public MovieRentingSystem(int n, int[][] entries) {
            for (int[] e : entries) {
                int shop = e[0], movie = e[1], price = e[2];
                // 把 shop 和 movie 存储到一个 long 中，方便作为 HashMap 的 key
                shopMovieToPrice.put((long) shop << 32 | movie, price);
                unrentedMovieToPriceShop
                        .computeIfAbsent(movie, new java.util.function.Function<Integer, TreeSet<int[]>>() {
                            @Override
                            public TreeSet<int[]> apply(Integer key) {
                                return new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
                            }
                        })
                        .add(new int[]{price, shop});
            }
        }

        /**
         * 搜索指定电影的前5个最便宜且未被借出的商店。
         *
         * @param movie 电影ID
         * @return 最多包含5个商店ID的列表，按价格升序排列
         */
        public List<Integer> search(int movie) {
            if (!unrentedMovieToPriceShop.containsKey(movie)) {
                return new ArrayList<>();
            }
            List<Integer> ans = new ArrayList<>(5); // 预分配空间
            for (int[] ps : unrentedMovieToPriceShop.get(movie)) {
                ans.add(ps[1]);
                if (ans.size() == 5) {
                    break;
                }
            }
            return ans;
        }

        /**
         * 标记指定商店的指定电影为已借出。
         *
         * @param shop  商店ID
         * @param movie 电影ID
         */
        public void rent(int shop, int movie) {
            int price = shopMovieToPrice.get((long) shop << 32 | movie);
            // 从 unrentedMovieToPriceShop 移到 rentedMovies
            unrentedMovieToPriceShop.get(movie).remove(new int[]{price, shop});
            rentedMovies.add(new int[]{price, shop, movie});
        }

        /**
         * 将指定商店的指定电影标记为已归还。
         *
         * @param shop  商店ID
         * @param movie 电影ID
         */
        public void drop(int shop, int movie) {
            int price = shopMovieToPrice.get((long) shop << 32 | movie);
            // 从 rentedMovies 移到 unrentedMovieToPriceShop
            rentedMovies.remove(new int[]{price, shop, movie});
            unrentedMovieToPriceShop.get(movie).add(new int[]{price, shop});
        }

        /**
         * 获取当前已借出电影中价格最低的前5部电影及其商店信息。
         *
         * @return 最多包含5个 [shop, movie] 列表的列表，按价格、商店ID、电影ID排序
         */
        public List<List<Integer>> report() {
            List<List<Integer>> ans = new ArrayList<>(5); // 预分配空间
            for (int[] e : rentedMovies) {
                ans.add(Arrays.asList(e[1], e[2]));
                if (ans.size() == 5) {
                    break;
                }
            }
            return ans;
        }
    }

}
