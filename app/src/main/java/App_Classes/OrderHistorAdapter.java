package App_Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.R;

import java.util.List;

public class OrderHistorAdapter extends RecyclerView.Adapter<OrderHistorAdapter.OrderHistoryViewHolder> {

    private List<OrderHistory> orderHistoryList;

    public OrderHistorAdapter(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_order_history, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        OrderHistory orderHistory = orderHistoryList.get(position);
        holder.textViewOrderNum.setText(orderHistory.getOrderNumber());
        holder.textViewStatus.setText(orderHistory.getStatus());
        holder.textViewDate.setText(orderHistory.getDate());
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOrderNum;
        TextView textViewStatus;
        TextView textViewDate;

        public OrderHistoryViewHolder(View itemView) {
            super(itemView);
            textViewOrderNum = itemView.findViewById(R.id.ordernum);
            textViewStatus = itemView.findViewById(R.id.status);
            textViewDate = itemView.findViewById(R.id.date);
        }
    }
}
