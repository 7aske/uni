package com.taske.duncan.activity.group;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.taske.duncan.R;
import com.taske.duncan.adapter.ServerGroupListAdapter;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.DaoSession;
import com.taske.duncan.entity.ServerGroup;
import com.taske.duncan.entity.ServerGroupDao;
import com.taske.duncan.util.DrawerUtil;

import java.util.List;

public class GroupAddActivity extends AppCompatActivity {
    private ServerGroupDao serverGroupDao;
    private ActionBarDrawerToggle toggle;
    private ListView viewGroupsList;
    private List<ServerGroup> groups;
    private PopupWindow popupWindow;
    private ServerGroup selectedGroup;
    private ServerGroupListAdapter serverGroupListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);

        toggle = DrawerUtil.initDrawerAndToggle(this, R.id.activity_group_add);

        DaoSession session = DbHelper.getSession(this);
        serverGroupDao = session.getServerGroupDao();
        groups = serverGroupDao.loadAll();

        viewGroupsList = findViewById(R.id.group_list);

        serverGroupListAdapter = new ServerGroupListAdapter(this, groups);
        viewGroupsList.setAdapter(serverGroupListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.group_add_menu_item_save) {
            saveGroup();
            return true;
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_add_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        groups = serverGroupDao.loadAll();
        updateAdapterData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        groups = serverGroupDao.loadAll();
        updateAdapterData();
    }

    private void updateAdapterData() {
        ((ServerGroupListAdapter) viewGroupsList.getAdapter()).setGroups(groups);
        ((BaseAdapter) viewGroupsList.getAdapter()).notifyDataSetChanged();
    }

    private void saveGroup() {
        EditText editText = findViewById(R.id.edit_group_name);
        ServerGroup serverGroup = new ServerGroup();
        String groupName = editText.getText().toString();
        if (groupName.isEmpty()) {
            Toast.makeText(this, "Group name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        serverGroup.setName(groupName.toUpperCase());
        serverGroupDao.save(serverGroup);
        groups = serverGroupDao.loadAll();
        updateAdapterData();
    }
}
