package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RedisUrlParserTest {
    @Test
    void parserTest() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://h:password@host:6380");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertEquals("password", result.password);
        assertEquals(6380, result.port);
    }

    @Test
    void parseTest_emptyUserInfo() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://host:6380");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertNull(result.password);
        assertEquals(6380, result.port);
    }

    @Test
    void parseTest_usernameOnly() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://h@host:6380");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertNull(result.password);
        assertEquals(6380, result.port);
    }

    @Test
    void parseTest_emptyUrl() {
        assertNull(RedisUrlParser.parse(""));
    }

    @Test
    void parseTest_nullUrl() {
        assertNull(RedisUrlParser.parse(null));
    }

    @Test
    void parseTest_illegalUrl() {
        assertNull(RedisUrlParser.parse(":/+$@*+$$%"));
    }
}
