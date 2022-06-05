# UnionFind

### [547. 省份数量](https://leetcode-cn.com/problems/number-of-provinces/)

```java
class Solution {
    boolean[][] visit;
    int max = 0;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        visit = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visit[i][j] && isConnected[i][j] == 1) {
                    dfs(isConnected, i, j);
                    max++;
                }
            }
        }
        return max;
    }
    public void dfs(int[][]isConnected, int i, int j) {
        if(visit[i][j] || isConnected[i][j] == 0) {
            return;
        }
        visit[i][j] = true;
        for(int k = 0; k < isConnected.length; k++) {
            if(isConnected[i][k] == 1) {
                dfs(isConnected, k, i);
            }
        }
    }
}
```

* 找到连通省份，然后把这个跟省份所有连通的省份，进行标记。已标记的跳过
* 步骤1的次数则为最终答案。

#### 空间优化

```java
class Solution {
    boolean[] visit;
    int cnt = 0;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visit[i]) {
                dfs(i, isConnected);
                cnt++;
            }
        }
        return cnt;
    }
    public void dfs(int i, int[][] isConnected) {
        visit[i] = true;
        for(int j = 0; j < isConnected.length; j++) {
            if(isConnected[i][j] == 1 && !visit[j]) {
                dfs(j, isConnected);
            }
        }
    }
}
```

#### 并查集

```java
class UnionFind {
    // 记录父节点
    private Map<Integer,Integer> father;
    // 记录集合的数量
    private int numOfSets = 0;
    
    public UnionFind() {
        father = new HashMap<Integer,Integer>();
        numOfSets = 0;
    }
    
    //将当前x节点作为独立的集合，并且集合数+1
    public void add(int x) {
        if (!father.containsKey(x)) {
            father.put(x, null);
            numOfSets++;
        }
    }
    
    public void merge(int x, int y) {
        //在合并的过程中，先调用find找到x节点和y节点的父节点
        int rootX = find(x);
        int rootY = find(y);
        //如果不是共同祖先，则将其连接，并将集合数减少
        if (rootX != rootY){
            father.put(rootX,rootY);
            numOfSets--;
        }
    }
    
    public int find(int x) {
        int root = x;
        //找的过程，先迭代祖先
        while(father.get(root) != null){
            root = father.get(root);
        }
        //判断祖先跟当前节点是否相同，不相同则进行路径压缩
        while(x != root){
            //当前x节点连接到根节点，父节点作为当前节点
            int original_father = father.get(x);
            father.put(x,root);
            x = original_father;
        }
        
        return root;
    }
   	//判断是否已经连通了
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
    //返回集合数
    public int getNumOfSets() {
        return numOfSets;
    }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        UnionFind uf = new UnionFind();
        for(int i = 0;i < isConnected.length;i++){
           	//将当前i节点创建一个父节点为空的集合。
            uf.add(i);
            //j<i
            for(int j = 0;j < i;j++){
                //如果跟j节点连通，则进行merge合并
                if(isConnected[i][j] == 1){
                    uf.merge(i,j);
                }
            }
        }
        
        return uf.getNumOfSets();
    }
}
```

#### 并查集代码优化

```java
class UnionFind{
    int[] father;
    int size;

    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
        }
        size = n;
    }

    public void merge(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            father[rootX] =rootY;
            size--;
        }
    }

    public int find (int x) {
        if(father[x] == x) {
            return x;
        }
        return find(father[x]);
    }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(isConnected[i][j] == 1) {
                    uf.merge(i, j);
                }
            }
        }
        return uf.size;
    }
}
```

