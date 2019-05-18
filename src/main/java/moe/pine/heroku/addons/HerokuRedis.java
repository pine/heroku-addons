package moe.pine.heroku.addons;


import java.net.URI;
import java.net.URISyntaxException;

public final class HerokuRedis {
    private static boolean DETECTED;
    private static String HOST;
    private static String PASSWORD;
    private static int PORT;

    static final class Parser {
        @SuppressWarnings("Duplicates")
        static ParserResult parse(final String redisUrl) {
            if (redisUrl == null) return null;
            if (redisUrl.isEmpty()) return null;

            final URI parsedUri;
            try {
                parsedUri = new URI(redisUrl);
            } catch (URISyntaxException e) {
                return null;
            }

            final ParserResult parserResult = new ParserResult();
            parserResult.host = parsedUri.getHost();
            parserResult.port = parsedUri.getPort();

            if (parsedUri.getUserInfo() != null) {
                parserResult.password = parsedUri.getUserInfo().split(":", 2)[1];
            }

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
        final ParserResult parserResult = Parser.parse(redisUrl);
        if (parserResult != null) {
            DETECTED = true;
            HOST = parserResult.host;
            PASSWORD = parserResult.password;
            PORT = parserResult.port;
        }
    }

    private HerokuRedis() {
    }

    public static boolean isDetected() {
        return DETECTED;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static int getPort() {
        return PORT;
    }
}
