package com.taske.duncan.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;

import lombok.Data;


@Data
@Entity(generateGettersSetters = false)
public class ServerGroup implements Serializable {
    private static final long serialVersionUID = 3268722208192327342L;
    @Id
    private Long id;
    @NotNull
    @Index
    private String name;

    public ServerGroup() {
    }

    @Generated(hash = 806374252)
    public ServerGroup(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        if (name == null) return "null";
        return name.toUpperCase();
    }
}
