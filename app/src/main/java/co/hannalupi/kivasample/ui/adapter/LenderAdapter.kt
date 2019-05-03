package co.hannalupi.kivasample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.hannalupi.kivasample.R
import co.hannalupi.kivasample.databinding.LenderItemBinding
import co.hannalupi.kivasample.model.Lender

class LenderAdapter : RecyclerView.Adapter<LenderAdapter.ViewHolder>() {

    private var lenders : List<Lender> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LenderAdapter.ViewHolder {
        val binding : co.hannalupi.kivasample.databinding.LenderItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lender_item, parent, false);
        return LenderAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LenderAdapter.ViewHolder, position: Int) {
        holder.binding.lender = lenders[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return lenders.size
    }

    fun setData(data: List<Lender>) {
        lenders = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : LenderItemBinding) : RecyclerView.ViewHolder(binding.root)
}