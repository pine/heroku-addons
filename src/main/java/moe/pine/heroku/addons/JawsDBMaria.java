package moe.pine.heroku.addons;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 * Connection information to JawsDB Maria
 *
 * @see <a href="https://elements.heroku.com/addons/jawsdb-maria">JawsDB Maria - Add-ons - Heroku Elements</a>
 */
public final class JawsDBMaria {
    private final @NonNull String host;
    private final @Nullable String username;
    private final @Nullable String password;
    private final @Nullable String database;
    private final int port;

    private static final @Nullable JawsDBMaria INSTANCE;

    static {
        final String mariaUrl = System.getenv("JAWSDB_MARIA_URL");
        final MySQLUrlParser.Result result = MySQLUrlParser.parse(mariaUrl);
        if (result != null) {
            INSTANCE = new JawsDBMaria(result);
        } else {
            INSTANCE = null;
        }
    }

    JawsDBMaria(final MySQLUrlParser.Result result) {
        host = Objects.requireNonNull(result.host);
        username = result.username;
        password = result.password;
        database = result.database;
        port = result.port;
    }

    public static @Nullable JawsDBMaria get() {
        return INSTANCE;
    }

    public @NonNull String getHost() {
        return host;
    }

    public @NonNull String getUsername() {
        if (username == null) {
            throw new IllegalStateException("JawsDB Maria should have `username`. but not.");
        }
        return username;
    }

    public @NonNull String getPassword() {
        if (password == null) {
            throw new IllegalStateException("JawsDB Maria should have `password`, but not.");
        }
        return password;
    }

    public @NonNull String getDatabase() {
        if (database == null) {
            throw new IllegalStateException("JawsDB Maria should have `database`, but not.");
        }
        return database;
    }

    public int getPort() {
        return port;
    }
}
