package com.example.payments_test_task_project.payments_page.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.payments_test_task_project.databinding.PaymentItemBinding
import com.example.payments_test_task_project.payments_page.model.Payment

class PaymentsViewHolder(
    private val binding: PaymentItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup,
    ) : this(
        PaymentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )
    @SuppressLint("SetTextI18n")
    fun onBind(item: Payment) {
        with(binding) {
            //            paymentCreatedDate.text = item.created.toString()
            titleOfPayment.text = item.title
//            amountOfPayment.text = item.amount.toString()
        }
    }
}
