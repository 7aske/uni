package com.taske.duncan.activity.server;

import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;
import static com.taske.duncan.intent.IntentExtraDataKeys.SERVER_ID_EXTRA_DATA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.taske.duncan.MainActivity;
import com.taske.duncan.R;
import com.taske.duncan.adapter.CommandListAdapter;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.Command;
import com.taske.duncan.entity.CommandDao;
import com.taske.duncan.entity.DaoSession;
import com.taske.duncan.entity.Server;
import com.taske.duncan.entity.ServerDao;
import com.taske.duncan.observable.Observable;
import com.taske.duncan.task.SshAsyncTask;
import com.taske.duncan.util.DrawerUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class ServerViewActivity extends AppCompatActivity {
    // @formatter:off
    private TextView    viewHost;
    private TextView    viewPort;
    private TextView    viewGroup;
    private TextView    viewUser;
    private ImageButton testBtn;
    private ListView    viewCommandList;
    private FloatingActionButton btnAddCommand;
    private PopupWindow popupWindow;

    private ServerDao  serverDao;
    private CommandDao commandDao;

    private Server        server;
    private List<Command> commands;

    private CommandListAdapter commandListAdapter = null;
    ActionBarDrawerToggle toggle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_view);

        DaoSession session = DbHelper.getSession(this);
        serverDao          = session.getServerDao();
        commandDao         = session.getCommandDao();

        Intent intent = getIntent();
        try {
            long serverId = Long.parseLong(intent.getStringExtra(SERVER_ID_EXTRA_DATA));
            server   = serverDao.loadByRowId(serverId);
            commands = server.getCommands();
        } catch (Exception ex){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        toggle = DrawerUtil.initDrawerAndToggle(this, R.id.activity_server_add);

        viewHost        = findViewById(R.id.view_host);
        viewPort        = findViewById(R.id.view_port);
        viewGroup       = findViewById(R.id.view_group);
        viewUser        = findViewById(R.id.view_user);
        testBtn         = findViewById(R.id.test_btn);
        viewCommandList = findViewById(R.id.server_command_list);
        btnAddCommand   = findViewById(R.id.add_command_btn);

        viewHost.setText(server.getHost());
        viewPort.setText(String.valueOf(server.getPort()));
        viewGroup.setText(server.getGroupName());
        viewUser.setText(server.getUser());
        // @formatter:on

        Observable<String> observable = new Observable<>();
        observable.subscribe(o -> {
            Log.d("SSH", o);
        });
        testBtn.setOnClickListener(v -> {
            testBtn.setBackgroundColor(getResources().getColor(R.color.primary_dark));
            testBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_insert_link_24));
            new SshAsyncTask(server)
                    .onOutput(data -> {
                        if (data != null && data.equals("1\n")) {
                            testBtn.setBackgroundColor(getResources().getColor(R.color.success));
                            testBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_done_24));
                        } else {
                            testBtn.setBackgroundColor(getResources().getColor(R.color.error));
                            testBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_close_24));
                        }
                    })
                    .onError(err -> {
                        testBtn.setBackgroundColor(getResources().getColor(R.color.error));
                        testBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_close_24));
                    })
                    .execute();
        });

        btnAddCommand.setOnClickListener(view -> openPopup());

        CommandListAdapter customCommandList = new CommandListAdapter(this, server.getCommands());
        viewCommandList.setAdapter(customCommandList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        commands = getCommands();
        updateAdapterData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        commands = getCommands();
        updateAdapterData();
    }

    private void updateAdapterData() {
        ((CommandListAdapter) viewCommandList.getAdapter()).setCommands(commands);
        ((BaseAdapter) viewCommandList.getAdapter()).notifyDataSetChanged();
    }

    public void openPopup() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        double width = size.x * .8;
        LayoutInflater inflater = this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.command_edit_popup, (ViewGroup) this.findViewById(R.id.command_edit_popup));
        popupWindow = new PopupWindow(layout, WRAP_CONTENT, WRAP_CONTENT, true);
        popupWindow.setWidth((int) width);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText editCommand = (EditText) layout.findViewById(R.id.edit_command);

        Button save = (Button) layout.findViewById(R.id.save_popup);
        save.setOnClickListener(view -> {
            String commandStr = editCommand.getText().toString();
            Command command = new Command(null, server.getId(), commandStr);
            commandDao.save(command);
            commands = getCommands();

            if (commandListAdapter == null) {
                commandListAdapter = new CommandListAdapter(ServerViewActivity.this, commands);
                viewCommandList.setAdapter(commandListAdapter);
            }

            ((CommandListAdapter) viewCommandList.getAdapter()).setCommands(commands);
            ((BaseAdapter) viewCommandList.getAdapter()).notifyDataSetChanged();
            popupWindow.dismiss();
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.server_view_menu_item_edit) {
            Intent intent = new Intent(getApplicationContext(), ServerAddActivity.class);
            intent.putExtra(SERVER_ID_EXTRA_DATA, String.valueOf(server.getId()));
            startActivity(intent);
            return true;
        } else if (itemId == R.id.server_view_menu_item_delete) {
            serverDao.delete(server);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
        getMenuInflater().inflate(R.menu.server_view_menu, menu);
        return true;
    }

    public List<Command> getCommands() {
        QueryBuilder<Command> queryBuilder = commandDao.queryBuilder()
                .where(CommandDao.Properties.ServerId.eq(server.getId()));
        return queryBuilder.list();
    }
}