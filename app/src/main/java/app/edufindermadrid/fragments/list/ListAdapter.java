package app.edufindermadrid.fragments.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.edufindermadrid.R;
import app.edufindermadrid.entities.EduCenter;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<EduCenter> edList;

    public ListAdapter(List<EduCenter> edList) {
        this.edList = edList;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.edu_center_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        EduCenter ed = edList.get(position);
        holder.tvEduName.setText(ed.getTittle());
    }

    @Override
    public int getItemCount() {
        return edList.size();
    }

    public class ListViewHolder  extends RecyclerView.ViewHolder{
        public TextView tvEduName;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEduName = itemView.findViewById(R.id.tvEduName);
        }
    }
}
