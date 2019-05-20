package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JawsDBMySQLTest {
    @Test
    void getTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        Whitebox.setInternalState(JawsDBMySQL.class, "INSTANCE", jawsDBMySQL);
        assertSame(jawsDBMySQL, JawsDBMySQL.get());

        Whitebox.setInternalState(JawsDBMySQL.class, "INSTANCE", (JawsDBMySQL) null);
        assertNull(JawsDBMySQL.get());
    }

    @Test
    void constructorTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertEquals("host", jawsDBMySQL.getHost());
        assertEquals("username", jawsDBMySQL.getUsername());
        assertEquals("password", jawsDBMySQL.getPassword());
        assertEquals(3306, jawsDBMySQL.getPort());
        assertEquals("database", jawsDBMySQL.getDatabase());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getUsernameTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getUsername);
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getPasswordTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getPassword);
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getDatabaseTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getDatabase);
    }
}
