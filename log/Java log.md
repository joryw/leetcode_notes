# Java log

### 1.栈的创建

Deque<Integer> stack = new ArrayDeque<>();

Deque<Integer> stack = new LinkedList<>();

[为什么推荐用deque而不用stack](https://mp.weixin.qq.com/s/Ba8jrULf8NJbENK6WGrVWg)

栈的弹出和查看栈顶分别用`pollLast`和`peekLast`

### 2.指定位置长度的数组拷贝

System.arraycopy(oldArray, oldstart, newArray,newStart,newEnd);



