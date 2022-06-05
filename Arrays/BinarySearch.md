# BinarySearch

[TOC]

### [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays/)

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //合并数组，直接找数组中位
        int n = nums1.length, m = nums2.length, len = m + n;
        int[] arr = new int[len];
        int i = 0, j = 0, idx = 0;
        while(i < n && j < m) {
            arr[idx++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        while(i < n) {
            arr[idx++] = nums1[i++];
        }
        while(j < m) {
            arr[idx++] = nums2[j++];
        }
        return len % 2 == 0 ? (arr[len / 2 - 1] + arr[len / 2]) / 2.0 : arr[len / 2];
    }
}
```

组成一个有序数组，再找中位数

### [287. 寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/)

#### 二分查找

```java
class Solution {
    public int findDuplicate(int[] nums) {
        //只有一个重复数，根据抽屉原理，会被二分法分布
        int left = 1, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int cnt = lowMidCnt(nums, mid);
            //至少有1个mid数量，算上了mid 还小，则说明右边数字更多，重复值在右边
            if(cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int lowMidCnt(int[] nums, int mid) {
        int cnt = 0;
        for(int i = 0; i <  nums.length; i++) {
            if(nums[i] <= mid) {
                cnt++;
            }
        }
        return cnt;
    }
}
```

**可以使用「二分查找」的原因**
因为题目要找的是一个 整数，并且这个整数有明确的范围，所以可以使用「二分查找」。

**重点理解：**这个问题使用「二分查找」是在数组 [1, 2,.., n] 中查找一个整数，而 并非在输入数组数组中查找一个整数。
使用 while (left < right) 与 right = mid; 和 left = mid + 1; 配对的写法是为了保证退出循环以后 left 与 right 重合，left （或者 right）就是我们要找的重复的整数；

#### 快慢指针

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
```

##### 思路

数组[1,3,4,2,2] 映射关系为

```
0->1
1->3
2->4
3->2
4->2
```

抽象链表如下图所示

![287.png](BinarySearch.assets/999e055b41e499d9ac704abada4a1b8e6697374fdfedc17d06b0e8aa10a8f8f6-287.png)

就可以通过快慢链表，首先找到是否存在环，再从起点触发找交点则为环的起始点。

### [378. 有序矩阵中第 K 小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/)

#### 二分查找

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        //采用左闭右开
        while(left < right) {
            int mid = left + ((right - left) >> 1);
            int cnt = countLowMidNum(matrix, mid, n);
            if(cnt < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int countLowMidNum(int[][] matrix, int mid, int n) {
        int i = n - 1, j = 0, cnt = 0;
        while(i >= 0 && j < n) {
            if(matrix[i][j] <= mid) {
                //这里是累加
                cnt += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return cnt;
    }
}
```

找到中点值，按照下图路线计算小于等于该值得数量，

进行二分比较，更新left、right值

![fig3](BinarySearch.assets/378_fig3.png)

#### 优先队列

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while(left < right) {
            int mid = left + (right - left) << 1;
            int cnt = countLowMidNum(matrix, mid, n);
            if(cnt < mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
```

### [704. 二分查找](https://leetcode-cn.com/problems/binary-search/)

#### 左闭右闭

```java
class Solution {
    public int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        if(target <  nums[i] || target > nums[j]) return -1;
        while(i < j) {
            int m = i +(j - i) / 2;
            if(nums[m] > target) {
                j = m - 1;
            } else if(nums[m] < target) {
                i = m + 1;
            } else {
                i = j = m;
            }
        }
        return target == nums[i] ? i : - 1;
    }
}
```

##### 代码优化

```java
class Solution {
    public int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        if(target <  nums[i] || target > nums[j]) return -1;
        while(i <= j) {
            int m = i +(j - i) / 2;
            if(nums[m] > target) {
                j = m - 1;
            } else if(nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return - 1;
    }
}
```

#### 左闭右开

```java
class Solution {
    public int search(int[] nums, int target) {
        int i = 0, j = nums.length;
        while(i < j) {
            int m = i +(j - i) / 2;
            if(nums[m] > target) {
                j = m;
            } else if(nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return - 1;
    }
}	
```

