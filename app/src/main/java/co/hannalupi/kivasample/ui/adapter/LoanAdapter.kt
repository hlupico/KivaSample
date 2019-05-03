package co.hannalupi.kivasample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.hannalupi.kivasample.R
import co.hannalupi.kivasample.databinding.LoanItemBinding
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.ui.SelectedCallback

class LoanAdapter(val callback : SelectedCallback) : RecyclerView.Adapter<LoanAdapter.ViewHolder>() {

    private var loans : List<Loan> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : LoanItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.loan_item, parent, false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.loan = loans[position]
        holder.binding.callback = callback
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return loans.size
    }

    fun setData(data: List<Loan>) {
        loans = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : LoanItemBinding) : RecyclerView.ViewHolder(binding.root)
}