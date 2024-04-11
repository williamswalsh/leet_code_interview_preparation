package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 * - Every close bracket has a corresponding open bracket of the same type.
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 *
 * This is my first implementation.
 * Leetcode:
 * Invalid implementation.
 * </pre>
 */
public class Q20ValidParentheses {

    public boolean isValidNonWorking(String s) {
        char[] chars = s.toCharArray();
        int x = 0;
        int y = 0;
        int z = 0;

        for (char c : chars) {
            switch (c) {
                case '(':
                    x++;
                    break;
                case '{':
                    y++;
                    break;
                case '[':
                    z++;
                    break;
                case ')':
                    x--;
                    if (x < 0) {
                        return false;
                    }
                    break;
                case '}':
                    y--;
                    if (y < 0) {
                        return false;
                    }
                    break;
                case ']':
                    z--;
                    if (z < 0) {
                        return false;
                    }
                    break;
                default:
                    System.out.println("Unexpected character");
            }
        }
        return x == 0 && y == 0 && z == 0;
    }

    /**
     * <pre>
     * This is my fastest implementation.
     * Using a stack to record the opening braces.
     * And a hashmap to track the ( -> ) relationship.
     *
     * Leetcode:
     * - 2ms        - beats 54.23% of users with Java
     * - 41.44MB    - beats 44.39% of users with Java
     * </pre>
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        for (char c: s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if (stack.isEmpty() || c != map.get(stack.pop())) {
                    return false;
            }
        }
        return stack.isEmpty();
    }

    @Test
    void singlePairBracketTest() {
        String s = "()";
        assertTrue(isValid(s));
    }

    @Test
    void multipleBracketPairsTest() {
        String s = "()[]{}";
        assertTrue(isValid(s));
    }

    @Test
    void unmatchedBracketTest() {
        String s = "(]";
        assertFalse(isValid(s));
    }

    @Test
    void bracketClosedBeforeInnerClosedTest() {
        String s = "([)]";
        assertFalse(isValid(s));
    }
}
