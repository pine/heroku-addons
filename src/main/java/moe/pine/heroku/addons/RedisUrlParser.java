package moe.pine.heroku.addons;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

final class RedisUrlParser {
    static final class Result {
        @Nullable String host;
        @Nullable String password;
        int port;
    }

    private RedisUrlParser() {
    }

    @SuppressWarnings("Duplicates")
    static @Nullable Result parse(final @Nullable String redisUrl) {
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
            if (userInfo.length > 1) {
                result.password = userInfo[1];
            }
        }

        return result;
    }
}