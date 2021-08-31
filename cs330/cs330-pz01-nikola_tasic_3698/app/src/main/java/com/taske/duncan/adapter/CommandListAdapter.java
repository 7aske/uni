package com.taske.duncan.adapter;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.taske.duncan.R;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.Command;
import com.taske.duncan.entity.CommandDao;
import com.taske.duncan.task.SshAsyncTask;

import java.util.List;

public class CommandListAdapter extends BaseAdapter {
    private Activity context;
    private List<Command> commands;
    private PopupWindow popupWindow;
    private CommandDao commandDao;

    public CommandListAdapter(Activity context, List<Command> commands) {
        this.context = context;
        this.commands = commands;
        this.commandDao = DbHelper.getSession(context).getCommandDao();
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public static class ViewHolder {
        TextView textCommand;
        ImageButton editButton;
        ImageButton deleteButton;
        ImageButton runButton;
    }

    @Override
    public int getCount() {
        return commands.size();
    }

    @Override
    public Object getItem(int position) {
        return commands.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commands.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            row = inflater.inflate(R.layout.command_row_item, null, true);

            // @formatter:off
            viewHolder.textCommand  = (TextView)    row.findViewById(R.id.command_text);
            viewHolder.editButton   = (ImageButton) row.findViewById(R.id.edit_command);
            viewHolder.deleteButton = (ImageButton) row.findViewById(R.id.delete_command);
            viewHolder.runButton    = (ImageButton) row.findViewById(R.id.run_command);
            // @formatter:on

            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textCommand.setText("" + commands.get(position).getCommand());

        final int positionPopup = position;
        viewHolder.editButton.setOnClickListener(view -> editPopup(positionPopup));
        viewHolder.runButton.setOnClickListener(view -> runCommand(positionPopup));
        viewHolder.deleteButton.setOnClickListener(view -> deleteCommand(positionPopup));
        return row;
    }

    public void deleteCommand(final int position) {
        commandDao.delete(commands.get(position));
        commands.remove(position);
        CommandListAdapter.this.notifyDataSetChanged();
    }

    public void editPopup(final int positionPopup) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        double width = size.x * .8;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.command_edit_popup, (ViewGroup) context.findViewById(R.id.command_edit_popup));
        popupWindow = new PopupWindow(layout, WRAP_CONTENT, WRAP_CONTENT, true);
        popupWindow.setWidth((int) width);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText commandEdit = (EditText) layout.findViewById(R.id.edit_command);
        commandEdit.setText(commands.get(positionPopup).getCommand());

        Button save = (Button) layout.findViewById(R.id.save_popup);

        save.setOnClickListener(view -> {
            String newCommand = commandEdit.getText().toString();
            Command command = commands.get(positionPopup);
            command.setCommand(newCommand);
            commandDao.update(command);
            commands = (List<Command>) commandDao.loadAll();
            notifyDataSetChanged();
            popupWindow.dismiss();
        });
    }

    public void runCommand(final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.command_run_popup, (ViewGroup) context.findViewById(R.id.command_run_popup));
        popupWindow = new PopupWindow(layout, MATCH_PARENT, MATCH_PARENT, true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        Command command = commands.get(position);
        final TextView textViewOutput = layout.findViewById(R.id.command_output);
        final TextView textViewStatus = layout.findViewById(R.id.command_status_output);
        final ImageButton btnCopy = layout.findViewById(R.id.command_output_copy);

        btnCopy.setOnClickListener(view -> {
            String text = textViewOutput.getText().toString();
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("key", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
        });

        new SshAsyncTask(command)
                .onProgress(data -> textViewStatus.setText(data))
                .onOutput(data -> textViewOutput.setText(textViewOutput.getText() + "\n" + data))
                .execute();
    }
}
