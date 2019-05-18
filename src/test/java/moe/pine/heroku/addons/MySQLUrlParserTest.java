package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MySQLUrlParserTest {
    @Test
    void parserTest() {
        final MySQLUrlParser.Result result =
            MySQLUrlParser.parse("mysql://username:password@host:3306/database");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertEquals("username", result.username);
        assertEquals("password", result.password);
        assertEquals("database", result.database);
        assertEquals(3306, result.port);
    }

    @Test
    void parseTest_usernameOnly() {
        final MySQLUrlParser.Result result =
            MySQLUrlParser.parse("mysql://username@host:3306/database");
        assertNotNull(result);
        assertEquals("host", result.host);
        assertNull(result.password);
        assertEquals("username", result.username);
        assertEquals(3306, result.port);
    }

    @Test
    void parseTest_emptyUrl() {
        assertNull(MySQLUrlParser.parse(""));
    }

    @Test
    void parseTest_nullUrl() {
        assertNull(MySQLUrlParser.parse(null));
    }

    @Test
    void parseTest_illegalUrl() {
        assertNull(MySQLUrlParser.parse(":/+$@*+$$%"));
    }
}
