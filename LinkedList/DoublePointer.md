# DoublePointer

### [19. 删除链表的倒数第 N 个结点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;

        for(int i = 0; i < n; i++) {
            head = head.next;
        }
        while(head != null) {
            pre = pre.next;
            head = head.next;
        }

        pre.next = pre.next.next;

        return dummy.next;
    }
}
```

### [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;

        while(head != null && head.next != null) {
            ListNode tmp = head.next.next;
            pre.next = head.next;
            pre.next.next = head;
            head.next = tmp;
            pre = head;
            head = tmp;
        }
        return dummy.next;
    }
}
```

### [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while(cur != null && cur.next != null) {
            if(cur.val == cur.next.val) {
                while(cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }

        }
        return dummy.next;
    }
}
```

### [86. 分隔链表](https://leetcode-cn.com/problems/partition-list/)

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode dummy1 = l1;
        ListNode dummy2 = l2;

        while(head != null) {
            if(head.val < x) {
                l1.next = head;
                l1 = head;
            }  else {
                l2.next = head;
                l2 = head;
            }
            head = head.next;
        }
        l2.next = null;
        l1.next = dummy2.next;
        return dummy1.next;
    }
}
```





### [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode yummy = new ListNode(-101);
        ListNode pre = yummy;
        yummy.next = head;

        while(head != null) {
            if(pre.val != head.val) {
                pre.next = head;
                pre = head;
            }
            head = head.next;
        }
        pre.next = head;
        return yummy.next;
    }
}
```



### [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

#### 快慢指针

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }
}
```

### [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

#### 快慢指针

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        if(fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
```



### [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;

        while(l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }
}
```

### [203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;

        while(head != null) {
            if(head.val == val) {
                pre.next = pre.next.next;
                head = pre.next;
            } else {
                pre = pre.next;
                head = head.next;
            }

        }
        return dummy.next;
    }
}
```

### [328. 奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/)

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode next = l2;

        while(l2 != null && l2.next != null) {
            l1.next = l2.next;
            l1 = l1.next;
            l2.next = l1.next;
            l2 = l2.next;
        }
        l1.next = next;
        return dummy;
    }
}
```

### [876. 链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/)

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

