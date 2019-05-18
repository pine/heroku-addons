package moe.pine.heroku.addons;

import java.net.URI;
import java.net.URISyntaxException;

class MySQLUrlParser {
    static final class Result {
        String host;
        String username;
        String password;
        String database;
        int port;
    }

    @SuppressWarnings("Duplicates")
    static Result parse(final String mysqlUrl) {
        if (mysqlUrl == null) return null;
        if (mysqlUrl.isEmpty()) return null;

        final URI parsedUri;
        try {
            parsedUri = new URI(mysqlUrl);
        } catch (URISyntaxException e) {
            return null;
        }

        final Result parserResult = new Result();
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
