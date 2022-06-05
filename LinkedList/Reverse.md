# Reverse

### [25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;
        while(end != null) {
            for(int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if(end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;

        while(head != null) {
            ListNode tmp =  head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
```

### [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)



```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        //1.计算长度
        //2.k % cnt 取余
        //双指针，找到倒数第k % cnt位，重新连接。
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;
        int cnt = 0;
        while(head != null) {
            head = head.next;
            cnt++;
        }
        int len = k % cnt;
        if(len == 0) {
            return dummy;
        }

        ListNode slow = dummy;
        ListNode fast = dummy;
        for(int i = 0; i < len; i++) {
            fast = fast.next;
        }
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode ans = slow.next;
        slow.next = null;
        fast.next = dummy;
        return ans;
    }
}

//代码优化
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;
        int cnt = 1;
        while(head.next != null) {
            head = head.next;
            cnt++;
        }
        int len = k % cnt;
        if(len == 0) {
            return dummy;
        }
        head.next = dummy;
        for(int i = 0; i < cnt - len; i++) {
            head = head.next;
        }
        ListNode ans = head.next;
        head.next = null;
        return ans;
    }
}
```



### [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode cur = null;
        ListNode start = pre.next, end = start;
        for(int i = 0; i < right - left + 1; i++) {
            ListNode tmp = end.next;
            end.next = cur;
            cur = end;
            end = tmp;
        }
        pre.next = cur;
        start.next = end;
        return dummy.next;
    }
}
```



### [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;

        while(head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
```

### [234. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        //边走边反转
        ListNode pre = null;
        ListNode cur = head;

        if(head == null || head.next == null) {
            return true;
        }

        while(head != null && head.next != null) {
            head = head.next.next;
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        if(head != null && head.next == null) {
            cur = cur.next;
        }

        while(cur != null) {
            if(cur.val != pre.val) {
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return true;
    }
}
```

利用快慢指针，找到中点    找的过程中进行反转，  最后再逐个判断回文
