package com.dns.wordsapp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

//Class LetterAdapter untuk RecyclerView pada MainActivity
class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    //generate CharRange dari A-Z dan mengubahnya dalam bentuk list
    private val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //membuat views dengan R.layout.item_view sebagai template
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        //membuat intent untuk menampilkan list words
        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)

            //memanggil variable LETTER dari DetailActivity
            intent.putExtra(DetailActivity.LETTER, holder.button.text.toString())

            context.startActivity(intent)
        }
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            val customString = host.context?.getString(R.string.look_up_words)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
            info.addAction(customClick)
        }
    }
}