package moe.pine.heroku.addons;


import java.net.URI;

public final class HerokuRedis {
    private static String REDIS_HOST;
    private static String REDIS_PASSWORD;
    private static int REDIS_PORT;

    static final class Parser {
        ParserResult parse(final String redisUrl) {
            if (redisUrl == null) return null;
            if (redisUrl.isEmpty()) return null;

            final URI parsedUri = URI.create(redisUrl);
            final ParserResult parserResult = new ParserResult();
            parserResult.host = parsedUri.getHost();
            parserResult.password = parsedUri.getUserInfo().split(":", 2)[1];
            parserResult.port = parsedUri.getPort();

            return parserResult;
        }
    }

    static final class ParserResult {
        String host;
        String password;
        int port;
    }

    static {
        final String redisUrl = System.getenv("REDIS_URL");
        final Parser parser = new Parser();
        final ParserResult parserResult = parser.parse(redisUrl);
        if (parserResult != null) {
            REDIS_HOST = parserResult.host;
            REDIS_PASSWORD = parserResult.password;
            REDIS_PORT = parserResult.port;
        }
    }

    private HerokuRedis() {
    }

    public static String getHost() {
        return REDIS_HOST;
    }

    public static String getPassword() {
        return REDIS_PASSWORD;
    }

    public static int getPort() {
        return REDIS_PORT;
    }
}
