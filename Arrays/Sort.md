### [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        for(int i = k; i < nums.length; i++) {
            if(queue.peek() < nums[i]) {
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }
}
```

先用优先队列k容量排序，再取出堆顶

### [347. 前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/)

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int min = Integer.MAX_VALUE, max = 0;
        for(int i : nums) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }
        int[] arr = new int[max - min + 1];
        for(int i : nums) {
            arr[i - min]++;
        }
        for(int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(queue.peek() < arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int flag = queue.peek();
        int[] ans = new int[k];
        int t = 0;
        for(int i = 0; i < arr.length;i++) {
            if(arr[i] >= flag) {
                ans[t++] = i + min;
            }
        }
        return ans;
    }
}
```

数组哈希计数存储，再借优先队列排序，找到高频

#### bucket sort

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer>[] list = new List[nums.length + 1];
        for(int key : map.keySet()) {
            int value = map.get(key);
            if(list[value] == null) {
                list[value] = new ArrayList();
            }
            list[value].add(key);
        }
        int[] ans = new int[k];
        int idx = 0;
        for(int i = nums.length; i >= 0; i--) {
            if(list[i] != null) {
                for(int num :list[i]) {
                    ans[idx++] = num;
                    if(idx == k) break;
                }
                if(idx == k) break;
            }
        }
        return ans;
    }
}
```

用list数组拉链，存同频率的值，再逆序从高到低频率存储。