package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Q21MergeLLV2Test {

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode();
        ListNode head = merged;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                merged.next = list2;
                list2 = list2.next;
            } else {
                merged.next = list1;
                list1 = list1.next;
            }
            merged = merged.next;
        }

        if (list1!=null) {
            merged.next = list1;
        } else {
            merged.next = list2;
        }

        return head.next;
    }

    @Test
    void testCaseOne() {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);


        ListNode merged = mergeTwoLists(list1, list2);
        assertNotNull(merged);

        System.out.println(merged.print());

        assertEquals(1, getValueAtIndex(merged, 0));
        assertEquals(1, getValueAtIndex(merged, 1));
        assertEquals(2, getValueAtIndex(merged, 2));
        assertEquals(3, getValueAtIndex(merged, 3));
        assertEquals(4, getValueAtIndex(merged, 4));
        assertEquals(4, getValueAtIndex(merged, 5));
    }

    @Test
    void testCaseTwo() {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();

        ListNode merged = mergeTwoLists(list1, list2);

        assertNotNull(merged);
        System.out.println(merged.print());
    }

    @Test
    void testCaseThree() {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode(0);

        ListNode merged = mergeTwoLists(list1, list2);
        assertNotNull(merged);
        assertEquals(0, getValueAtIndex(merged, 0));
    }

    private int getValueAtIndex(ListNode list, int index) {
        if (list==null) {
            throw new NullPointerException("LinkedList passed as argument is null. It cannot be null.");
        }

        int value;
        ListNode curr;
        if (index==0) {
            return list.val;
        } else if (index<0) {
            throw new IndexOutOfBoundsException("Cannot have negative index value.");
        } else {
            curr = list;
            while(index>0) {
                curr = curr.next;
                index--;
            }
            return curr.val;
        }
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    boolean hasNext() {
        return next != null;
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;

        while (current.hasNext()) {
            sb.append(current.val).append(" -> ");
            current = current.next;
        }
        return sb.toString();
    }

    public void add(int val) {

        // start of list
        if (this.val == -1){
            this.val = val;
        } else {
            next = new ListNode(val);
        }
    }

    public ListNode addAndIncrement(int val) {

        // start of list
        if (this.val == -1){
            this.val = val;
            return this;
        } else {
            next = new ListNode(val);
            return next;
        }
    }
}
