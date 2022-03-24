# Java log

### 1.栈的创建

Deque<Integer> stack = new ArrayDeque<>();

Deque<Integer> stack = new LinkedList<>();

[为什么推荐用deque而不用stack](https://mp.weixin.qq.com/s/Ba8jrULf8NJbENK6WGrVWg)

栈的弹出和查看栈顶分别用`pollLast`和`peekLast`

### 2.指定位置长度的数组拷贝

System.arraycopy(oldArray, oldstart, newArray,newStart,newEnd);

### 3.StringBuilder删除最后一个元素

sb.deleteCharAt(sb.length()-1);

### 4.字符串裁剪

```
str = str.substring(start,);
```

### 5.二维数组优先对属性1升序，再属性2降序

#### 方法一：

```java
Arrays.sort(arr, new Comparator<int[]>(){
    public int compare(int[] a, int[] b) {
        return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
    }
});
```

#### 方法二：

```java
Arrays.sort(people, (a, b)-> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
```

### 6.列表转数组

```java
list.toArray(new int[list.size()][2]);
```

