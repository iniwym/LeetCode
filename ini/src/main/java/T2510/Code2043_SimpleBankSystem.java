package T2510;

/**
 * @Description: 设计题
 * @Author: iniwym
 * @Date: 2025-10-26
 * @Link: https://leetcode.cn/problems/simple-bank-system/
 */
public class Code2043_SimpleBankSystem {

    /**
     * 银行类，用于管理多个账户的余额操作
     */
    class Bank {
        private final long[] b;

        /**
         * 构造函数，初始化银行账户余额数组
         *
         * @param balance 账户余额数组，数组长度表示账户数量
         */
        public Bank(long[] balance) {
            b = balance;
        }

        /**
         * 转账操作，从一个账户向另一个账户转账指定金额
         *
         * @param account1 转出账户编号（从1开始）
         * @param account2 转入账户编号（从1开始）
         * @param money    转账金额
         * @return 操作成功返回true，失败返回false
         */
        public boolean transfer(int account1, int account2, long money) {
            // 验证账户是否存在以及转出账户余额是否充足
            if (account1 > b.length || account2 > b.length || b[account1 - 1] < money) {
                return false;
            }
            b[account1 - 1] -= money;
            b[account2 - 1] += money;
            return true;
        }

        /**
         * 存款操作，向指定账户存入指定金额
         *
         * @param account 账户编号（从1开始）
         * @param money   存款金额
         * @return 操作成功返回true，失败返回false
         */
        public boolean deposit(int account, long money) {
            // 验证账户是否存在
            if (account > b.length) {
                return false;
            }
            b[account - 1] += money;
            return true;
        }

        /**
         * 取款操作，从指定账户取出指定金额
         *
         * @param account 账户编号（从1开始）
         * @param money   取款金额
         * @return 操作成功返回true，失败返回false
         */
        public boolean withdraw(int account, long money) {
            // 验证账户是否存在以及账户余额是否充足
            if (account > b.length || b[account - 1] < money) {
                return false;
            }
            b[account - 1] -= money;
            return true;
        }
    }

}
