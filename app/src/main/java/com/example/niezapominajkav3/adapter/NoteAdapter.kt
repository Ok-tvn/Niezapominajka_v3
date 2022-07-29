package com.example.niezapominajkav3.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.niezapominajkav3.UpdateNote
import com.example.niezapominajkav3.databinding.ItemNoteBinding
import com.example.niezapominajkav3.db.ReminderEntity
import com.example.niezapominajkav3.utils.Constants.BUNDLE_REMINDER_ID

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    private lateinit var binding: ItemNoteBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ReminderEntity) {
            //InitView
            binding.apply {
                //Set text
                tvTitle.text = item.reminderTitle
                tvDesc.text= item.reminderDescription

                root.setOnClickListener{
                    val intent=Intent(context, UpdateNote::class.java)
                    intent.putExtra(BUNDLE_REMINDER_ID, item.reminderID)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ReminderEntity>() {
        override fun areItemsTheSame(oldItem: ReminderEntity, newItem: ReminderEntity): Boolean {
            return oldItem.reminderID == newItem.reminderID
        }

        override fun areContentsTheSame(oldItem: ReminderEntity, newItem: ReminderEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}