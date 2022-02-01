/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import database.Database;
import database.DatabaseMySQLImpl;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author frostmaine
 */
public class SQLReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String message;

    public SQLReport()
    {
        message = "";
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SQLReport other = (SQLReport) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BalanceBean{" + '}';
    }
    
    
}
