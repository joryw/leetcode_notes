# dfs

#### [剑指 Offer 13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

```java
class Solution {
    int sum = 0;
    int[][] dp;
    public int movingCount(int m, int n, int k) {
        dp = new int[m][n];
        helper(m,n,k,0,0);
        return sum;
    }
    public void helper(int m, int n, int k, int i, int j) {
        if(i >= m || j >= n || i/10 + i%10 + j/10 + j%10 > k || dp[i][j] == 1){
            return;
        }
        dp[i][j]++;
        sum++;
        helper(m, n, k, i + 1, j);
        helper(m, n, k, i, j + 1);
    }
}
```

核心在于确定终止条件` if(i >= m || j >= n || i/10 + i%10 + j/10 + j%10 > k || dp[i][j] == 1)`

