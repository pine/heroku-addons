package moe.pine.heroku.addons;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public final class JawsDBMySQL {
    private static final @Nullable JawsDBMySQL INSTANCE;

    private final @NonNull String host;
    private final @Nullable String username;
    private final @Nullable String password;
    private final @Nullable String database;
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
        host = Objects.requireNonNull(result.host);
        username = result.username;
        password = result.password;
        database = result.database;
        port = result.port;
    }

    public static @Nullable JawsDBMySQL get() {
        return INSTANCE;
    }

    public @NonNull String getHost() {
        return host;
    }

    public @NonNull String getUsername() {
        if (username == null) {
            throw new IllegalStateException("JawsDB MySQL should have `username`. but not.");
        }
        return username;
    }

    public @NonNull String getPassword() {
        if (password == null) {
            throw new IllegalStateException("JawsDB MySQL should have `password`, but not.");
        }
        return password;
    }

    public @NonNull String getDatabase() {
        if (database == null) {
            throw new IllegalStateException("JawsDB MySQL should have `database`, but not.");
        }
        return database;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JawsDBMySQL that = (JawsDBMySQL) o;
        return port == that.port && host.equals(that.host) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(database, that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, username, password, database, port);
    }

    @Override
    public String toString() {
        return "JawsDBMySQL{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", database='" + database + '\'' +
                ", port=" + port +
                '}';
    }
}
