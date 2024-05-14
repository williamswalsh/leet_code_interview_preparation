package top_150_interview_qs;

import org.junit.jupiter.api.Test;

public class Q141LinkedListLoopDetectionTest {

    boolean hasCycle(ListNodeSimple head) {

        if (head == null || head.next == null) {
            return false;
        }

        // init 2 ptrs
        ListNodeSimple fast = head;
        ListNodeSimple slow = head;

        // How to stop the loop
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    @Test
    void testCase4() {

    }
}

class ListNodeSimple {
    int val;
    ListNodeSimple next;

    ListNodeSimple(int x) {
        val = x;
        next = null;
    }
}
