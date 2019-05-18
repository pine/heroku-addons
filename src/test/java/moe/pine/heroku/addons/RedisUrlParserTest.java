package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisUrlParserTest {
    @Test
    public void parserTest() {
        final RedisUrlParser.Result result =
            RedisUrlParser.parse("redis://h:password@host:6380");
        assertEquals("host", result.host);
        assertEquals("password", result.password);
        assertEquals(6380, result.port);
    }

}
