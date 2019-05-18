package moe.pine.heroku.addons;

public final class HerokuRedis {
    private static boolean DETECTED;
    private static String HOST;
    private static String PASSWORD;
    private static int PORT;

    static {
        final String redisUrl = System.getenv("REDIS_URL");
        final RedisUrlParser.Result result = RedisUrlParser.parse(redisUrl);
        if (result != null) {
            DETECTED = true;
            HOST = result.host;
            PASSWORD = result.password;
            PORT = result.port;
        }
    }

    private HerokuRedis() {
    }

    public static boolean isDetected() {
        return DETECTED;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static int getPort() {
        return PORT;
    }
}
