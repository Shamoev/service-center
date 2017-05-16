package ua.com.sourceit.jdbc.model;

import ua.com.sourceit.jdbc.exceptions.GenericDaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public abstract class BaseEntity <PK extends Serializable> implements BaseEntityInterface<PK> {
    protected PK id;

    protected final String READ_SQL;
    protected final String DELETE_SQL;
    protected final String CREATE_SQL;
    protected final String UPDATE_SQL;

    private static final String RESOURCES_DB_PROPERTIES = "src/edu/sourceit/homeworks/homework16/resources/db.properties";

    {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(RESOURCES_DB_PROPERTIES));
            props.load(fis);
            READ_SQL   = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".read");
            DELETE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".delete");
            CREATE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".create");
            UPDATE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".update");
        } catch (IOException e) {
            throw new GenericDaoException("Error loading SQL definitions", e);
        }
    }

    @Override
    public String getCreateSql() {
        return CREATE_SQL;
    }

    @Override
    public String getReadSql() {
        return READ_SQL;
    }

    @Override
    public String getUpdateSql() {
        return UPDATE_SQL;
    }

    @Override
    public String getDeleteSql() {
        return DELETE_SQL;
    }

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
