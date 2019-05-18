package moe.pine.heroku.addons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JawsDBMySQLTest {
    @Test
    public void parserTest() {
        final JawsDBMySQL.Parser parser = new JawsDBMySQL.Parser();
        final JawsDBMySQL.ParserResult parserResult =
            parser.parse("mysql://username:password@host:3306/database");
        assertEquals("host", parserResult.host);
        assertEquals("username", parserResult.username);
        assertEquals("password", parserResult.password);
        assertEquals("database", parserResult.database);
        assertEquals(3306, parserResult.port);
    }
}
