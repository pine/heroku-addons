package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HerokuRedisTest {
    @Test
    public void isDetectedTest() {
        Whitebox.setInternalState(HerokuRedis.class, "DETECTED", false);
        assertFalse(HerokuRedis.isDetected());
        Whitebox.setInternalState(HerokuRedis.class, "DETECTED", true);
        assertTrue(HerokuRedis.isDetected());
    }

    @Test
    public void getHostTest() {
        Whitebox.setInternalState(HerokuRedis.class, "HOST", "host");
        assertEquals("host", HerokuRedis.getHost());
    }

    @Test
    public void getPasswordTest() {
        Whitebox.setInternalState(HerokuRedis.class, "PASSWORD", "password");
        assertEquals("password", HerokuRedis.getPassword());
    }

    @Test
    public void getPortTest() {
        Whitebox.setInternalState(HerokuRedis.class, "PORT", 6380);
        assertEquals(6380, HerokuRedis.getPort());
    }
}
