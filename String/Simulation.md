### [28. 实现 strStr()](https://leetcode.cn/problems/implement-strstr/)

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        for(int i = 0; i <= len1 - len2; i++) {
            int j = i, k = 0;
            while(k < len2 && haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if(k == len2) {
                return i;
            }
        }
        return -1;
    }
}
```

### [459. 重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern/)

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for(int i = len / 2; i > 0; i--) {
            if(len % i == 0) {
                int num = len / i - 2;
                int j = 0;
                while(j <= num && s.substring(j * i , (j + 1) * i).equals(s.substring((j + 1) * i, (j + 2) * i))) {

                    j++;
                }
                if(j > num) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### [541. 反转字符串 II](https://leetcode.cn/problems/reverse-string-ii/)

```java
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder str = new StringBuilder();
        int len = s.length();
        int flag = -1;
        for(int i = 0; i <= len / k; i++) {
            int l = i * k;
            int r = (i + 1) * k - 1;
            flag = r;
            if(i == len / k) {
                r = len - 1;
            }
            if(i % 2 == 0) {
                while(r >= l) {
                    str.append(s.charAt(r));
                    r--;
                }
            } else {
                while(r >= l) {
                    str.append(s.charAt(l));
                    l++;
                } 
            }
        }
        return str.toString();
    }
}
```

#### 模拟2(优解)

```java
class Solution {
    public String reverseStr(String s, int k) {
        int len = s.length();
        char[] c = s.toCharArray();
        for(int i = 0; i < len; i = i + 2*k ) {
            reverse(c, i, Math.min(i + k, len) - 1);
        }
        return String.valueOf(c);
    }

    public void reverse(char[] c, int l, int r) {
        while(r > l) {
            char tmp = c[l];
            c[l] = c[r];
            c[r] = tmp;
            l++;
            r--;
        }
    }
}
```

转为数组操作更为简单。

### [剑指 Offer 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) ==' ') {
                str.append("%20");
                continue;
            }
            str.append(s.charAt(i));
        }
        return str.toString();
    }
}
```



### [剑指 Offer 58 - II. 左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < len; i++) {
            str.append(s.charAt((i + n) % len));
        }
        return str.toString();
    }
}
```

