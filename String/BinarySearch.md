### [744. 寻找比目标字母大的最小字母](https://leetcode.cn/problems/find-smallest-letter-greater-than-target/)

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left == letters.length - 1 &&  letters[left] <= target? letters[0]: letters[left];
    }
}
```

#### 代码优化

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left % letters.length];
    }
}
```

