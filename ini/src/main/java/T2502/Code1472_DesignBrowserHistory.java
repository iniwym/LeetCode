package T2502;

/**
 * @Description: 双指针数组
 * @Author: iniwym
 * @Date: 2025-02-26
 * @Link: https://leetcode.cn/problems/design-browser-history/
 */
public class Code1472_DesignBrowserHistory {

    /**
     * BrowserHistory类用于模拟浏览器的历史记录功能
     * 它允许用户访问新的网页，以及在历史记录中向前和向后导航
     */
    public static class BrowserHistory {
        // 定义历史记录的最大容量
        private static final int MAX = 5001;

        // 存储历史记录的数组
        private static String[] history;

        // r表示当前历史记录的末尾位置，cur表示当前浏览的页面位置
        int r, cur;

        /**
         * 构造函数，初始化浏览器历史记录，并将首页添加到历史记录中
         *
         * @param homepage 主页URL
         */
        public BrowserHistory(String homepage) {
            history = new String[MAX];
            r = 0;
            cur = 0;
            history[cur] = homepage;
        }

        /**
         * 访问新网页，将其添加到历史记录中，并更新当前浏览位置
         * 同时，清除当前页面之后的历史记录
         *
         * @param url 要访问的新网页URL
         */
        public void visit(String url) {
            r = cur;
            history[++cur] = url;
            r++;
        }

        /**
         * 向后导航指定的步数，更新当前浏览位置，并返回该位置的网页URL
         *
         * @param steps 向后导航的步数
         * @return 当前浏览位置的网页URL
         */
        public String back(int steps) {
            cur = Math.max(cur - steps, 0);
            return history[cur];
        }

        /**
         * 向前导航指定的步数，更新当前浏览位置，并返回该位置的网页URL
         *
         * @param steps 向前导航的步数
         * @return 当前浏览位置的网页URL
         */
        public String forward(int steps) {
            cur = Math.min(cur + steps, r);
            return history[cur];
        }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        browserHistory.back(2);                   // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        browserHistory.back(7);                   // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"    }
    }
}