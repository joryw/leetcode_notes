# DivideAndConquer

### [241. 为运算表达式设计优先级](https://leetcode.cn/problems/different-ways-to-add-parentheses/)

```java
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ways = new ArrayList();
        int n = expression.length();
        for(int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for(int l: left) {
                    for(int r: right) {
                        switch(c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if(ways.size() == 0) {
            ways.add(Integer.valueOf(expression));
        }
        return ways;
    }
}
```

1. 首先通过符号'+'、'-'、'*' 将字符串分为左右子串分别执行计算，
2. 将执行后的结果进行笛卡尔积计算。