package org.example.shopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.example.shopping.db.ListItem;
import org.example.shopping.db.ListItemRepository;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    private List<ListItem> mData;

    ListAdapter(List<ListItem> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.itemStatus.setChecked(mData.get(position).Status);
        holder.itemName.setText(mData.get(position).Name);
        String number = "数量：" + mData.get(position).Number;
        holder.itemNumber.setText(number);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListItem temp = mData.get(position);
                temp.Status = !temp.Status;
                new ListItemRepository(v.getContext()).updateListItem(temp);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final ListItem temp = mData.get(position);
                new ListItemRepository(v.getContext()).deleteListItem(temp);
                Snackbar.make(v, "物品已删除", Snackbar.LENGTH_LONG)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new ListItemRepository(v.getContext()).addListItem(temp);
                            }
                        }).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemStatus;
        TextView itemName, itemNumber;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemStatus = itemView.findViewById(R.id.checkbox);
            itemName = itemView.findViewById(R.id.item_name);
            itemNumber = itemView.findViewById(R.id.item_number);
        }
    }
}
