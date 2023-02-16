package com.fhs.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhs.fhsnews.adapter.NewsDataAdapter
import com.fhs.fhsnews.databinding.FragmentEventsViewBinding
import com.fhs.fhsnews.overview.OverviewViewModel
import java.text.SimpleDateFormat

private lateinit var calendarView: CalendarView


class EventsViewFragment : Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEventsViewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.eventsRecycler.adapter = NewsDataAdapter()
        binding.eventsRecycler.layoutManager = LinearLayoutManager(context)

        calendarView = binding.eventsDatePicker
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val format = SimpleDateFormat("yyyy-M-dd")
            Log.d(
                TAG,
                "onCreateView: \"$year-${month + 1}-$dayOfMonth\" selected\npattern: $format"
            )
            viewModel.getSearchDateResults(
                format.parse("$year-${month + 1}-$dayOfMonth").time,
                format.parse("$year-${month + 1}-${dayOfMonth + 1}").time
            )
            Log.d(
                TAG,
                "onCreateView: searching between ${
                    format.parse("$year-${month + 1}-$dayOfMonth").time
                } and ${format.parse("$year-${month + 1}-${dayOfMonth + 1}").time}"
            )
            // Shift $month one forward to become 1-12 instead of 0-11
        }
        viewModel.getSearchDateResults(calendarView.date, calendarView.date + 86400000)
        Log.d(
            TAG,
            "onCreateView: searching between ${calendarView.date} and ${calendarView.date + 86400000}"
        )


        return binding.root
    }
}