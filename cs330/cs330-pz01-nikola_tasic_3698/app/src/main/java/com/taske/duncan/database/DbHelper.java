package com.taske.duncan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.taske.duncan.entity.DaoMaster;
import com.taske.duncan.entity.DaoSession;

import org.greenrobot.greendao.database.StandardDatabase;


public class DbHelper {
    public final static String DB_NAME = "duncan.db";

    private static DaoSession daoSession = null;

    public static DaoSession getSession(Context context) {
        if (daoSession == null)
            init(context);

        return daoSession;
    }

    private static class DaoMasterHelper extends DaoMaster.OpenHelper {
        public DaoMasterHelper(Context context) {
            super(context, DB_NAME);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                StandardDatabase dba = new StandardDatabase(db);
                DaoMaster.dropAllTables(dba, true);
                DaoMaster.createAllTables(dba, false);
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            StandardDatabase dba = new StandardDatabase(db);
            DaoMaster.createAllTables(dba, false);
        }
    }

    private static void init(Context context) {
        DaoMasterHelper helper = new DaoMasterHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
