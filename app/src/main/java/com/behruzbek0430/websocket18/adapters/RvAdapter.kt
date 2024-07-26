package com.behruzbek0430.websocket18.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.behruzbek0430.websocket18.databinding.ItemRvBinding
import com.behruzbek0430.websocket18.models.Message
import com.behruzbek0430.websocket18.models.MessageX
import com.behruzbek0430.websocket18.models.MyMessage

class RvAdapter(val list: List<MessageX>): RecyclerView.Adapter<RvAdapter.VH>() {
    inner class VH(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun OnBind(user: MessageX){
            itemRvBinding.itemTv.text = user.message

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.OnBind(list[position])
    }

}