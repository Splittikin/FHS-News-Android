package com.fhs.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhs.fhsnews.adapter.NewsDataAdapter
import com.fhs.fhsnews.databinding.FragmentEventsViewBinding
import com.fhs.fhsnews.overview.OverviewViewModel
import java.text.SimpleDateFormat

private lateinit var calendarView: CalendarView


class FragmentEventsView : Fragment() {
	private val viewModel: OverviewViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		val binding = FragmentEventsViewBinding.inflate(inflater)

		binding.lifecycleOwner = this
		binding.viewModel = viewModel
		binding.eventsRecycler.adapter = NewsDataAdapter()
		binding.eventsRecycler.layoutManager = LinearLayoutManager(context)

		(activity as AppCompatActivity).supportActionBar?.title =
			resources.getString(R.string.title_events)

		calendarView = binding.eventsDatePicker
		calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
			val format = SimpleDateFormat("yyyy-M-dd")
			Log.d(
				TAG,
				"onCreateView: \"$year-${month + 1}-$dayOfMonth\" selected\npattern: $format"
			)
			viewModel.getSearchDateResults(
				format.parse("$year-${month + 1}-$dayOfMonth").time / 1000,
				format.parse("$year-${month + 1}-${dayOfMonth + 1}").time / 1000
			)
			Log.d(
				TAG,
				"onCreateView: searching between ${
					format.parse("$year-${month + 1}-$dayOfMonth").time / 1000
				} and ${format.parse("$year-${month + 1}-${dayOfMonth + 1}").time / 1000}"
			)
			// Shift $month one forward to become 1-12 instead of 0-11
		}

		// Convert to UNIX time in s instead of ms
		val searchTime = calendarView.date / 1000

		viewModel.getSearchDateResults(searchTime, searchTime + 86400)
		Log.d(
			TAG,
			"onCreateView: searching between $searchTime and ${searchTime + 86400}"
		)


		return binding.root
	}
}