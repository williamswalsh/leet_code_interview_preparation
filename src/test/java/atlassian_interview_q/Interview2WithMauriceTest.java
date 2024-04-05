package atlassian_interview_q;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <pre>
 *     We are working on a security system for a badged-access room in our company's building.
 *     Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
 *     1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)
 *     2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)
 *     Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.
 * </pre>
 */
public class Interview2WithMauriceTest {

    Pair mismatches(String[][] accessRecords) {
        Map<String, Boolean> employeeIsEntered = new HashMap<>();
        String employee;
        boolean employeeAction;

        Set<String> missingExit = new HashSet<>();
        Set<String> missingEnter = new HashSet<>();

        for (String[] record : accessRecords) {
            employee = record[0];
            employeeAction = record[1].equals("enter");

//                User in Map
            if (employeeIsEntered.containsKey(employee)) {

                // employeeEntered & employeeAction
//                          0           0           -> add to bad exit list
//                          0           1           -> update map state to employeeEntered=true
//                          1           0           -> update map state to employeeEntered=false
//                          1           1           -> add to bad enter list

//                Employee is already entered
                if (employeeIsEntered.get(employee)) {
                    if (employeeAction) {
                        // add to bad enter list
                        missingExit.add(employee);
                        employeeIsEntered.put(employee, true);
                    } else {
//                        update map state to employeeEntered=false
                        employeeIsEntered.put(employee, false);
                    }
                } else {
                    // employee is not already entered
                    if (employeeAction) {
                        //update map state to employeeEntered=true
                        employeeIsEntered.put(employee, true);
                    } else {
                        //add to bad exit list
                        missingEnter.add(employee);
                        employeeIsEntered.put(employee, false);
                    }
                }
            } else {
//                User not in Map
                if (!employeeAction) {
                    missingEnter.add(employee);
                }
                employeeIsEntered.put(employee, employeeAction);
            }

        }

//        Check for enters without exits
        for (var entry : employeeIsEntered.entrySet()) {
            // If employee is still entered at end of day:
            if(entry.getValue()) {
                missingExit.add(entry.getKey());
            }
        }
        return new Pair(missingExit, missingEnter);
    }

    static class Pair {
        private final Set<String> missingExit;
        private final Set<String> missingEnter;

        Pair(Set<String> missingExit, Set<String> missingEnter) {
            this.missingExit = missingExit;
            this.missingEnter = missingEnter;
        }

        @Override
        public String toString() {
            return missingExit + ", " + missingEnter;
        }
    }

    @Test
    void mismatchesEg1() {
        String[][] records1 = {
                {"Paul", "enter"}, // no issue
                {"Pauline", "exit"}, //issue, means she entered the room without using her badge, exit w/o enter
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };
        Pair actual = mismatches(records1);
        String expected = "[Joe, Steve, Curtis, Paul], [Joe, Pauline, Curtis, Martha]";
        assertEquals(expected, actual.toString());

//        NB: Order of employees is different in their solution.??????
//        Expected output: ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]

//        Their output also doesn't report multiple instances of the bad behaviours. - mine does.
//        I aligned with the expected solution by introducing HashSet to remove duplicates.
//        HashSets don't preserve order.
//        LinkedHashSets do preserve order.
//        My order was still different to their expected outcomes even when preserving the order.
    }

    @Test
    void mismatchesEg2() {
        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };
        Pair actual = mismatches(records2);
        String expected = "[], []";
        assertEquals(expected, actual.toString());
    }


    @Test
    void mismatchesEg3() {
        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };
        Pair actual = mismatches(records3);
        String expected = "[Paul], [Paul]";
        assertEquals(expected, actual.toString());
    }

    @Test
    void mismatchesEg4() {
        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"},
        };
        Pair actual = mismatches(records4);
        String expected = "[Raj, Paul], [Paul]";
        assertEquals(expected, actual.toString());
    }
}
