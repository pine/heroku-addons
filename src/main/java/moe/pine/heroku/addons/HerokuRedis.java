package moe.pine.heroku.addons;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public final class HerokuRedis {
    private static final HerokuRedis INSTANCE;

    private final String host;
    private final String password;
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

    @Nullable
    public static HerokuRedis get() {
        return INSTANCE;
    }

    @Nonnull
    public String getHost() {
        return host;
    }

    @Nonnull
    public String getPassword() {
        if (password == null) {
            throw new IllegalStateException("Heroku Redis should have `password`, but not.");
        }
        return password;
    }

    public int getPort() {
        return port;
    }
}
