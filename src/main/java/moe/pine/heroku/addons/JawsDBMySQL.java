package moe.pine.heroku.addons;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class JawsDBMySQL {
    private static final JawsDBMySQL INSTANCE;

    private final String host;
    private final String username;
    private final String password;
    private final String database;
    private final int port;

    static {
        final String redisUrl = System.getenv("JAWSDB_URL");
        final MySQLUrlParser.Result result = MySQLUrlParser.parse(redisUrl);
        if (result != null) {
            INSTANCE = new JawsDBMySQL(result);
        } else {
            INSTANCE = null;
        }
    }

    JawsDBMySQL(final MySQLUrlParser.Result result) {
        host = result.host;
        username = result.username;
        password = result.password;
        database = result.database;
        port = result.port;
    }

    @Nullable
    public static JawsDBMySQL get() {
        return INSTANCE;
    }

    @Nonnull
    public String getHost() {
        return host;
    }

    @Nonnull
    public String getUsername() {
        if (username == null) {
            throw new IllegalStateException("JawsDB MySQL should have `username`. but not.");
        }
        return username;
    }

    @Nonnull
    public String getPassword() {
        if (password == null) {
            throw new IllegalStateException("JawsDB MySQL should have `password`, but not.");
        }
        return password;
    }

    @Nonnull
    public String getDatabase() {
        if (database == null) {
            throw new IllegalStateException("JawsDB MySQL should have `database`, but not.");
        }
        return database;
    }

    public int getPort() {
        return port;
    }
}
