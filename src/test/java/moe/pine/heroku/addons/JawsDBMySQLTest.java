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
    void getTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        
        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        JawsDBMySQL.get(); // Pre-load `Holder` class

        Whitebox.setInternalState(JawsDBMySQL.Holder.class, "INSTANCE", jawsDBMySQL);
        assertSame(jawsDBMySQL, JawsDBMySQL.get());

        Whitebox.setInternalState(JawsDBMySQL.Holder.class, "INSTANCE", (JawsDBMySQL) null);
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
    void getUsernameTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getUsername);
    }

    @Test
    void getPasswordTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getPassword);
    }

    @Test
    void getDatabaseTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMySQL jawsDBMySQL = new JawsDBMySQL(result);
        assertThrows(IllegalStateException.class, jawsDBMySQL::getDatabase);
    }

    @Test
    void equalsTest_equals() {
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

        JawsDBMySQL mysql1 = new JawsDBMySQL(result1);
        JawsDBMySQL mysql2 = new JawsDBMySQL(result1);
        JawsDBMySQL mysql3 = new JawsDBMySQL(result2);

        assertEquals(mysql1, mysql1);
        assertEquals(mysql1, mysql2);
        assertEquals(mysql1, mysql3);
    }

    @Test
    void equalsTest_notEquals_fields() {
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
    void equalsTest_notEquals_class() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        assertNotEquals(new JawsDBMaria(result), new JawsDBMySQL(result));
    }

    @Test
    void hashCodeTest_equals() {
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
    void hashCodeTest_notEquals() {
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

    @Test
    void toStringTest() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        assertEquals("JawsDBMySQL{host='host', username='username', database='database', port=3306}",
                new JawsDBMySQL(result).toString());
    }
}
