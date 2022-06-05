# Simulation

### [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
```

### [237. 删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```



### [725. 分隔链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)

```java
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        //计算长度
        ListNode dummy = head;
        int cnt = 0;

        while(head != null) {
            head = head.next;
            cnt++;
        }

        int len = cnt / k;
        int mod = cnt % k;
        
        head = dummy;
        ListNode next = head;
        ListNode[] ans = new ListNode[k];
        
        for(int i = 0; i < k && next != null; i++) {
            int num = len + (i < mod ? 1 : 0);
            ListNode l = new ListNode(0);
            l.next = next;
            ans[i] = l.next;
            for(int j = 0; j < num; j++) {
                l = l.next;
            }
            if(l != null ) {
                next = l.next;
                l.next = null; 
            }
        }
        return ans;
    }
}
```

### [147. 对链表进行插入排序](https://leetcode-cn.com/problems/insertion-sort-list/)

```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        while(head != null) {
            ListNode pre = dummy;
            ListNode tmp = head.next;
            while(pre.next != null && pre.next.val < head.val) {
                pre = pre.next;
            }
            head.next = pre.next;
            pre.next = head;
            head = tmp;
        }
        return dummy.next;
    }
}
```

