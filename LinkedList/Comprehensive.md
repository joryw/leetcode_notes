# Comprehensive

[TOC]



### [23. 合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

#### 归并排序

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if(left >= right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode lNode = mergeKLists(lists, left, mid);
        ListNode rNode = mergeKLists(lists, mid + 1, right);
        return sortLists(lNode, rNode);
    }

    public ListNode sortLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if(l1.val <= l2.val) {
            l1.next = sortLists(l1.next, l2);
            return l1;
        } else {
            l2.next = sortLists(l1, l2.next);
            return l2;
        }
    }
}
```

### [143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

```java
class Solution {
    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode next = slow.next;
        slow.next = null;
        ListNode dummy = new ListNode(0);
        dummy.next = reverse(next);
        merge(head, dummy.next);
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;

        while(head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    public void merge(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode tmp = l1.next;
        ans.next = l1;
        ans = l1;
        l1 = tmp;
        while(l1 != null && l2 != null) {
            ListNode tmp1 = l1.next;
            ListNode tmp2 = l2.next;
            ans.next = l2;
            ans.next.next = l1;
            ans = l1;
            l1 = tmp1;
            l2 = tmp2;
        }
    }
}
```

1. 找中点
2. 反转
3. 合并

### [148. 排序链表](https://leetcode-cn.com/problems/sort-list/)

#### 归并排序

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
       	//注意这里不能用同一起点，会栈过深
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode r = sortList(slow.next);
        slow.next = null;
        ListNode l = sortList(head);
        return mergeList(l, r);
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return ans.next;
    }
}
```

#### 快排(超时)

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        return quickSort(dummy, null);
    }

    public ListNode quickSort(ListNode dummy, ListNode end) {
        //end而不是null
        if(dummy == end || dummy.next == end || dummy.next.next == end) {
            return dummy;
        }

        ListNode l = new ListNode(0);
        ListNode p = dummy.next, r = p, cur = l;
        while(r.next != end) {
            if(r.next.val < p.val) {
                l.next = r.next;
                l = l.next;
                //去除结点
                r.next = r.next.next;
            } else {
                r = r.next;
            }
        }
        //原链表较大 接到新链表后面。
        l.next = dummy.next;
        //接回到头结点后面
        dummy.next = cur.next;
        quickSort(dummy, p);
        quickSort(p, end);
        return dummy.next;
    }
}
```



#### 优先队列

```java
class Solution {
    public ListNode sortList(ListNode head) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        while(head != null) {
            queue.add(head.val);
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        head = dummy;
        while(!queue.isEmpty()) {
            head.next = new ListNode(queue.poll());
            head = head.next;
        }
        return dummy.next;
    }
}
```

