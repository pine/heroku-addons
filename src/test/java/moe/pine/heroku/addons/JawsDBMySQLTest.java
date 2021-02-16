package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({
        "AssertBetweenInconvertibleTypes",
        "ResultOfMethodCallIgnored",
})
class JawsDBMySQLTest {
    @Test
    void get() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        Whitebox.setInternalState(JawsDBMySQL.class, "INSTANCE", jawsDBMySQL);
        assertSame(jawsDBMySQL, JawsDBMySQL.get());

        Whitebox.setInternalState(JawsDBMySQL.class, "INSTANCE", (JawsDBMySQL) null);
        assertNull(JawsDBMySQL.get());
    }

    @Test
    void constructor() {
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
    void getUsername_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getUsername);
    }

    @Test
    void getPassword_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getPassword);
    }

    @Test
    void getDatabase_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getDatabase);
    }

    @Test
    void equals_equals() {
        MySQLUrlParser.Result result1 = new MySQLUrlParser.Result();
        result1.host = "host";
        result1.username = "username";
        result1.password = "password";
        result1.port = 3306;
        result1.database = "database";

        MySQLUrlParser.Result result2 = new MySQLUrlParser.Result();
        result2.host = "host";
        result2.username = "username";
        result2.password = "password";
        result2.port = 3306;
        result2.database = "database";

        assertEquals(new JawsDBMySQL(result1), new JawsDBMySQL(result1));
        assertEquals(new JawsDBMySQL(result1), new JawsDBMySQL(result2));
    }

    @Test
    void equals_notEquals_fields() {
        MySQLUrlParser.Result result1 = new MySQLUrlParser.Result();
        result1.host = "host";
        result1.username = "username";
        result1.password = "password";
        result1.port = 3306;
        result1.database = "database";

        MySQLUrlParser.Result result2 = new MySQLUrlParser.Result();
        result2.host = "host";
        result2.username = "username";
        result2.password = "password";
        result2.port = 3307;
        result2.database = "database";

        assertNotEquals(new JawsDBMySQL(result1), new JawsDBMySQL(result2));
    }

    @Test
    void equals_notEquals_class() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        assertNotEquals(new JawsDBMaria(result), new JawsDBMySQL(result));
    }

    @Test
    void hashCode_equals() {
        MySQLUrlParser.Result result1 = new MySQLUrlParser.Result();
        result1.host = "host";
        result1.username = "username";
        result1.password = "password";
        result1.port = 3306;
        result1.database = "database";

        MySQLUrlParser.Result result2 = new MySQLUrlParser.Result();
        result2.host = "host";
        result2.username = "username";
        result2.password = "password";
        result2.port = 3306;
        result2.database = "database";

        assertEquals(new JawsDBMySQL(result1).hashCode(), new JawsDBMySQL(result1).hashCode());
        assertEquals(new JawsDBMySQL(result1).hashCode(), new JawsDBMySQL(result2).hashCode());
    }

    @Test
    void hashCode_notEquals() {
        MySQLUrlParser.Result result1 = new MySQLUrlParser.Result();
        result1.host = "host";
        result1.username = "username";
        result1.password = "password";
        result1.port = 3306;
        result1.database = "database";

        MySQLUrlParser.Result result2 = new MySQLUrlParser.Result();
        result2.host = "host";
        result2.username = "username";
        result2.password = "password";
        result2.port = 3307;
        result2.database = "database";

        assertNotEquals(new JawsDBMySQL(result1).hashCode(), new JawsDBMySQL(result2).hashCode());
    }
}
