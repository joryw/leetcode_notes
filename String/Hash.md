# Hash

### [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // if(s.length() < 2) return s.length();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, j = -1;
        // boolean flag =false;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                // flag = true;
                j = Math.max(map.get(c), j);
            }
            max = Math.max(max, i - j);
            map.put(c, i);
        }
        // return flag ? max : s.length();
        return max;
    }
}
```

