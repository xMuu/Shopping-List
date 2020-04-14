package org.example.shopping.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items") // 自定义表名
public class ListItem {
    @PrimaryKey(autoGenerate = true) // 注明此元素为主键并自动生成
            int ID;

    @ColumnInfo(name = "ItemName") // 自定义列名
    public String Name;

    @ColumnInfo(name = "ItemNumber", defaultValue = "1") // 设置默认值
    public String Number;

    @ColumnInfo(name = "ItemStatus", defaultValue = "false")
    public boolean Status;

    // 需要手动创建一个空的构造函数，否则编译时会报错
    ListItem() {
    }

    // 这个是为了创建对象时方便的构造函数
    public ListItem(String name, String number) {
        Name = name;
        Number = number;
    }

    // 必须为所有的列创建 getter 和 setter 以便进行访问
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

}