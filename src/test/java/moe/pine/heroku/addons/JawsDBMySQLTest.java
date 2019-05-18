package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JawsDBMySQLTest {
    @Test
    public void isDetectedTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "DETECTED", false);
        assertFalse(JawsDBMySQL.isDetected());
        Whitebox.setInternalState(JawsDBMySQL.class, "DETECTED", true);
        assertTrue(JawsDBMySQL.isDetected());
    }

    @Test
    public void getHostTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "HOST", "host");
        assertEquals("host", JawsDBMySQL.getHost());
    }

    @Test
    public void getUsernameTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "USERNAME", "username");
        assertEquals("username", JawsDBMySQL.getUsername());
    }

    @Test
    public void getPasswordTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "PASSWORD", "password");
        assertEquals("password", JawsDBMySQL.getPassword());
    }

    @Test
    public void getDatabaseTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "DATABASE", "database");
        assertEquals("database", JawsDBMySQL.getDatabase());
    }

    @Test
    public void getPortTest() {
        Whitebox.setInternalState(JawsDBMySQL.class, "PORT", 3306);
        assertEquals(3306, JawsDBMySQL.getPort());
    }
}
