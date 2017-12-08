package com.wangweijun.structure;

import com.wangweijun.structure.data.local.db.Account;
import com.wangweijun.structure.data.local.db.DaoMaster;
import com.wangweijun.structure.data.local.db.DaoSession;
import com.wangweijun.structure.data.local.db.DbOpenHelper;
import com.wangweijun.structure.data.local.db.MigrationHelper;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

/**
 * Created by wangweijun1 on 2017/12/8.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DbOpenHelperTest {

    DaoSession daoSession;

    @Before
    public void setup() {
        boolean ENCRYPTED = false;
        MigrationHelper.DEBUG = true;
        DbOpenHelper helper2 = new DbOpenHelper(RuntimeEnvironment.application, ENCRYPTED ? "notes-db-encrypted" : "notes-db",
                null);
        Database db = ENCRYPTED ? helper2.getEncryptedWritableDb("super-secret") : helper2.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    @Test
    public void setRibots() {
        System.out.println("daoSession:" + daoSession);
        Account account = new Account();
        account.setPlatformName("草根投资:xxxxx");
        account.setOfficialWeb("http://www.baidu.com");
        account.setUserName("刘德华");
        account.setLoginPassword("loginpwdi");
        account.setPayPassword("paypassworki");
        daoSession.getAccountDao().insert(account);

        List<Account> list = daoSession.getAccountDao().queryBuilder().build().list();
        System.out.println("list size:" + list.size());
        for (Account account1 : list) {
            System.out.println("account1:" + account1);
        }

    }
}
