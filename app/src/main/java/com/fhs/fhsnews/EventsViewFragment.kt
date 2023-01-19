package com.fhs.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.adapter.EventsViewAdapter
import com.fhs.fhsnews.databinding.FragmentEventsViewBinding
import java.sql.Date
import java.text.SimpleDateFormat

private var _binding: FragmentEventsViewBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView
private lateinit var calendarView: CalendarView

class EventsViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.eventsRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        (activity as AppCompatActivity).supportActionBar?.title = "FHS Events"

        calendarView = binding.eventsDatePicker
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val format = SimpleDateFormat("yyyy-MM-dd")
            recyclerView.adapter = EventsViewAdapter(format.parse("$year-${month + 1}-$dayOfMonth"))
            // Shift $month one forward to become 1-12 instead of 0-11
        }
        recyclerView.adapter = EventsViewAdapter(Date(calendarView.date))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}