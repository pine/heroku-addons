package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"ResultOfMethodCallIgnored", "OverlyStrongTypeCast"})
class JawsDBMariaTest {
    @Test
    void getTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        JawsDBMaria.get(); // Pre-load `Holder` class

        Whitebox.setInternalState(JawsDBMaria.Holder.class, "INSTANCE", jawsDBMaria);
        assertSame(jawsDBMaria, JawsDBMaria.get());

        Whitebox.setInternalState(JawsDBMaria.Holder.class, "INSTANCE", (JawsDBMaria) null);
        assertNull(JawsDBMaria.get());
    }

    @Test
    void constructorTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertEquals("host", jawsDBMaria.getHost());
        assertEquals("username", jawsDBMaria.getUsername());
        assertEquals("password", jawsDBMaria.getPassword());
        assertEquals(3306, jawsDBMaria.getPort());
        assertEquals("database", jawsDBMaria.getDatabase());
    }

    @Test
    void getUsernameTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getUsername);
    }

    @Test
    void getPasswordTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getPassword);
    }

    @Test
    void getDatabaseTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getDatabase);
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

        assertEquals(new JawsDBMaria(result1), new JawsDBMaria(result1));
        assertEquals(new JawsDBMaria(result1), new JawsDBMaria(result2));
    }

    @Test
    void equalsTest_notEquals() {
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

        assertNotEquals(new JawsDBMaria(result1), new JawsDBMaria(result2));
    }
}
