package moe.pine.heroku.addons;

import java.net.URI;
import java.net.URISyntaxException;

class RedisUrlParser {
    static final class Result {
        String host;
        String password;
        int port;
    }

    @SuppressWarnings("Duplicates")
    static Result parse(final String redisUrl) {
        if (redisUrl == null) return null;
        if (redisUrl.isEmpty()) return null;

        final URI parsedUri;
        try {
            parsedUri = new URI(redisUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        final Result result = new Result();
        result.host = parsedUri.getHost();
        result.port = parsedUri.getPort();

        if (parsedUri.getUserInfo() != null) {
            final String[] userInfo = parsedUri.getUserInfo().split(":", 2);
            result.password = userInfo[1];
        }

        return result;
    }
}