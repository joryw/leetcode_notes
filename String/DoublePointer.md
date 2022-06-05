# DoublePointer

### [151. 颠倒字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/)

```java
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        int i = s.length() - 1, j = i;
        StringBuilder str = new StringBuilder();
        while(i > 0) {
            if(s.charAt(i) == ' ') {
                str.append(s.substring(i + 1, j + 1)).append(' ');
                while(s.charAt(i) == ' ') {
                    i--;
                }
                j = i;
            } else {
                i--;
            }
        }
        return str.append(s.substring(i, j + 1)).toString();
    }
}
```

从后往前，双指针，当遇到空格，停下来添加字符串，直到遇到字符串起点。

### [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/)

```java
class Solution {
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while(i < j) {
            char tmp = s[i];
            s[i++] = s[j];
            s[j--] = tmp;
        }
    }
}
```



### [345. 反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)

```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int i = 0, j = s.length() - 1;
        char[] c = s.toCharArray();
        while(i < j) {
            while(i < j && !set.contains(c[i])) {
                i++;
            }
            while(i < j && !set.contains(c[j])) {
                j--;
            }
            if(i < j) {
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;
            }
            i++;
            j--;
        }
        return String.valueOf(c);
    }
}
```

### [524. 通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length(), max = 0, idx = 0;
        for(int k = 0; k < dictionary.size(); k++) {
            String str = dictionary.get(k);
            int i = 0, j = 0, m = str.length();
            while(i < n && j < m) {
                if(s.charAt(i) == str.charAt(j)) {
                    j++;
                }
                i++;
            }
            if(j == m) {
                if(max < m) {
                    max = m;
                    idx = k;
                } else if(max == m) {
                    if(dictionary.get(idx).compareTo(str)>0) {
                        idx = k;
                    }
//                    for(int l = 0; l < m; l++) {
//                        if(dictionary.get(idx).charAt(l) < str.charAt(l)) {
//                            break;
//                        } else if(dictionary.get(idx).charAt(l) > str.charAt(l)) {
//                            idx = k;
//                            break;
//                        }
//                    }
                }
            }
        }
        return max == 0 ? "" : dictionary.get(idx);
    }
}
```

#### 排序+双指针+贪心

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (a, b) -> {
            if(a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        int n = s.length();
        for(String str : dictionary) {
            int m = str.length(), i = 0, j = 0;
            while(i < n && j < m) {
                if(str.charAt(j) == s.charAt(i)) {
                    j++;
                }
                i++;
            }
            if(j == m) {
                return str;
            }
        }
        return "";
    }
}
```



### [680. 验证回文字符串 Ⅱ](https://leetcode-cn.com/problems/valid-palindrome-ii/)

```java
class Solution {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return isvalue(s, i + 1, j) || isvalue(s, i, j - 1) ;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isvalue(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
```

#### 