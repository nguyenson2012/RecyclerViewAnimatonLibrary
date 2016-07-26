package com.example.asus.recyclerviewanimatonlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus on 7/25/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderStudent> {
    private ArrayList<Student> listStudent;
    private Context mContext;
    private OnItemClickListener listener;
    private int currentCategoryPosition = 0;

    public RecyclerViewAdapter(Context context,ArrayList<Student> students,OnItemClickListener listener){
        this.mContext=context;
        this.listStudent=students;
        this.listener=listener;
    }
    @Override
    public ViewHolderStudent onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout,parent,false);
        return new ViewHolderStudent(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderStudent holder, int position) {
        Student student=listStudent.get(position);
        holder.bind(student,listener);
        holder.imgAvatar.setImageResource(R.drawable.man_icon);
        holder.tvName.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }
    public void remove(int position) {
        listStudent.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String text, int position) {
        listStudent.add(position, new Student(text));
        notifyItemInserted(position);
    }
    public interface OnItemClickListener {
        void onItemClick(Student item, int currentPosition);
    }
    public class ViewHolderStudent extends RecyclerView.ViewHolder {
        public ImageView imgAvatar;
        public TextView tvName;
        public ViewHolderStudent(View itemView) {
            super(itemView);
            imgAvatar=(ImageView)itemView.findViewById(R.id.img_avatar);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
        }
        public void bind(final Student item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(currentCategoryPosition);
                    currentCategoryPosition = getLayoutPosition();
                    notifyItemChanged(currentCategoryPosition);
                    listener.onItemClick(item, currentCategoryPosition);
                }
            });
        }
    }
}
