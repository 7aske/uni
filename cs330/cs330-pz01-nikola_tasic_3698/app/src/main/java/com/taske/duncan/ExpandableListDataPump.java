package com.taske.duncan;

import android.content.Context;

import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.DaoSession;
import com.taske.duncan.entity.Server;
import com.taske.duncan.entity.ServerDao;
import com.taske.duncan.entity.ServerGroup;
import com.taske.duncan.entity.ServerGroupDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListDataPump {
    public static Map<String, List<Server>> getData(Context context) {
        DaoSession session = DbHelper.getSession(context);
        ServerDao serverDao = session.getServerDao();
        ServerGroupDao serverGroupDao = session.getServerGroupDao();

        List<Server> servers = serverDao.loadAll();
        List<ServerGroup> serverGroups = serverGroupDao.loadAll();
        Map<String, List<Server>> serverMap = new HashMap<>();

        // brain was dead
        for (ServerGroup serverGroup : serverGroups) {
            serverMap.put(serverGroup.getName(), new ArrayList<>());
        }
        for (Server server : servers) {
            if (server.getGroup() != null) {
                if (serverMap.containsKey(server.getGroupName())) {
                    serverMap.get(server.getGroupName()).add(server);
                } else {
                    serverMap.put(server.getGroupName(), new ArrayList<Server>(){{
                        add(server);
                    }});
                }
            }
        }
        return serverMap;
    }
}