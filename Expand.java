// Problem 1087. Brace Expansion
// Time Complexity : O(m^b)
// Space Complexity : O(m^b)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public String[] expand(String s) {
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        int n = s.length();
        while (i < n) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(s.charAt(i));
            }
            Collections.sort(block);
            blocks.add(block);
            i++;
        }
        List<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        backtrack(blocks, 0, path, result);
        return result.toArray(new String[0]);
    }

    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder path, List<String> result) {
        if (idx == blocks.size()) {
            result.add(path.toString());
            return;
        }
        List<Character> block = blocks.get(idx);
        for (char c : block) {
            path.append(c);
            backtrack(blocks, idx + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
