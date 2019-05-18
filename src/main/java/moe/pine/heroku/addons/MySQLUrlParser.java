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

        final Result result = new Result();
        result.host = parsedUri.getHost();
        result.port = parsedUri.getPort();

        if (parsedUri.getUserInfo() != null) {
            final String[] userInfo = parsedUri.getUserInfo().split(":", 2);
            result.username = userInfo[0];
            result.password = userInfo[1];
        }

        if (parsedUri.getPath() != null) {
            result.database = parsedUri.getPath().substring(1);
        }

        return result;
    }
}
