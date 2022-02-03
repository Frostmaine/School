package assignment_3;

import assn2.classes.Employee;
import java.util.Comparator;

/**
 * A Comparator that compares employees based on their names.
 * @author Matthew Yackiel
 */
public class EmployeeComparator implements Comparator<Employee> {

    /**
     * Compares two employee objects based on the alphabetical order of names,
     * last names take priority.
     * @param o1
     * @param o2
     * @return a positive integer if o1 > o2
     *         a negative integer if o1 < o2
     *         zero if o1 == o2
     */
    @Override
    public int compare(Employee o1, Employee o2) 
    {
        String o1Last = o1.getName().getLastName();
        String o1First = o1.getName().getFirstName();
        String o2Last = o2.getName().getLastName();
        String o2First = o2.getName().getFirstName();
                
        // their last name is the same
        if (o1Last.compareTo(o2Last) == 0)
        {
            // their first name is the same
            if (o1First.compareTo(o2First) == 0)
                return 0;
            // different first name so compare them
            return o1First.compareTo(o2First);
        }
        // they are not equal so only compare the last names
        return o1Last.compareTo(o2Last);
    }
    
}
