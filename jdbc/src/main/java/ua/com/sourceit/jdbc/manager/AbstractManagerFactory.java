package ua.com.sourceit.jdbc.manager;

public abstract class AbstractManagerFactory {
    public static AbstractManagerFactory getManagerFactory() {
        return new MySqlManagerFactory();
    }

    public abstract UserManager getUserManager();
}
