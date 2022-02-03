package mysql;


/**
 * Represents a MySQL database that implements the <code>DatabaseManagement</code>
 * interface.
 *
 * @author Curt Jones (2018)
 */
public class DatabaseManagement implements database.DatabaseManagement {

    private database.DatabasePropertyManager databasePropertyManager;
    private database.DatabaseErrorLogManager databaseErrorLogManager;

    @Override
    public void initializeDatabaseManagement() {

    }

    @Override
    public void CreateTables() {
        throw new UnsupportedOperationException("Not yet a supported operation!"); 
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
