package com.taske.duncan;

import static com.taske.duncan.intent.IntentExtraDataKeys.SERVER_ID_EXTRA_DATA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.taske.duncan.activity.group.GroupAddActivity;
import com.taske.duncan.activity.server.ServerAddActivity;
import com.taske.duncan.activity.server.ServerViewActivity;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.DaoSession;
import com.taske.duncan.entity.Server;
import com.taske.duncan.entity.ServerGroup;
import com.taske.duncan.entity.ServerGroupDao;
import com.taske.duncan.observable.Observable;
import com.taske.duncan.util.DrawerUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private Map<String, List<Server>> expandableListDetail;
    private ActionBarDrawerToggle toggle;

    private ServerGroupDao serverGroupDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // @formatter:off
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession     session        = DbHelper.getSession(this);
        serverGroupDao = session.getServerGroupDao();

        Observable<String> observable = new Observable<>();
        observable.subscribe(payload -> Log.d("SSH", payload));

        toggle = DrawerUtil.initDrawerAndToggle(this, R.id.activity_main);
        QueryBuilder<ServerGroup> queryBuilder = serverGroupDao.queryBuilder().where(ServerGroupDao.Properties.Name.eq("NO GROUP"));
        if (queryBuilder.list().isEmpty()) {
            ServerGroup defaultGroup = new ServerGroup();
            defaultGroup.setName("NO GROUP");
            serverGroupDao.save(defaultGroup);
        }
        // @formatter:on
    }


    @Override
    protected void onStart() {
        super.onStart();
        initializeListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeListView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.main_menu_add_new_server) {
            Intent intent = new Intent(getApplicationContext(), ServerAddActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.main_menu_add_new_group) {
            Intent intent = new Intent(getApplicationContext(), GroupAddActivity.class);
            startActivity(intent);
            return true;
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initializeListView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListDetail = ExpandableListDataPump.getData(this);
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListAdapter.onGroupDelete(index -> {
            String serverGroup = (String) expandableListAdapter.getGroup(index);
            if (!serverGroup.equals("NO GROUP")) {
                serverGroupDao.queryBuilder()
                        .where(ServerGroupDao.Properties.Name.eq(serverGroup))
                        .buildDelete()
                        .executeDeleteWithoutDetachingEntities();
                initializeListView();
            }
        });
        expandableListView.setOnChildClickListener((p, v, gPos, cPos, id) -> {
            String groupTitle = expandableListTitle.get(gPos);
            List<Server> servers = expandableListDetail.get(groupTitle);
            if (servers == null) return false;
            Server server = servers.get(cPos);
            Intent intent = new Intent(getApplicationContext(), ServerViewActivity.class);
            intent.putExtra(SERVER_ID_EXTRA_DATA, String.valueOf(server.getId()));
            startActivity(intent);
            return true;
        });
    }
}