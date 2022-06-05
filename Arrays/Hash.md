# Hash

### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0; i <nums.length; i++){
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
```



### [645. 错误的集合](https://leetcode-cn.com/problems/set-mismatch/)

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length, j = 0, k = 0;
        int[] cnt = new int[n + 1];
        for(int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }
        for(int i = 1; i <= n; i++) {
            if(cnt[i] == 0) {
                k = i;
            }
            if(cnt[i] == 2) {
                j = i;
            }
        }
        return new int[]{j, k};
    }
}
```

### [697. 数组的度](https://leetcode-cn.com/problems/degree-of-an-array/)

```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        int n = nums.length, max = 0, min = Integer.MAX_VALUE, cur = 0, ans = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        int[] cnt = new int[50000];
        for(int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
            cur = Math.max(cur, cnt[nums[i]]);
            map.put(nums[i], i);
        }
        for(int i = min; i <= max; i++) {
            if(cnt[i] == cur) {
                for(int j = 0; j < nums.length; j++) {
                    if(nums[j] == i) {
                        ans = Math.min(ans, map.get(i) - j + 1);
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
```

1. 计数，并找出最大度数，并记录每个数最后一个值的位置
2. 通过最大度数值 逐个进行计算最大连续数组。

### [565. 数组嵌套](https://leetcode-cn.com/problems/array-nesting/)

#### 哈希

```java
class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length, max = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int j = i, cnt = 0;
            while(!set.contains(nums[j])) {
                set.add(nums[j]);
                cnt++;
                j = nums[j];
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
```

1. 通过set存储计算过的位置，

#### 原地哈希

```java
class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length, max = 0;
        for(int i = 0; i < n; i++) {
            int j = i, cnt = 0;
            while(nums[j] != -1) {
                int tmp = nums[j];
                nums[j] = -1;
                cnt++;
                j = tmp;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
```

