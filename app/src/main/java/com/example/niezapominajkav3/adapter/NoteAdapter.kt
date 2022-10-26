package com.example.niezapominajkav3.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.niezapominajkav3.UpdateNote
import com.example.niezapominajkav3.databinding.ItemNoteBinding
import com.example.niezapominajkav3.db.ReminderEntity2
import com.example.niezapominajkav3.utils.Constants.BUNDLE_REMINDER_ID2

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

    inner class DiaryGestureListener : GestureDetector.SimpleOnGestureListener(){

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float,
        ):Boolean{
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.x?.minus(downEvent!!.y) ?: 0.0F

            if(Math.abs(diffX) > Math.abs(diffY)){
                if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX)> SWIPE_VELOCITY_THRESHOLD){
                    if(diffX > 0){
                        //right swipe
                        this@NoteAdapter.onSwipeRight()
                    }else{
                        //left swipe
                        this@NoteAdapter.onSwipeLeft()
                    }
                }
            }else{
                if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY)> SWIPE_VELOCITY_THRESHOLD){
                    if(diffY > 0){
                        //top swipe
                        this@NoteAdapter.onSwipeTop()
                    }else{
                        //bottom swipe
                        this@NoteAdapter.onSwipeBottom()
                    }
                }

            }

            return super.onFling(downEvent, moveEvent, velocityX, velocityY)
        }

    }
    private fun onSwipeRight(){
        //Toast.makeText(this, "Right Swipe", Toast.LENGTH_LONG).show()
    }
    private fun onSwipeLeft(){

    }
    private fun onSwipeTop(){

    }
    private fun onSwipeBottom(){

    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {



        @SuppressLint("SetTextI18n")
        fun bind(item: ReminderEntity2) {
            //InitView
            binding.apply {
                //Set text
                tvTitle.text = item.reminderTitle
                tvDesc.text= item.reminderDate+" ,"+item.reminderTime

                root.setOnClickListener{
                    val intent=Intent(context, UpdateNote::class.java)
                    intent.putExtra(BUNDLE_REMINDER_ID2, item.reminderID)
                    context.startActivity(intent)
                }

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ReminderEntity2>() {
        override fun areItemsTheSame(oldItem: ReminderEntity2, newItem: ReminderEntity2): Boolean {
            return oldItem.reminderID == newItem.reminderID
            //return oldItem.reminderCategory == newItem.reminderCategory
        }

        override fun areContentsTheSame(oldItem: ReminderEntity2, newItem: ReminderEntity2): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}
