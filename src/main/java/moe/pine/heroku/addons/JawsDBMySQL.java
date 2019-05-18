package moe.pine.heroku.addons;

public final class JawsDBMySQL {
    private static boolean DETECTED;
    private static String HOST;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DATABASE;
    private static int PORT;

    static {
        final String redisUrl = System.getenv("JAWSDB_URL");
        final MySQLUrlParser.Result result = MySQLUrlParser.parse(redisUrl);
        if (result != null) {
            DETECTED = true;
            HOST = result.host;
            USERNAME = result.username;
            PASSWORD = result.password;
            DATABASE = result.database;
            PORT = result.port;
        }
    }

    private JawsDBMySQL() {
    }

    public static boolean isDetected() {
        return DETECTED;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static int getPort() {
        return PORT;
    }
}
