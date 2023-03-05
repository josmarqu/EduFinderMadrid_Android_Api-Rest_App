package app.edufindermadrid.fragments.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.edufindermadrid.R;
import app.edufindermadrid.entities.EduCenter;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> implements View.OnClickListener{
    private List<EduCenter> edList;
    private View.OnClickListener listener;
    private Context context;
    private boolean filtered = false;

    public ListAdapter(List<EduCenter> edList, Context context, boolean filtered) {
        this.edList = edList;
        this.context = context;
        this.filtered = filtered;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (filtered) {
            parent.setPadding(0, 160, 0, 0);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.edu_center_item, parent, false);
        itemView.setOnClickListener(this);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        EduCenter ed = edList.get(position);
        holder.tvEduName.setText(ed.getTitle());
        setItemMargins(holder, position);
    }

    public void setItemMargins(@NonNull ListViewHolder holder, int position) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        int marginBot = (int) context.getResources().getDimension(R.dimen.margItemBottom);
        int marginTop =  (int) context.getResources().getDimension(R.dimen.margItemTop);
        if (position == 0) {
            layoutParams.setMargins(0, marginTop , 0, 0);
        }
        else if (position == edList.size() - 1) {
            layoutParams.setMargins(0, marginTop, 0, marginBot);
        }
        else {
            layoutParams.setMargins(0, marginTop, 0, 0);
        }

        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return edList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }

    public class ListViewHolder  extends RecyclerView.ViewHolder{
        public TextView tvEduName;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEduName = itemView.findViewById(R.id.tvEduName);
        }
    }
}
