## 二叉搜索树（BST）

[二叉搜索树套路](https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/)

#### [108. 将有序数组转换为二叉搜索树（esay）](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

### [230. 二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

```java
class Solution {
    int ans = 0;
    int cnt;
    public int kthSmallest(TreeNode root, int k) {
        cnt = k;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if(root == null || cnt == 0) return ;
        dfs(root.left);
        cnt--;
        if(cnt == 0){
            ans = root.val;
        }
        dfs(root.right);
    }
}
```

##### 思路

1. 建立全局cnt、ans，分别暂存还有cnt个节点、第k小的值。
2. 中序遍历、cnt==0得到第k小的值，最后进行剪枝。

### [235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root == p || root == q) return root;
        TreeNode left =lowestCommonAncestor(root.left, p, q);
        TreeNode right =lowestCommonAncestor(root.right, p, q);
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }
}
```

##### 思路

1. 找到p或者q用新建的left、right节点暂存。
2. 如果当前层级的left不存在，则将right往上传，反之传right
3. 只有当左右子树都存在值，即left、right节点不为null   则当前节点为公共节点。

#### 利用BST性质

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}
```

##### 思路

1. 从上到下开始搜索，如果第一个出现值分在p.val和q.val两边的节点，则为最终的公共祖先。

### [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root == p || root == q) return root;
        TreeNode left =lowestCommonAncestor(root.left, p, q);
        TreeNode right =lowestCommonAncestor(root.right, p, q);
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }
}
```

##### 思路

同235解法1

### [530. 二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)

```java
class Solution {
    int pre = -10000, min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root == null ||root.left == null && root.right == null){
            return 0;
        }
        dfs(root);
        return min;
    }

    public void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        min = Math.min(min, root.val - pre);
        pre = root.val;
        dfs(root.right);
    }
}
```

##### 思路

1. 利用pre暂存上一节点值
2. 相邻两节点存在最小值，所以采用中序遍历。

### [538. 把二叉搜索树转换为累加树](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)

```java
class Solution {
    //右中左
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    public void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.right);
        root.val = root.val + pre;
        pre = root.val;
        dfs(root.left);
    }
}
```

##### 思路

1. 首先确定递归思路，是采用右中左。
2. 用pre暂存上一节点值，用于累加

### [653. 两数之和 IV - 输入 BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)

```java
class Solution {
    Set<Integer> set = new HashSet();
    boolean flag = false;
    public boolean findTarget(TreeNode root, int k) {
        dfs(root, k);
        return flag;
    }

    public void dfs(TreeNode root, int k) {
        if(root == null) return ;
        dfs(root.left, k);
        if(flag) return;
        if(set.contains(k - root.val)){
            flag = true;
            return;
        }
        set.add(root.val);
        dfs(root.right, k);
    }
}
```

##### 思路

1. 用flag暂存结果，用set哈希表，来检查两数之和。