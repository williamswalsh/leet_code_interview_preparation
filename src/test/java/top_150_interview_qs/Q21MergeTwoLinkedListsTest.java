package top_150_interview_qs;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Q21MergeTwoLinkedListsTest {

    @Test
    void testCaseOne() {
        ListNode1 list1 = new ListNode1(1);
        list1.next = new ListNode1(2);
        list1.next.next = new ListNode1(4);

        ListNode1 list2 = new ListNode1(1);
        list2.next = new ListNode1(3);
        list2.next.next = new ListNode1(4);


        ListNode1 merged = mergeTwoLists(list1, list2);
        assertNotNull(merged);

        System.out.println(merged.print());

        assertFalse(isEmpty(merged));
        assertEquals(1, getValueAtIndex(merged, 0));
        assertEquals(1, getValueAtIndex(merged, 1));
        assertEquals(2, getValueAtIndex(merged, 2));
        assertEquals(3, getValueAtIndex(merged, 3));
        assertEquals(4, getValueAtIndex(merged, 4));
        assertEquals(4, getValueAtIndex(merged, 5));
    }

    @Test
    void testIsEmpty() {
        ListNode1 list1 = new ListNode1();
        assertTrue(isEmpty(list1));
        System.out.println(list1.print());
    }

    boolean isEmpty(@NotNull ListNode1 list) {
        return (list.val == -1 && list.next == null);
    }

    @Test
    void testCaseTwo() {
        ListNode1 list1 = new ListNode1();
        ListNode1 list2 = new ListNode1();

        ListNode1 merged = mergeTwoLists(list1, list2);

        assertNotNull(merged);
        System.out.println(merged.print());

        assertTrue(isEmpty(list1));
        assertTrue(isEmpty(list2));
        assertTrue(isEmpty(merged));
    }

    @Test
    void testCaseThree() {
        ListNode1 list1 = new ListNode1();
        ListNode1 list2 = new ListNode1(0);

        ListNode1 merged = mergeTwoLists(list1, list2);
        assertNotNull(merged);
        assertFalse(isEmpty(merged));
        assertEquals(0, getValueAtIndex(merged, 0));
    }

    private int getValueAtIndex(ListNode1 list, int index) {
        if (list==null) {
            throw new NullPointerException("LinkedList passed as argument is null. It cannot be null.");
        }

        int value;
        ListNode1 curr;
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

    private ListNode1 mergeTwoLists(ListNode1 list1, ListNode1 list2) {

        // guard clauses
        if (isEmpty(list1) && isEmpty(list2)) {
            return new ListNode1();
        }
        if (isEmpty(list1)) {
            return list2;
        }
        if (isEmpty(list2)) {
            return list1;
        }

        ListNode1 aPtr = list1;
        ListNode1 bPtr = list2;
        ListNode1 merged = new ListNode1(-1);
        ListNode1 head = merged;
        while (aPtr != null && bPtr != null) {

            if (aPtr.val > bPtr.val) {
                merged = merged.addAndIncrement(bPtr.val);
                // progress B ptr
                bPtr = bPtr.next;

            } else if (aPtr.val < bPtr.val){
                merged = merged.addAndIncrement(aPtr.val);
                // progress A ptrs
                aPtr = aPtr.next;

            } else {
                // add both values
                merged = merged.addAndIncrement(aPtr.val);
                merged = merged.addAndIncrement(bPtr.val);

                // increment both pointers
                aPtr = aPtr.next;
                bPtr = bPtr.next;
            }
        }
        return head;
    }
}

class ListNode1 {
    private static final int DEFAULT_INIT_VALUE = -1;
    int val;
    ListNode1 next;

    ListNode1() {
        this.val = DEFAULT_INIT_VALUE;
    }

    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }

    boolean hasNext() {
        return next != null;
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        ListNode1 current = this;

        if (current.val != -1) {
            sb.append(current.val).append(" -> ");
        }

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
            next = new ListNode1(val);
        }
    }

    public ListNode1 addAndIncrement(int val) {

        // start of list
        if (this.val == -1){
            this.val = val;
            return this;
        } else {
            next = new ListNode1(val);
            return next;
        }
    }
}
