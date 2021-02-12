package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"ResultOfMethodCallIgnored", "OverlyStrongTypeCast"})
class HerokuRedisTest {
    @Test
    void getTest() {
        RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";

        HerokuRedis herokuRedis = new HerokuRedis(result);
        HerokuRedis.get(); // Pre-load `Holder` class

        Whitebox.setInternalState(HerokuRedis.Holder.class, "INSTANCE", herokuRedis);
        assertSame(herokuRedis, HerokuRedis.get());

        Whitebox.setInternalState(HerokuRedis.Holder.class, "INSTANCE", (HerokuRedis) null);
        assertNull(HerokuRedis.get());
    }

    @Test
    void constructorTest() {
        RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";
        result.password = "password";
        result.port = 6380;

        HerokuRedis herokuRedis = new HerokuRedis(result);
        assertEquals("host", herokuRedis.getHost());
        assertEquals("password", herokuRedis.getPassword());
        assertEquals(6380, herokuRedis.getPort());
    }

    @Test
    void getPasswordTest_illegalState() {
        RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";

        HerokuRedis herokuRedis = new HerokuRedis(result);
        assertThrows(IllegalStateException.class, herokuRedis::getPassword);
    }

    @Test
    void equalsTest_equals() {
        RedisUrlParser.Result result1 = new RedisUrlParser.Result();
        result1.host = "host";
        result1.password = "password";
        result1.port = 6380;

        RedisUrlParser.Result result2 = new RedisUrlParser.Result();
        result2.host = "host";
        result2.password = "password";
        result2.port = 6380;

        assertEquals(new HerokuRedis(result1), new HerokuRedis(result1));
        assertEquals(new HerokuRedis(result1), new HerokuRedis(result2));
    }

    @Test
    void equalsTest_notEquals() {
        RedisUrlParser.Result result1 = new RedisUrlParser.Result();
        result1.host = "host";
        result1.password = "password";
        result1.port = 6380;

        RedisUrlParser.Result result2 = new RedisUrlParser.Result();
        result2.host = "host";
        result2.password = "password";
        result2.port = 6381;

        assertNotEquals(new HerokuRedis(result1), new HerokuRedis(result2));
    }

    @Test
    void hashCodeTest_equals() {
        RedisUrlParser.Result result1 = new RedisUrlParser.Result();
        result1.host = "host";
        result1.password = "password";
        result1.port = 6380;

        RedisUrlParser.Result result2 = new RedisUrlParser.Result();
        result2.host = "host";
        result2.password = "password";
        result2.port = 6380;

        assertEquals(new HerokuRedis(result1).hashCode(), new HerokuRedis(result1).hashCode());
        assertEquals(new HerokuRedis(result1).hashCode(), new HerokuRedis(result2).hashCode());
    }

    @Test
    void hashCodeTest_notEquals() {
        RedisUrlParser.Result result1 = new RedisUrlParser.Result();
        result1.host = "host";
        result1.password = "password";
        result1.port = 6380;

        RedisUrlParser.Result result2 = new RedisUrlParser.Result();
        result2.host = "host";
        result2.password = "password";
        result2.port = 6381;

        assertNotEquals(new HerokuRedis(result1).hashCode(), new HerokuRedis(result2).hashCode());
    }

    @Test
    void toStringTest() {
        RedisUrlParser.Result result = new RedisUrlParser.Result();
        result.host = "host";
        result.password = "password";
        result.port = 6380;

        HerokuRedis redis = new HerokuRedis(result);
        assertEquals("HerokuRedis{host='host', password='password', port=6380}", redis.toString());
    }
}
