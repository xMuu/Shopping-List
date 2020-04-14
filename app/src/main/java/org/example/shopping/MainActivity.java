package org.example.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.example.shopping.db.ListItem;
import org.example.shopping.db.ListItemRepository;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ListItemRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        repository = new ListItemRepository(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewItemDialog(view).show();
            }
        });
    }

    private AlertDialog createNewItemDialog(final View view) {
        final View dialogView;
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(view.getContext()));
        dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setTitle("添加新物品").setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextInputEditText itemNameInput = dialogView.findViewById(R.id.item_name_input);
                TextInputEditText itemNumberInput = dialogView.findViewById(R.id.item_number_input);
                String name = Objects.requireNonNull(itemNameInput.getText()).toString();
                String number = Objects.requireNonNull(itemNumberInput.getText()).toString();
                if (!name.equals("")) {
                    repository.addListItem(new ListItem(name, number));
                    Snackbar.make(view, "物品已添加", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "物品名称不能为空", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        return builder.create();
    }
}
