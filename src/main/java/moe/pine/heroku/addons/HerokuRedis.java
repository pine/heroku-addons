package moe.pine.heroku.addons;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public final class HerokuRedis {
    private final @NonNull String host;
    private final @Nullable String password;
    private final int port;

    static final class Holder {
        private static final @Nullable HerokuRedis INSTANCE;

        private Holder() {
        }

        static {
            final String redisUrl = System.getenv("REDIS_URL");
            final RedisUrlParser.Result result = RedisUrlParser.parse(redisUrl);
            if (result != null) {
                INSTANCE = new HerokuRedis(result);
            } else {
                INSTANCE = null;
            }
        }
    }

    HerokuRedis(RedisUrlParser.Result result) {
        host = Objects.requireNonNull(result.host, "result.host");
        password = result.password;
        port = result.port;
    }

    /**
     * Get a cached {@link HerokuRedis} instance.
     * <p>
     * This instance will be initialized from the environment variable {@code REDIS_URL}
     * when {@link #get} is called for the first time.
     *
     * @return cached instance, or {@code null} if the environment variable not found
     */
    public static @Nullable HerokuRedis get() {
        return Holder.INSTANCE;
    }

    /**
     * Get Redis host name
     *
     * @return host name
     */
    public @NonNull String getHost() {
        return host;
    }

    /**
     * Get Redis password
     *
     * @return password
     * @throws IllegalStateException if the password doesn't exist
     */
    public @NonNull String getPassword() {
        if (password == null) {
            throw new IllegalStateException("Heroku Redis should have `password`, but not.");
        }
        return password;
    }

    /**
     * Get Redis port number
     *
     * @return port number
     */
    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HerokuRedis that = (HerokuRedis) o;
        return port == that.port && host.equals(that.host) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, password, port);
    }

    @Override
    public String toString() {
        return "HerokuRedis{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
