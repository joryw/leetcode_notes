class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(-5);
        root.left.right.left.right = new TreeNode(-3);
        root.left.right.right = new TreeNode(4);
        root.left.right.right.right = new TreeNode(10);
        int ans =new Solution().maxSumBST(root);
        System.out.println(ans);
    }
    int max = Integer.MIN_VALUE;
    boolean flag = true;
    public int maxSumBST(TreeNode root) {
        if(root == null) return 0;
        isBST(root, Integer.MIN_VALUE);
        if(flag) {
            max = Math.max(max, count(root));
        }
        flag = true;
        maxSumBST(root.left);
        maxSumBST(root.right);
        return max < 0 ? 0 :max;
    }

    public void isBST(TreeNode root, int pre) {
        if(!flag || root == null) return;
        isBST(root.left, pre);
        if(root.val <= pre) {
            flag = false;
        }
        pre = root.val;
        isBST(root.right, pre);
    }

    public int count (TreeNode root) {
        if(root == null) return 0;
        return root.val + count(root.left) + count(root.right);
    }
}