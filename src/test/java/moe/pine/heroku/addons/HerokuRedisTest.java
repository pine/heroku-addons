package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HerokuRedisTest {
    @Test
    public void parserTest() {
        final HerokuRedis.Parser parser = new HerokuRedis.Parser();
        final HerokuRedis.ParserResult parserResult = parser.parse("redis://h:password@host:6380");
        assertEquals("host", parserResult.host);
        assertEquals("password", parserResult.password);
        assertEquals(6380, parserResult.port);
    }

    @Test
    public void isDetectedTest() {
        Whitebox.setInternalState(HerokuRedis.class, "DETECTED", false);
        assertFalse(HerokuRedis.isDetected());
        Whitebox.setInternalState(HerokuRedis.class, "DETECTED", true);
        assertTrue(HerokuRedis.isDetected());
    }

    @Test
    public void getHostTest() {
        Whitebox.setInternalState(HerokuRedis.class, "REDIS_HOST", "host");
        assertEquals("host", HerokuRedis.getHost());
    }

    @Test
    public void getPasswordTest() {
        Whitebox.setInternalState(HerokuRedis.class, "REDIS_PASSWORD", "password");
        assertEquals("password", HerokuRedis.getPassword());
    }

    @Test
    public void getPort() {
        Whitebox.setInternalState(HerokuRedis.class, "REDIS_PORT", 6380);
        assertEquals(6380, HerokuRedis.getPort());
    }
}
