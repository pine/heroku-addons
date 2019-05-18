package moe.pine.heroku.addons;

import javax.annotation.Nullable;
import java.net.URI;
import java.net.URISyntaxException;

final class RedisUrlParser {
    static final class Result {
        String host;
        String password;
        int port;
    }

    private RedisUrlParser() {
    }

    @Nullable
    @SuppressWarnings("Duplicates")
    static Result parse(@Nullable final String redisUrl) {
        if (redisUrl == null) return null;
        if (redisUrl.isEmpty()) return null;

        final URI parsedUri;
        try {
            parsedUri = new URI(redisUrl);
        } catch (URISyntaxException e) {
            return null;
        }

        final Result result = new Result();
        result.host = parsedUri.getHost();
        result.port = parsedUri.getPort();

        if (parsedUri.getUserInfo() != null) {
            final String[] userInfo = parsedUri.getUserInfo().split(":", 2);
            if (userInfo.length >= 2) {
                result.password = userInfo[1];
            }
        }

        return result;
    }
}