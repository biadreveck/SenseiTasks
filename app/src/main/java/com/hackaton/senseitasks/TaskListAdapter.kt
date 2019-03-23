package com.hackaton.senseitasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.hackaton.senseitasks.domain.TaskSummary

class TaskListAdapter(private val context: Context,
                      private val dataSource: List<TaskSummary>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.content_main_list_item, parent, false)

        val titleTextView = rowView.findViewById(R.id.title) as TextView
        val finishedCheckbox = rowView.findViewById(R.id.finished) as CheckBox

        val task = getItem(position) as TaskSummary

        titleTextView.text = task.title
        finishedCheckbox.isChecked = task.isFinished

        return rowView
    }
}