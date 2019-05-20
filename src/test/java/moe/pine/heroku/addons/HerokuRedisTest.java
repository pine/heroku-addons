package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HerokuRedisTest {
    @Test
    void getTest() {
        final RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";

        final HerokuRedis herokuRedis = new HerokuRedis(result);
        Whitebox.setInternalState(HerokuRedis.class, "INSTANCE", herokuRedis);
        assertSame(herokuRedis, HerokuRedis.get());
    }

    @Test
    void constructorTest() {
        final RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";
        result.password = "password";
        result.port = 6380;

        final HerokuRedis herokuRedis = new HerokuRedis(result);
        assertEquals("host", herokuRedis.getHost());
        assertEquals("password", herokuRedis.getPassword());
        assertEquals(6380, herokuRedis.getPort());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void getPasswordTest_illegalState() {
        final RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";

        final HerokuRedis herokuRedis = new HerokuRedis(result);
        assertThrows(IllegalStateException.class, herokuRedis::getPassword);
    }
}
