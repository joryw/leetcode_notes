# DifferentialArray

### [1109. 航班预订统计](https://leetcode-cn.com/problems/corporate-flight-bookings/)

#### 暴力

```java
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for(int i = 0; i < bookings.length; i++) {
            for(int j = bookings[i][0]; j <= bookings[i][1]; j++) {
                ans[j - 1] +=  bookings[i][2];
            }
        }
        return ans;
    }
}
```

#### 差分

```java
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for(int[] booking : bookings) {
            ans[booking[0] - 1] += booking[2];
            if(booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }
        for(int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
```

理解为公交车， `booking[0] - 1`为进站人数，  `booking[1]`为离站人数， ans数组计算其中人数变化，

最后通过`ans[i] += ans[i - 1];`一次性累加得到最终答案

### [1094. 拼车](https://leetcode-cn.com/problems/car-pooling/)

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] counter = new int[1001];
        int max = 0, sum = 0;
        for(int[] trip : trips) {
            max = Math.max(max, trip[2]);
            counter[trip[1]] += trip[0];
            counter[trip[2]] -= trip[0]; 
        }
        for(int i = 0; i <= max; i++) {
            sum += counter[i];
            if(sum > capacity) {
                return false;
            }
        }
        return true;
    }
}
```

思路同上