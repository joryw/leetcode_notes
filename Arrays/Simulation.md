# 	Simulation（模拟）

### [48. 旋转图像](https://leetcode-cn.com/problems/rotate-image/)

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i <= n / 2; i++) {
            for(int j = i; j < m - i - 1; j++) {
                int pre1 = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                int pre2 = matrix[j][m - i - 1];
                matrix[j][m - i - 1] = pre1;
                pre1 = matrix[n - i - 1][m - j - 1];
                matrix[n - i - 1][m - j - 1] = pre2;
                matrix[n - j - 1][i] = pre1;
            }
        }
    }
}

//代码优化
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i <= n / 2; i++) {
            for(int j = i; j < m - i - 1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][m - j - 1];
                matrix[n - i - 1][m - j - 1] = matrix[j][m - i - 1];
                matrix[j][m - i - 1] = tmp;
            }
        }
    }
}
```

弄清轮旋转的位置关系

### [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList();
        int n = matrix.length, m = matrix[0].length, l = 0, t = 0, r = m - 1, d = n - 1;
        while(l <= r) {
            for(int i = l ; i <= r && t <= d; i++) {
                list.add(matrix[t][i]);
            }
            t++;
            for(int i = t; i <= d && l <= r; i++) {
                list.add(matrix[i][r]);
            }
            r--;
            for(int i = r; i >= l && t <= d; i--) {
                list.add(matrix[d][i]);
            }
            d--;
            for(int i = d; i >= t && l <= r ; i--) {
                list.add(matrix[i][l]);
            }
            l++;
        } 
        return list;
    }
}
```



### [59. 螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/)

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int l = 0, t = 0, r = n - 1, d = n - 1, num = 1;
        while(l <= r) {
            for(int i = l; i <= r; i++) {
                ans[t][i] = num++;
            }
            t++;
            for(int i = t; i <= d; i++) {
                ans[i][r] = num++;
            }
            r--;
            for(int i = r; i >= l; i--) {
                ans[d][i] = num++;
            }
            d--;            
            for(int i = d; i >= t; i--) {
                ans[i][l] = num++;
            }
            l++;
        }
        return ans;
    }
}
```

### [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int i = 0, j = m - 1;
        while(i < n && j >= 0) {
            if(target > matrix[i][j]) {
                i++;
            } else if(target < matrix[i][j]) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

### [485. 最大连续 1 的个数](https://leetcode-cn.com/problems/max-consecutive-ones/)

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, max = 0;
        for(int num : nums) {
            if(num == 1) {
                cnt++;
            } else {
                max = Math.max(max, cnt);
                cnt = 0;
            }
        }
        return cnt > max ? cnt : max; 
    }
}
```

### [566. 重塑矩阵](https://leetcode-cn.com/problems/reshape-the-matrix/)

```java
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length, m = mat[0].length;
        if(r * c != n * m ) {
            return mat;
        }
        int[][] ans = new int[r][c];
        int num = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                ans[num / c][num % c] = mat[i][j];
                num++;
            }
        }
        return ans;
    }
}
```

### [667. 优美的排列 II](https://leetcode-cn.com/problems/beautiful-arrangement-ii/)

#### 翻转

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        if(k == 1) return arr;
        for(int i = 1; i < k; i++) {
            reverse(arr, i, n - 1);
        }
        return arr;
    }

    public void reverse(int[] arr, int i, int j) {
        while(i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
```

若n=8初始状态
1 2 3 4 5 6 7 8
k=1         | 1 2 3 4 5 6 7 8 (不翻转，直接返回)
k=2         1 | 8 7 6 5 4 3 2
k=3         1 8 | 2 3 4 5 6 7
k=4         1 8 2 | 7 6 5 4 3
总结规律：当k>1时,需要翻转的次数为k-1次，每次翻转保留前m(m = 1,2,3...k-1)个数，每次翻转都在原数组进行。

#### 构造

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int[] arr = new int[n];
        //等差数列
        for(int i = 0; i < n - k - 1; i++) {
            arr[i] = i + 1;
        }

        //交错数列
        int j = 0, left = n - k, right = n;
        for(int i = n - k - 1; i < n; i++) {
            if(j % 2 == 0) {
                arr[i] = left;
                left++;
            } else {
                arr[i] = right;
                right--;
            }
            j++;
        }
        return arr;
    }
}
```

也可以先写交错数列，再写等差数列

### [766. 托普利茨矩阵](https://leetcode-cn.com/problems/toeplitz-matrix/)

#### 遍历每个对角线位置

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i = n - 2; i >= 0; i--) {
            int j = i + 1, k = 1;
            while(j < n && k < m) {
                if(matrix[j][k] != matrix[j - 1][k - 1]){
                    return false;
                }
                j++;
                k++;
            }
        }
        for(int i = 1; i < m - 1; i++) {
            int j = 1, k = i + 1;
            while(j < n && k < m) {
                if(matrix[j][k] != matrix[j - 1][k - 1]){
                    return false;
                }
                j++;
                k++;
            }
        }
        return true;
    }
}
```

#### 比较右下角元素

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i < n - 1; i++) {
            for(int j  = 0; j < m - 1; j++) {
                if(matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### [769. 最多能完成排序的块](https://leetcode-cn.com/problems/max-chunks-to-make-sorted/)

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int i = 0, cnt = 0;
        int ans = 0;
        while(i < arr.length) {
            int j = i + 1;
            int min = arr[i], max = arr[i],next = arr[i];
            while(j < arr.length) {
                if(arr[j] < min) {
                    i = j;
                    min = arr[j];
                    max = next;
                } else if (arr[j] > max) {
                    next = Math.max(next, arr[j]);
                } else {
                    i = j;
                    max = next;
                }
                j++;
            }
            cnt++;
            i++;
        }
        return cnt;
    }
}
```

1. 初始化最大值最小值，
2. 分情况讨论
   1. 当前值小于最小值：更新最小值，更新标记位i，更新最大值max。
   2. 最大值最小值之间：更新标记位i，更新最大值max。
   3. 超过最大值：用next暂存，用于其他两个状态更新最大值。

#### 暴力优化

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max = 0, res = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(max == i) {
                res++;
            }
        }
        return res;
    }
}
```

合理利用题目`给定一个长度为 n 的整数数组 arr，它表示在 [0, n - 1] 范围内的整数的排列。`条件。

