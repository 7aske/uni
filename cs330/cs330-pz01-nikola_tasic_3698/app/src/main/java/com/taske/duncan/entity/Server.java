package com.taske.duncan.entity;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@Entity(generateGettersSetters = false)
public class Server implements Serializable {
    private static final long serialVersionUID = 1533428857866398600L;
    @Id
    private Long id;
    private String uuid;
    @NotNull
    private String host;
    private String user = "root";
    private long groupId;
    @ToOne(joinProperty = "groupId")
    private ServerGroup group;
    private Integer port = 22;
    private String password;
    private String privateKey;
    private String publicKey;
    @ToMany(referencedJoinProperty = "serverId")
    List<Command> commands;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 617962843)
    private transient ServerDao myDao;
    @Generated(hash = 201187923)
    private transient Long group__resolvedKey;
    public Server() {
    }

    @Generated(hash = 1256338746)
    public Server(Long id, String uuid, @NotNull String host, String user, long groupId, Integer port,
            String password, String privateKey, String publicKey) {
        this.id = id;
        this.uuid = uuid;
        this.host = host;
        this.user = user;
        this.groupId = groupId;
        this.port = port;
        this.password = password;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getGroupName() {
        return getGroup() == null ? "NO GROUP" : getGroup().getName();
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2124603864)
    public ServerGroup getGroup() {
        long __key = this.groupId;
        if (group__resolvedKey == null || !group__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ServerGroupDao targetDao = daoSession.getServerGroupDao();
            ServerGroup groupNew = targetDao.load(__key);
            synchronized (this) {
                group = groupNew;
                group__resolvedKey = __key;
            }
        }
        return group;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1742210772)
    public void setGroup(@NotNull ServerGroup group) {
        if (group == null) {
            throw new DaoException(
                    "To-one property 'groupId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.group = group;
            groupId = group.getId();
            group__resolvedKey = groupId;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1994974432)
    public List<Command> getCommands() {
        if (commands == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CommandDao targetDao = daoSession.getCommandDao();
            List<Command> commandsNew = targetDao._queryServer_Commands(id);
            synchronized (this) {
                if (commands == null) {
                    commands = commandsNew;
                }
            }
        }
        return commands;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 400571921)
    public synchronized void resetCommands() {
        commands = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 766171920)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getServerDao() : null;
    }
}