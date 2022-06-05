# Java log

[TOC] 



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

### 7.字符串转整数

```java
Integer.parseInt(s)
```

### 8.坐标数组转list

```java
Arrays.asList(i, j);
```

### 9.PriorytyQueue

初始为小顶堆，从小到大排列

### 10.Map

map.getOrDefault(key, defaultValue);

如果存在key则取value，否则取defaultValue

### 11.字典序最小

1. `str.compareTo(sstr)>0`

2. 采用Collections.sort()进行排序

```java
//长度不同，按照长度长的排前面
//长度相同，按照字典序小的排前面
Collections.sort(dictionary, (a, b) -> {
    if(a.length() != b.length()) {
        return b.length() - a.length();
    }
    return a.compareTo(b);
});
```

### 12.字符串相等

不能用`==`  要用`equals`比较字符串是否相等
