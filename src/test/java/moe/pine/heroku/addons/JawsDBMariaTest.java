package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JawsDBMariaTest {
    @Test
    void getTest() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        Whitebox.setInternalState(JawsDBMaria.class, "INSTANCE", jawsDBMaria);
        assertSame(jawsDBMaria, JawsDBMaria.get());

        Whitebox.setInternalState(JawsDBMaria.class, "INSTANCE", (JawsDBMaria) null);
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
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getUsernameTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getUsername);
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getPasswordTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getPassword);
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getDatabaseTest_illegalState() {
        final MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        final JawsDBMaria jawsDBMaria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, jawsDBMaria::getDatabase);
    }
}
