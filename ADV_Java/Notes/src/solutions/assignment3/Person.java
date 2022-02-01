
package solutions.assignment3;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author COMPSCI 221
 * @version 2021
 */
public class Person implements  Serializable {

    /**
     * Determines if a de-serialized file is compatible with this class.
     *
     * Maintainers must change this value if and only if the new version
     * of this class is not compatible with old versions. See Sun documents
     * for <a href=http://java.sun.com/products/jdk/1.1/docs/guide
     * /serialization/spec/version.doc.html> details. </a>
     *
     * Not necessary to include in first version of the class, but
     * included here as a reminder of its importance.
     */
    private static final long serialVersionUID = 1L;
    
    private Name name; 
    private LocalDate birthdate;

    public Person(){
        
    }
    public Person(Name name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    
    
}
