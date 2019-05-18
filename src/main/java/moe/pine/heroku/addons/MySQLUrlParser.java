package moe.pine.heroku.addons;

import javax.annotation.Nullable;
import java.net.URI;
import java.net.URISyntaxException;

final class MySQLUrlParser {
    static final class Result {
        String host;
        String username;
        String password;
        String database;
        int port;
    }

    private MySQLUrlParser() {
    }

    @Nullable
    @SuppressWarnings("Duplicates")
    static Result parse(@Nullable final String mysqlUrl) {
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
            if (userInfo.length > 0) {
                result.username = userInfo[0];
                if (userInfo.length > 1) {
                    result.password = userInfo[1];
                }
            }
        }

        if (parsedUri.getPath() != null && parsedUri.getPath().length() > 1) {
            result.database = parsedUri.getPath().substring(1);
        }

        return result;
    }
}
