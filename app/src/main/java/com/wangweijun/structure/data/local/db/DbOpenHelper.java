package com.wangweijun.structure.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.greenrobot.greendao.database.Database;

/**
 * Created by wangweijun1 on 2017/6/17.
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, AccountDao.class);
    }
}
