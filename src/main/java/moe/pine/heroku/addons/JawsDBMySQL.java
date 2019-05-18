package moe.pine.heroku.addons;

import java.net.URI;
import java.net.URISyntaxException;

public final class JawsDBMySQL {
    private static boolean DETECTED;
    private static String HOST;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DATABASE;
    private static int PORT;

    static final class Parser {
        @SuppressWarnings("Duplicates")
        ParserResult parse(final String jawsDbUrl) {
            if (jawsDbUrl == null) return null;
            if (jawsDbUrl.isEmpty()) return null;

            final URI parsedUri;
            try {
                parsedUri = new URI(jawsDbUrl);
            } catch (URISyntaxException e) {
                return null;
            }

            final ParserResult parserResult = new ParserResult();
            parserResult.host = parsedUri.getHost();
            parserResult.port = parsedUri.getPort();

            if (parsedUri.getUserInfo() != null) {
                final String[] userInfo = parsedUri.getUserInfo().split(":", 2);
                parserResult.username = userInfo[0];
                parserResult.password = userInfo[1];
            }

            if (parsedUri.getPath() != null) {
                parserResult.database = parsedUri.getPath().substring(1);
            }

            return parserResult;
        }
    }

    static final class ParserResult {
        String host;
        String username;
        String password;
        String database;
        int port;
    }

    static {
        final String redisUrl = System.getenv("JAWSDB_URL");
        final Parser parser = new Parser();
        final ParserResult parserResult = parser.parse(redisUrl);
        if (parserResult != null) {
            DETECTED = true;
            HOST = parserResult.host;
            USERNAME = parserResult.username;
            PASSWORD = parserResult.password;
            DATABASE = parserResult.database;
            PORT = parserResult.port;
        }
    }

    private JawsDBMySQL() {
    }

    public static boolean isDetected() {
        return DETECTED;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static int getPort() {
        return PORT;
    }
}
