package mysql;


/**
 * Represents a MySQL database that implements the <code>DatabaseManagement</code>
 * interface.
 *
 * @author Curt Jones (2018)
 */
public class DatabaseManagement implements database.DatabaseManagement {

    private database.UserManager userManager; 
    private database.DatabasePropertyManager databasePropertyManager;
    private database.DatabaseErrorLogManager databaseErrorLogManager;

    @Override
    public void initializeDatabaseManagement() {

    }

    @Override
    public void CreateTables() {
        throw new UnsupportedOperationException("Not yet a supported operation!"); 
    }

    /**
     * Returns a <code>UserManager</code> object for this
     * <code>DatabaseManagement</code>.
     *
     * @return A <code>UserManager</code>  object.
     * @see common.User 
     */
    @Override
    public database.UserManager getUserManager() {
        if (userManager == null) userManager = new mysql.UserManager();
        return userManager;
    }

    @Override
    public database.DatabasePropertyManager getDatabasePropertyManager() {
        if (databasePropertyManager == null) databasePropertyManager = new mysql.DatabasePropertyManager();
        return databasePropertyManager;
    }

    @Override
    public database.DatabaseErrorLogManager getDatabaseErrorLogManager() {
        if(databaseErrorLogManager==null) {
            databaseErrorLogManager = new mysql.DatabaseErrorLogManager();
        }
        return  databaseErrorLogManager;
    }

 }
