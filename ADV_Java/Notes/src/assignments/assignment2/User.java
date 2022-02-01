package assignments.assignment2;

import java.io.Serializable;
import java.time.Clock;
import java.util.Objects;

/**
 *
 * @author cjones
 * @version 1
 */
public class User implements Serializable{
      private static final long serialVersionUID = 1L;
      private int userIdentificationKey; //unique user identification - one per User - primary key
      private UserType userType; 
      private int linkToActualUser ; //The link to this customer or employee or administrator
      private String loginIdentificationString; 
      private String password;
      private Clock lastLoginTime;
      private int numberOfLogins;

    public User(int userIdentificationKey, UserType userType, int linkToActualUser, String loginIdentificationString, String password, Clock lastLoginTime, int numberOfLogins) {
        this.userIdentificationKey = userIdentificationKey;
        this.userType = userType;
        this.linkToActualUser = linkToActualUser;
        this.loginIdentificationString = loginIdentificationString;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.numberOfLogins = numberOfLogins;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.userIdentificationKey;
        hash = 19 * hash + Objects.hashCode(this.loginIdentificationString);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userIdentificationKey != other.userIdentificationKey) {
            return false;
        }
        if (!Objects.equals(this.loginIdentificationString, other.loginIdentificationString)) {
            return false;
        }
        return true;
    }

    public int getUserIdentificationKey() {
        return userIdentificationKey;
    }

    public void setUserIdentificationKey(int userIdentificationKey) {
        this.userIdentificationKey = userIdentificationKey;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getLinkToActualUser() {
        return linkToActualUser;
    }

    public void setLinkToActualUser(int linkToActualUser) {
        this.linkToActualUser = linkToActualUser;
    }

    public String getLoginIdentificationString() {
        return loginIdentificationString;
    }

    public void setLoginIdentificationString(String loginIdentificationString) {
        this.loginIdentificationString = loginIdentificationString;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Clock getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Clock lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getNumberOfLogins() {
        return numberOfLogins;
    }

    public void setNumberOfLogins(int numberOfLogins) {
        this.numberOfLogins = numberOfLogins;
    }
         
      
      
}

