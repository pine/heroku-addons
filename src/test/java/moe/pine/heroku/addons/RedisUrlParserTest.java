package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RedisUrlParserTest {
    @Test
    public void parserTest() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://h:password@host:6380");
        assertEquals("host", result.host);
        assertEquals("password", result.password);
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
