package com.example.payments_test_task_project.payments_page.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.payments_test_task_project.R
import com.example.payments_test_task_project.payments_page.model.Payment

class PaymentsAdapter : RecyclerView.Adapter<PaymentsViewHolder>() {
    private val myPayments = mutableListOf<Payment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
        return PaymentsViewHolder(parent)
    }

    override fun getItemCount(): Int = myPayments.size


    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {
        val listItem = myPayments[position]
        holder.onBind(listItem)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setMyPayments(items: List<Payment>) {
        myPayments.clear()
        myPayments.addAll(items)
        notifyDataSetChanged()
    }
}

