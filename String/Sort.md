### [451. 根据字符出现频率排序](https://leetcode.cn/problems/sort-characters-by-frequency/)

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c , map.getOrDefault(c, 0) + 1);
        }

        List<Character>[] list = new List[s.length() + 1];
        for(char key : map.keySet()) {
            int value = map.get(key);
            if(list[value] == null) {
                list[value] = new ArrayList();
            }
            list[value].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = s.length(); i >= 0; i--) {
            if(list[i] != null) {
                for(int j = 0; j < list[i].size(); j++) {
                    char c = list[i].get(j);
                    for(int k = 0; k < i; k++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.==toString();
    }
}
```

桶排序

先哈希计算，再建桶放桶，最后生成字符串