package com.taske.duncan.adapter;

import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.taske.duncan.R;
import com.taske.duncan.database.DbHelper;
import com.taske.duncan.entity.ServerGroup;
import com.taske.duncan.entity.ServerGroupDao;

import java.util.List;

public class ServerGroupListAdapter extends BaseAdapter {
    private Activity context;
    private List<ServerGroup> groups;
    private PopupWindow popupWindow;
    private ServerGroupDao serverGroupDao;

    public ServerGroupListAdapter(Activity context, List<ServerGroup> groups) {
        this.context = context;
        this.groups = groups;
        this.serverGroupDao = DbHelper.getSession(context).getServerGroupDao();
    }

    public List<ServerGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ServerGroup> groups) {
        this.groups = groups;
    }

    public static class ViewHolder {
        TextView textGroupName;
        ImageButton editButton;
        ImageButton deleteButton;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return groups.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            row = inflater.inflate(R.layout.group_row_item, null, true);

            // @formatter:off
            viewHolder.textGroupName  = (TextView)    row.findViewById(R.id.group_name);
            viewHolder.editButton = (ImageButton) row.findViewById(R.id.edit_group);
            viewHolder.deleteButton = (ImageButton) row.findViewById(R.id.delete_group);
            // @formatter:on

            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textGroupName.setText("" + groups.get(position).getName());

        final int positionPopup = position;
        viewHolder.deleteButton.setOnClickListener(view -> deleteGroup(positionPopup));
        viewHolder.editButton.setOnClickListener(view -> editPopup(positionPopup));
        return row;
    }

    public void deleteGroup(final int position) {
        serverGroupDao.delete(groups.get(position));
        groups.remove(position);
        ServerGroupListAdapter.this.notifyDataSetChanged();
    }

    public void editPopup(final int positionPopup) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        double width = size.x * .8;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.group_edit_popup, (ViewGroup) context.findViewById(R.id.group_edit_popup));
        popupWindow = new PopupWindow(layout, WRAP_CONTENT, WRAP_CONTENT, true);
        popupWindow.setWidth((int) width);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText groupEdit = (EditText) layout.findViewById(R.id.edit_group);
        groupEdit.setText(groups.get(positionPopup).getName());

        Button save = (Button) layout.findViewById(R.id.save_popup);

        save.setOnClickListener(view -> {
            String newGroup = groupEdit.getText().toString();
            ServerGroup group = groups.get(positionPopup);
            group.setName(newGroup.toUpperCase());
            serverGroupDao.update(group);
            groups = (List<ServerGroup>) serverGroupDao.loadAll();
            notifyDataSetChanged();
            popupWindow.dismiss();
        });
    }

}
