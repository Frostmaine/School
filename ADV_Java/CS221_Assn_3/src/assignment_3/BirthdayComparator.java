/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_3;

import assn2.classes.Customer;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author frostmaine
 */
public class BirthdayComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        LocalDate b1 = o1.getBirthdate(); // get the birthdates
        LocalDate b2 = o2.getBirthdate();
        return b1.compareTo(b2);
    }
    
}
