package moe.pine.heroku.addons;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
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

    @Nested
    class Equals {
        @Test
        void equals() {
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
        void notEquals_fields() {
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
        void notEquals_class() {
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

            assertNotEquals(new JawsDBMaria(result1), new JawsDBMySQL(result2));
            assertNotEquals(new JawsDBMySQL(result1), new JawsDBMaria(result2));
        }
    }
}
