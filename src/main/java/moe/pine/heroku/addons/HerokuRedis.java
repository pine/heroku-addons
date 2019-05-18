package moe.pine.heroku.addons;

import javax.annotation.Nullable;

public class HerokuRedis {
    private static HerokuRedis INSTANCE;

    private String host;
    private String password;
    private int port;

    static {
        final String redisUrl = System.getenv("REDIS_URL");
        final RedisUrlParser.Result result = RedisUrlParser.parse(redisUrl);
        if (result != null) {
            INSTANCE = new HerokuRedis(result);
        }
    }

    HerokuRedis(RedisUrlParser.Result result) {
        host = result.host;
        password = result.password;
        port = result.port;
    }

    @Nullable
    public static HerokuRedis get() {
        return INSTANCE;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        if (password == null) {
            throw new IllegalStateException("Heroku Redis should have password, but not.");
        }
        return password;
    }

    public int getPort() {
        return port;
    }
}
