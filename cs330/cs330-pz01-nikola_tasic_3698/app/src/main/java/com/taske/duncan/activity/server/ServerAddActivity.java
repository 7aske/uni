package com.taske.duncan.activity.server;

import static com.taske.duncan.intent.IntentExtraDataKeys.SERVER_ID_EXTRA_DATA;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.GetContent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.taske.duncan.MainActivity;
import com.taske.duncan.R;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.DaoSession;
import com.taske.duncan.entity.Server;
import com.taske.duncan.entity.ServerDao;
import com.taske.duncan.entity.ServerGroup;
import com.taske.duncan.entity.ServerGroupDao;
import com.taske.duncan.util.DrawerUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ServerAddActivity extends AppCompatActivity {
    // @formatter:off
    public  Spinner       groupSelectSpinner;
    private EditText      viewEditHost;
    private EditText      viewEditPort;
    private EditText      viewEditPassword;
    private EditText      viewEditUser;
    private ImageButton   btnPrivateKeyClear;
    private ImageButton   btnPublicKeyClear;
    private TextView      textPrivateKey;
    private TextView      textPublicKey;
    private Uri           uriPublicKey = null;
    private Uri           uriPrivateKey = null;

    private ServerDao      serverDao;
    private ServerGroupDao serverGroupDao;
    private Server         server = null;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_add);

        DaoSession session = DbHelper.getSession(this);
        serverDao      = session.getServerDao();
        serverGroupDao = session.getServerGroupDao();

        List<ServerGroup> groups = serverGroupDao.loadAll().stream()
                .sorted((sg1, _sg2) -> sg1.getName().equals("NO GROUP") ? -1 : 0).collect(Collectors.toList());

        initGroupSelectSpinner(groups);
        toggle = DrawerUtil.initDrawerAndToggle(this, R.id.activity_server_add);

        viewEditHost       = findViewById(R.id.edit_host);
        viewEditPort       = findViewById(R.id.edit_port);
        viewEditUser       = findViewById(R.id.edit_user);
        viewEditPassword   = findViewById(R.id.edit_password);
        btnPrivateKeyClear = findViewById(R.id.private_key_clear);
        btnPublicKeyClear  = findViewById(R.id.public_key_clear);
        textPrivateKey     = findViewById(R.id.private_key_text);
        textPublicKey      = findViewById(R.id.public_key_text);
        initPrivatePublicKeyButtons();
        // @formatter:on

        Intent intent = getIntent();
        try {
            long serverId = Long.parseLong(intent.getStringExtra(SERVER_ID_EXTRA_DATA));
            server = serverDao.loadByRowId(serverId);
        } catch (Exception ex) {
            // ignored
        }

        if (server == null) {
            initForCreate();
            server = new Server();
        } else {
            initForEdit();
        }
    }

    private void initForEdit() {
        viewEditHost.setText(server.getHost());
        viewEditPort.setText(String.valueOf(server.getPort()));
        viewEditUser.setText(server.getUser());
        viewEditPassword.setText(server.getPassword());
        textPrivateKey.setText(server.getPrivateKey());
        textPublicKey.setText(server.getPublicKey());
        int pos = ((ArrayAdapter<ServerGroup>) groupSelectSpinner.getAdapter()).getPosition(server.getGroup());
        if (pos >= 0) {
            groupSelectSpinner.setSelection(pos, true);
        }
    }

    private void initForCreate() {
        viewEditUser.setText("root");
        viewEditPort.setText("22");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.server_add_menu_item_save) {
            saveServer();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.server_add_menu, menu);
        return true;
    }

    private void initGroupSelectSpinner(List<ServerGroup> groups) {
        ArrayAdapter<ServerGroup> adapter = new ArrayAdapter<>(this, R.layout.server_group_item, groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSelectSpinner = findViewById(R.id.edit_group);
        groupSelectSpinner.setAdapter(adapter);
    }

    private void saveServer() {

        try {
            server.setPort(Integer.parseInt(viewEditPort.getText().toString()));
        } catch (NumberFormatException ex) {
            viewEditPort.setText("22");
            server.setPort(22);
        }

        server.setHost(viewEditHost.getText().toString());
        if (server.getUuid() == null)
            server.setUuid(UUID.randomUUID().toString());

        server.setUser(viewEditUser.getText().toString());
        server.setPassword(viewEditPassword.getText().toString());
        ServerGroup serverGroup = (ServerGroup) groupSelectSpinner.getSelectedItem();
        serverGroup = serverGroupDao.loadByRowId(serverGroup.getId());
        server.setGroup(serverGroup);

        // handle files
        if (uriPublicKey != null) {
            String filename = "id_rsa.pub";
            Path destinationPath = Paths.get(this.getFilesDir().getAbsolutePath(), server.getUuid(), filename);
            File destination = new File(destinationPath.toString());
            try {
                copy(uriPublicKey, destination);
                server.setPublicKey(destination.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (uriPrivateKey != null) {
            String filename = "id_rsa";
            Path destinationPath = Paths.get(this.getFilesDir().getAbsolutePath(), server.getUuid(), filename);
            File destination = new File(destinationPath.toString());
            try {
                copy(uriPrivateKey, destination);
                server.setPrivateKey(destination.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (server.getId() == null) {
            serverDao.save(server);
        } else {
            serverDao.update(server);
        }

        startActivity(new Intent(ServerAddActivity.this, MainActivity.class));
    }

    private void initPrivatePublicKeyButtons() {
        ActivityResultLauncher<String> getPublicKey = registerForActivityResult(new GetContent(),
                uri -> {
                    if (uri == null) {
                        textPublicKey.setText("");
                    } else {
                        textPublicKey.setText(uri.getPath());
                    }
                    uriPublicKey = uri;
                });
        textPublicKey.setOnClickListener(view -> getPublicKey.launch("*/*"));
        btnPublicKeyClear.setOnClickListener(view -> {
            server.setPublicKey(null);
            textPublicKey.setText(null);
        });

        ActivityResultLauncher<String> getPrivateKey = registerForActivityResult(new GetContent(),
                uri -> {
                    if (uri == null) {
                        textPrivateKey.setText("");
                    } else {
                        textPrivateKey.setText(uri.getPath());
                    }
                    uriPrivateKey = uri;
                });
        textPrivateKey.setOnClickListener(view -> getPrivateKey.launch("*/*"));
        btnPrivateKeyClear.setOnClickListener(view -> {
            server.setPrivateKey(null);
            textPrivateKey.setText(null);
        });
    }

    private void copy(Uri source, File destination) throws IOException {
        destination.getParentFile().mkdirs();
        try (InputStream in = getContentResolver().openInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {
            int length;
            byte[] bytes = new byte[1024];
            while ((length = in.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
        } catch (Exception ex) {
            Log.e("KEY_COPY", ex.getMessage());
        }
    }
}