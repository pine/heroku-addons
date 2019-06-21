package moe.pine.heroku.addons;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public final class HerokuRedis {
    private static final @Nullable HerokuRedis INSTANCE;

    private final @NonNull String host;
    private final @Nullable String password;
    private final int port;

    static {
        final String redisUrl = System.getenv("REDIS_URL");
        final RedisUrlParser.Result result = RedisUrlParser.parse(redisUrl);
        if (result != null) {
            INSTANCE = new HerokuRedis(result);
        } else {
            INSTANCE = null;
        }
    }

    HerokuRedis(RedisUrlParser.Result result) {
        host = Objects.requireNonNull(result.host);
        password = result.password;
        port = result.port;
    }


    public static @Nullable HerokuRedis get() {
        return INSTANCE;
    }


    public @NonNull String getHost() {
        return host;
    }

    public @NonNull String getPassword() {
        if (password == null) {
            throw new IllegalStateException("Heroku Redis should have `password`, but not.");
        }
        return password;
    }

    public int getPort() {
        return port;
    }
}
