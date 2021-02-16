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
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        JawsDBMaria maria = new JawsDBMaria(result);
        JawsDBMaria.get(); // Pre-load `Holder` class

        Whitebox.setInternalState(JawsDBMaria.Holder.class, "INSTANCE", maria);
        assertSame(maria, JawsDBMaria.get());

        Whitebox.setInternalState(JawsDBMaria.Holder.class, "INSTANCE", (JawsDBMaria) null);
        assertNull(JawsDBMaria.get());
    }

    @Test
    void constructorTest() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        JawsDBMaria maria = new JawsDBMaria(result);
        assertEquals("host", maria.getHost());
        assertEquals("username", maria.getUsername());
        assertEquals("password", maria.getPassword());
        assertEquals(3306, maria.getPort());
        assertEquals("database", maria.getDatabase());
    }

    @Test
    void getUsernameTest_illegalState() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        JawsDBMaria maria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, maria::getUsername);
    }

    @Test
    void getPasswordTest_illegalState() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        JawsDBMaria maria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, maria::getPassword);
    }

    @Test
    void getDatabaseTest_illegalState() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";

        JawsDBMaria maria = new JawsDBMaria(result);
        assertThrows(IllegalStateException.class, maria::getDatabase);
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

        JawsDBMaria maria1 = new JawsDBMaria(result1);
        JawsDBMaria maria2 = new JawsDBMaria(result1);
        JawsDBMaria maria3 = new JawsDBMaria(result2);

        assertEquals(maria1, maria1);
        assertEquals(maria1, maria2);
        assertEquals(maria1, maria3);
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

        JawsDBMaria maria1 = new JawsDBMaria(result1);
        JawsDBMaria maria2 = new JawsDBMaria(result2);

        assertNotEquals(maria1, null);
        assertNotEquals(maria1, maria2);
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

        JawsDBMaria maria1 = new JawsDBMaria(result1);
        JawsDBMaria maria2 = new JawsDBMaria(result2);

        assertEquals(maria1.hashCode(), maria1.hashCode());
        assertEquals(maria1.hashCode(), maria2.hashCode());
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

        JawsDBMaria maria1 = new JawsDBMaria(result1);
        JawsDBMaria maria2 = new JawsDBMaria(result2);
        
        assertNotEquals(maria1.hashCode(), maria2.hashCode());
    }

    @Test
    void toStringTest() {
        MySQLUrlParser.Result result = new MySQLUrlParser.Result();
        result.host = "host";
        result.username = "username";
        result.password = "password";
        result.port = 3306;
        result.database = "database";

        assertEquals("JawsDBMaria{host='host', username='username', database='database', port=3306}",
                new JawsDBMaria(result).toString());
    }
}
