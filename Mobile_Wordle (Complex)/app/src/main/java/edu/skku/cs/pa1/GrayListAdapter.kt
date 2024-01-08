package edu.skku.cs.pa1

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pa1.R

class GrayListAdapter(val context: Context, val grayArrayList: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
        return grayArrayList.size
    }

    override fun getItem(p0: Int): Any {
        return grayArrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.letterlayout,null)

        val textView = view.findViewById<TextView>(R.id.letterTextView)

        textView.setBackgroundColor(Color.parseColor("#787C7E"))
        textView.setTextColor(Color.parseColor("#FFFFFF"))

        textView.setText(grayArrayList[p0].capitalize())

        return view
    }
}