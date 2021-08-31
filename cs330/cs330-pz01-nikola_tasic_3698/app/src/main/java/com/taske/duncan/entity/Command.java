package com.taske.duncan.entity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import lombok.Data;

@Data
@Entity(generateGettersSetters = false)
public class Command {
    private static final long serialVersionUID = -4498342718374292351L;
    @Id
    private Long id;
    private long serverId;
    @ToOne(joinProperty = "serverId")
    private Server server;
    private String command;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 33699342)
    private transient CommandDao myDao;
    @Generated(hash = 262073390)
    public Command(Long id, long serverId, String command) {
        this.id = id;
        this.serverId = serverId;
        this.command = command;
    }
    @Generated(hash = 1834133387)
    public Command() {
    }
    @Generated(hash = 994707259)
    private transient Long server__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1876424865)
    public Server getServer() {
        long __key = this.serverId;
        if (server__resolvedKey == null || !server__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ServerDao targetDao = daoSession.getServerDao();
            Server serverNew = targetDao.load(__key);
            synchronized (this) {
                server = serverNew;
                server__resolvedKey = __key;
            }
        }
        return server;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1217160630)
    public void setServer(@NotNull Server server) {
        if (server == null) {
            throw new DaoException(
                    "To-one property 'serverId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.server = server;
            serverId = server.getId();
            server__resolvedKey = serverId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1329402299)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCommandDao() : null;
    }
}
