# dfs

### [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

#### 重复搜索(超时)

```java
class Solution {
    boolean[][] visit;
    public void solve(char[][] board) {
        int n = board.length, m =board[0].length;
        visit = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 'O') {
                    if(isvalue(board, i, j)){
                        dfs(board, i, j);
                    }
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X'){
            return;
        }
        board[i][j] = 'X';
        dfs(board, i - 1, j) ;
        dfs(board, i + 1, j) ;
        dfs(board, i, j - 1) ;
        dfs(board, i, j + 1) ;
    }

    public boolean isvalue(char[][] board, int i, int j) {
        if(board[i][j] == 'O' && (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1)) {
            return false;
        }
        if(visit[i][j] || board[i][j] == 'X') {
            return true;
        }
        visit[i][j] = true;
        boolean ans =  isvalue(board, i - 1, j) && isvalue(board, i + 1, j) && isvalue(board, i, j - 1) && isvalue(board, i, j + 1) ;
        visit[i][j] = false;
        return ans;
    }
}
```

#### 边缘搜索

```java
class Solution {
    boolean[][] visit;
    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;
        visit = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && board[i][j] == 'O'){
                    dfs(board, i, j);
                }      
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visit[i][j] && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
        
    }
    public void dfs(char[][] board, int i, int j) {
        if(i < 0 || i >= board.length || j < 0 || j>= board[0].length || board[i][j] == 'X' || visit[i][j]) {
            return;
        }
        visit[i][j] = true;
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
```

* 从边缘搜索，遇到O就进行深搜，将所有的O都进行标记，
* 最后将没有标记过的，全部转为X

### [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

```java
class Solution {
    int cnt = 0;
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return ;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }
}
```

 每次找到岛屿，将能深搜到的所有岛屿标记为已访问，cnt++

### [417. 太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)

```java
class Solution {
    List<List<Integer>> ans = new ArrayList();
    boolean[][] canReachP;
    boolean[][] canReachA;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length, m = heights[0].length;

        canReachP = new boolean[n][m];
        canReachA = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            dfs(heights, canReachP, i, 0);
            dfs(heights, canReachA, i , m - 1);
        }

        for(int j = 0; j < m; j++) {
            dfs(heights, canReachP, 0, j);
            dfs(heights, canReachA, n - 1, j);
        }

        return ans;
    }

    public void dfs(int[][] heights, boolean[][] canReach, int i, int j) {
        if(canReach[i][j]) {
            return;
        }
        canReach[i][j] = true;
        
        if(canReachP[i][j] && canReachA[i][j]) {
            ans.add(Arrays.asList(i, j));
        }

        if(i + 1 < heights.length && heights[i + 1][j] >= heights[i][j] ) {
            dfs(heights, canReach, i + 1, j);
        }
        if(j + 1 < heights[0].length && heights[i][j + 1] >= heights[i][j] ) {
            dfs(heights, canReach, i, j + 1);
        }
        if(i - 1 >= 0 && heights[i - 1][j] >= heights[i][j] ) {
            dfs(heights, canReach, i - 1, j);
        }
        if(j - 1 >= 0 && heights[i][j - 1] >= heights[i][j] ) {
            dfs(heights, canReach, i, j - 1);
        }

    }
}
```

##### 思路

1. 找到所有太平洋能到达的位置
2. 找到所有大西洋能到达的位置
3. 重合部分为最终解

#### 代码优化

```java
class Solution {
    List<List<Integer>> ans = new ArrayList();
    boolean[][] canReachP;
    boolean[][] canReachA;
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    int n, m;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        n = heights.length;
        m = heights[0].length;

        canReachP = new boolean[n][m];
        canReachA = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            dfs(heights, canReachP, i, 0);
            dfs(heights, canReachA, i , m - 1);
        }

        for(int j = 0; j < m; j++) {
            dfs(heights, canReachP, 0, j);
            dfs(heights, canReachA, n - 1, j);
        }

        return ans;
    }

    public void dfs(int[][] heights, boolean[][] canReach, int i, int j) {
        if(canReach[i][j]) {
            return;
        }
        canReach[i][j] = true;
        
        if(canReachP[i][j] && canReachA[i][j]) {
            ans.add(Arrays.asList(i, j));
        }
        
        for(int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];
            if(isValue(newX, newY) && heights[i][j] <= heights[newX][newY]) {
                dfs(heights, canReach, newX, newY);
            }
        }
    }

    public boolean isValue(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
```



### [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 + dfs(grid, i - 1, j) + dfs(grid, i, j - 1) +dfs(grid, i + 1, j) + dfs(grid, i, j + 1);
    }
}
```

* 当找到1(岛屿)，进行深搜，找出当前区域所有的岛屿。
* 找到的位置置为0，表示已找过。
* 统计当前能找到的岛屿数量，取出全局最大。

#### [剑指 Offer 13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

```java
class Solution {
    int sum = 0;
    int[][] dp;
    public int movingCount(int m, int n, int k) {
        dp = new int[m][n];
        helper(m,n,k,0,0);
        return sum;
    }
    public void helper(int m, int n, int k, int i, int j) {
        if(i >= m || j >= n || i/10 + i%10 + j/10 + j%10 > k || dp[i][j] == 1){
            return;
        }
        dp[i][j]++;
        sum++;
        helper(m, n, k, i + 1, j);
        helper(m, n, k, i, j + 1);
    }
}
```

核心在于确定终止条件` if(i >= m || j >= n || i/10 + i%10 + j/10 + j%10 > k || dp[i][j] == 1)`

