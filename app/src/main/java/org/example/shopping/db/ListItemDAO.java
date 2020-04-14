package org.example.shopping.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListItemDAO {
    @Insert
        // 插入操作
    void insert(ListItem... items); // 对象类后加三个点表示多个对象，添加时使用逗号分隔

    @Delete
        // 删除操作
    void delete(ListItem... items);

    @Update
        // 更新操作
    void update(ListItem... items);

    @Query("SELECT * FROM items")
        // 查询全部数据
    LiveData<List<ListItem>> getAllItems();

}
