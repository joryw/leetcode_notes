# SlidingWindow

### [209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0, j = 0, ans = Integer.MAX_VALUE;
        while(j < n) {
            if(target > 0){
                target -= nums[j++]; 
            } else{
                ans = Math.min(ans, j - i);
                target += nums[i++];
            }
        }
        while(target <= 0) {
            ans = Math.min(ans, j - i);
            target += nums[i++];
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
```

#### 代码优化

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, ans = Integer.MAX_VALUE, sum = 0;
        for(int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while(sum >= target) {
                ans = Math.min(ans, j - i + 1);
                sum -= nums[i++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0: ans;
    }
}
```

