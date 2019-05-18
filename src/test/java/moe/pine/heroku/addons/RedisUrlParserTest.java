package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RedisUrlParserTest {
    @Test
    public void parserTest() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://h:password@host:6380");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertEquals("password", result.password);
        assertEquals(6380, result.port);
    }

    @Test
    public void parseTest_emptyUserInfo() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://host:6380");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertNull(result.password);
        assertEquals(6380, result.port);
    }

    @Test
    public void parseTest_emptyUrl() {
        assertNull(RedisUrlParser.parse(""));
    }

    @Test
    public void parseTest_nullUrl() {
        assertNull(RedisUrlParser.parse(null));
    }

    @Test
    public void parseTest_illegalUrl() {
        assertNull(RedisUrlParser.parse(":/+$@*+$$%"));
    }
}
