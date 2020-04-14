package org.example.shopping.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ListItemRepository {
    private ListItemDAO listItemDAO;

    public ListItemRepository(Context context) {
        // 获取 AppDatabase 并获取对应 DAO
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
        listItemDAO = appDatabase.getListItemDAO();
    }

    // LiveData 类型会自动在进行多线程操作，无需手动新建多线程异步操作任务
    public LiveData<List<ListItem>> getAllItems() {
        return listItemDAO.getAllItems();
    }

    // 调用多线程异步操作任务
    public void addListItem(ListItem... items) {
        new insertAsyncTask(listItemDAO).execute(items);
    }

    public void deleteListItem(ListItem... items) {
        new deleteAsyncTask(listItemDAO).execute(items);
    }

    public void updateListItem(ListItem... items) {
        new updateAsyncTask(listItemDAO).execute(items);
    }

    // 创建多线程异步操作任务，下同
    static class insertAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListItemDAO listItemDAO;

        insertAsyncTask(ListItemDAO listItemDAO) {
            this.listItemDAO = listItemDAO;
        }

        @Override
        protected Void doInBackground(ListItem... items) {
            listItemDAO.insert(items);
            return null;
        }
    }

    static class deleteAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListItemDAO listItemDAO;

        deleteAsyncTask(ListItemDAO listItemDAO) {
            this.listItemDAO = listItemDAO;
        }

        @Override
        protected Void doInBackground(ListItem... items) {
            listItemDAO.delete(items);
            return null;
        }
    }

    static class updateAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListItemDAO listItemDAO;

        updateAsyncTask(ListItemDAO listItemDAO) {
            this.listItemDAO = listItemDAO;
        }

        @Override
        protected Void doInBackground(ListItem... items) {
            listItemDAO.update(items);
            return null;
        }
    }
}
