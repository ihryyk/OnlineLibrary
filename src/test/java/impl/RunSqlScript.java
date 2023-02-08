package impl;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RunSqlScript {
    private static final String DB_RESOURCE_BUNDLE = "db";
    private static final String URL = "db.url";
    private static final String DB_SCRIPT_PATH = "D:\\EPUM_Courses\\Проект\\ProjectLibrary\\src\\main\\resources\\dbScript.sql";

    static public void RunSqlScript() throws SQLException {
        String aSQLScriptFilePath = DB_SCRIPT_PATH;
        ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
        Connection con = DriverManager.getConnection(rb.getString(URL));
        Statement stmt = null;
        try {
            ScriptRunner sr = new ScriptRunner(con);
            Reader reader = new BufferedReader(
                    new FileReader(aSQLScriptFilePath));
            sr.runScript(reader);
        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath
                    + " The error is " + e.getMessage());
        } finally {
            con.close();
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
