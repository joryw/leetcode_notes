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

#### 二分查找

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //分别上下取整找中位数
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //奇偶情况合并，除2.0保留浮点数形式
        return (findKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + findKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) / 2.0;
    }

    public int findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //保证len1长度比较小
        if(len1 > len2) {
            return findKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if(len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;
        if(nums1[i] > nums2[j]) {
            return findKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return findKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
```

为找第k位，先找到两个数组的k/2位，去掉较小值前部分，更新k值，以此类推

![image.png](https://pic.leetcode-cn.com/09b8649cd2b8bbea74f7f632b098fed5f8404530ff44b5a0b54a360b3cf7dd8f-image.png)

#### 数组切割

```JAVA
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if(n > m) return findMedianSortedArrays(nums2, nums1);

        int iMin = 0, iMax = n;
        while(iMin <= iMax) {
            int i = iMin + (iMax - iMin) / 2;
            int j = (m + n + 1) / 2 - i;

            if(i != 0 && j != m && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            }
            else if(j != 0 && i != n && nums1[i] < nums2[j - 1]) {
                iMin = i + 1;
            } else {
                int leftMid = 0;
                if(i == 0) {
                    leftMid = nums2[j - 1];
                } else if (j == 0) {
                    leftMid = nums1[i - 1];
                } else {
                    leftMid = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if((m + n) % 2 != 0) {
                    return leftMid;
                }
                int rightMid = 0;
                if(i == n) {
                    rightMid = nums2[j];
                } else if(j == m) {
                    rightMid = nums1[i];
                } else {
                    rightMid = Math.min(nums1[i], nums2[j]);
                }
                return (leftMid + rightMid) / 2.0;
            }
        }
        return 0.0;
    }
}
```

[题解](https://leetcode.cn/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/[)中解法4

### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

#### 方案1:二分找target的上一个值，供target+1复用

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) {
            return new int[]{-1, -1};
        }
        int right = search(nums, target + 1);
        if(right == -1 ||nums[right] != target) {
            return new int[]{-1, -1};
        }
        int left = search(nums, target) + 1;
        return new int[]{left, right};
    }

    public int search(int[] nums, int target) {
        int left = -1, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left + 1) / 2;
            if(nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
```

##### 思路

1. 当target+1传入的值，找不到nums[right] != target，则说明不存在该值
2. 处理好边界条件，如`int left = -1`,`if(right == -1`等

#### 方案2:写找开头跟找结尾两种二分查找。

### [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx/)

```java
class Solution {
    public int mySqrt(int x) {
        if(x < 2) return x;
        long left = 1, right = x / 2;
        while(left < right) {
            //这里通过+1，实现了取中点时取右节点，防止剩下两个节点时重复执行left=mid
            long mid = left + (right - left + 1) / 2;
            if(mid * mid > x) {
                right = mid - 1;
            } else if (mid * mid < x){
                left = mid;
            } else {
                return (int)mid;
            }
        }
        return (int)left;
    }
}
```

### [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int flat = nums[right];
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < flat) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
```



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

### [540. 有序数组中的单一元素](https://leetcode.cn/problems/single-element-in-a-sorted-array/)

#### 方案1:异或

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for(int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
```

#### 方案2:二分

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(mid < nums.length - 1 && mid % 2 == 0 && nums[mid] == nums[mid + 1] || mid > 0 && mid % 2 == 1 && nums[mid] == nums[mid - 1]) {
                left = mid + 1;
            } else if (mid < nums.length - 1 && mid % 2 == 1 && nums[mid] == nums[mid + 1] || mid > 0 && mid % 2 == 0 && nums[mid] == nums[mid - 1]) {
                right = mid - 1;
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
```

分情况讨论

1. mid为偶数，Vmid == V(mid - 1) , right = mid - 1；
2. mid为偶数，Vmid == V(mid +1)，left = mid + 1;
3. mid为奇数，Vmid == V(mid - 1)，left = mid + 1；
4. mid为奇数，Vmid == (mid + 1)，right = mid - 1； 

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

