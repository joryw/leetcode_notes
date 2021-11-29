/**
 * @author wjunchao
 * @create 2021-11-29 19:17
 */
public class CowProduction {
    public static void main(String[] args) {
        int n = 6;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }
        System.out.println(dp[n]);
    }
}
