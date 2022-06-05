# DoublePointer

### [26. 删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int pre = Integer.MIN_VALUE, k = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] != pre) {
                nums[k++] = nums[i];
                pre = nums[i];
            }
        }
        return k;
    }
}
```

### [27. 移除元素](https://leetcode-cn.com/problems/remove-element/)

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length, j = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
```

### 

### [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

```java
class Solution {
    public int trap(int[] height) {
        int i = 0, j = height.length - 1, left = 0, right = 0, sum = 0;
        while(i <= j) {
            if(left <= right) {
                if(height[i] < left) {
                    sum += left - height[i];
                } else {
                    left = height[i];
                }
                i++;
            } else {
                if(height[j] < right) {
                    sum += right - height[j];
                } else {
                    right = height[j];
                }
                j--;
            }
        }
        return sum;
    }
}
```

* 当左边边界小于等于右边边界，从左往右扫描
  * 当前扫描索引高度小于左边界，说明积水，进行累加
  * 否则，提高左边界高度。
* 否则，从右往左扫描
  * 同理

### [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        m--;
        n--;
        while(m >= 0 && n >= 0) {
            if(nums1[m] > nums2[n]) {
                nums1[k--] = nums1[m--];
            } else {
                nums1[k--] = nums2[n--];
            }
        }
        while(n >= 0) {
            nums1[k--] = nums2[n--];
        }
    }
}
```



### [167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length, i = 0, j = n - 1;
        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if(sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{1, 2};
    }
}
```

### [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
            if(nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        while(i < nums.length) {
            nums[i++] = 0;
        }
    }
}
```

### [633. 平方数之和](https://leetcode-cn.com/problems/sum-of-square-numbers/)

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        long i = 0, j = (long)Math.pow(c, 0.5);
        while(i <= j) {
            long sum = i * i + j * j;
            if(sum < c) {
                i++;
            } else if (sum > c) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

![image.png](DoublePointer.assets/1623185846-LKBLqt-image-16506106823165.png)

### [977. 有序数组的平方](https://leetcode-cn.com/problems/squares-of-a-sorted-array/)

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1, k = j;
        int[] ans = new int[j + 1];
        while(i <= j) {
            if(Math.abs(nums[i]) > Math.abs(nums[j])) {
                ans[k--] = nums[i] * nums[i++];
            } else {
                ans[k--] = nums[j] * nums[j--];
            }
        }
        return ans;
    }
}
```

#### 代码2

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1, k = j;
        int[] ans = new int[j + 1];
        while(i <= j) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if(a > b) {
                ans[k--] = a;
                i++;
            } else {
                ans[k--] = b;
                j--;
            }
        }
        return ans;
    }
}
```

