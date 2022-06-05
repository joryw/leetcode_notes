# DataStructure

### [445. 两数相加 II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new LinkedList();
        Deque<Integer> s2 = new LinkedList();
        ListNode head = new ListNode(0);

        while(l1 != null) {
            s1.addLast(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            s2.addLast(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int a = s1.isEmpty() ? 0 : s1.pollLast();
            int b = s2.isEmpty() ? 0 : s2.pollLast();
            int sum = a + b + carry;
            carry = sum / 10;
            ListNode tmp =  new ListNode(sum % 10);
            tmp.next = head.next;
            head.next = tmp;
        }
        return head.next;
    }
}
```

### [707. 设计链表](https://leetcode-cn.com/problems/design-linked-list/)

```java
class ListNode {
    int val;
    ListNode pre, next;
    public ListNode(int val){
        this.val = val;
    }
}

class MyLinkedList {
    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }
    
    public int get(int index) {
        if(index >= size) {
            return -1;
        }
        ListNode cur = head;
        for(int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
        
    }
    
    public void addAtHead(int val) {
        ListNode cur = new ListNode(val);
        cur.next = head.next;
        head.next.pre = cur;
        head.next = cur;
        cur.pre = head;
        size++;
    }
    
    public void addAtTail(int val) {
        ListNode cur = new ListNode(val);
        cur.pre = tail.pre;
        tail.pre.next = cur;
        cur.next = tail;
        tail.pre = cur;
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        if(size < index) {
            return;
        }
        ListNode cur = new ListNode(val); 
        ListNode ppre = head;
        for(int i = 0; i < index; i++) {
            ppre = ppre.next;
        }
        cur.next = ppre.next;
        ppre.next.pre = cur;
        cur.pre = ppre;
        ppre.next = cur;
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if(size <= index) {
            return;
        }
        ListNode cur = head;
        for(int i = 0; i < index; i++) {
            cur = cur.next;
        }
        //忘记恢复双链结构了
        cur.next = cur.next.next;
        cur.next.pre = cur;    //就因为少了这句代码  调了几个小时。
        size--;
    }
}
```

### [138. 复制带随机指针的链表](https://leetcode.cn/problems/copy-list-with-random-pointer/)

```java
class Solution {
    public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node pre = dummy;
        Node start = head;
        Map<Node, Node> map = new HashMap<>();

        //构造新链表
        while(start != null) {
            Node cur = new Node(start.val);
            pre.next = cur;
            pre = cur;
            map.put(start, cur);
            start = start.next;
        } 

        //生成新的随机指针
        pre = dummy.next;
        while(head != null) {
            pre.random = map.get(head.random);
            pre = pre.next;
            head = head.next;
        }

        return dummy.next;
    }
}
```

1. 遍历链表，深拷贝链表结构，并且用哈希存储链表节点位置。
2. 再次遍历链表，深拷贝随机指针
