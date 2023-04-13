package com.fhs.fhsnews.adapter

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.FragmentClubScrollerDirections
import com.fhs.fhsnews.FragmentEventsViewDirections
import com.fhs.fhsnews.FragmentNewsScrollerDirections
import com.fhs.fhsnews.R
import com.fhs.fhsnews.databinding.*
import com.fhs.fhsnews.model.*

// Adapter to find the correct card type to use for an item and inflate it

class FeedDataAdapter : ListAdapter<FeedData, RecyclerView.ViewHolder>(DiffCallback) {

	// TODO: Filter by tag

	class CardNewsViewHolder(private var binding: CardNewsBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(thisArticle: Article) {
			binding.article = thisArticle

			Log.d(TAG, "bind: binding article $thisArticle")
			/* TODO: Format posted date relatively. Must be done manually, android's own system
				for this requires minimum API 24 (We are using minimum API 21) */
			binding.newsCardConstraintLayout.setOnClickListener {
				var action: NavDirections
				val currentFragment = binding.root.findNavController().currentDestination!!.id
				Log.d(TAG, "bind: current destination is $currentFragment")
				when (currentFragment) {
					R.id.newsScrollerFragment -> {
						action =
							FragmentNewsScrollerDirections.actionNewsScrollerFragmentToOpenArticleFragment(
								articleId = thisArticle.articleId
							)
					}
					R.id.eventsViewFragment -> {
						action =
							FragmentEventsViewDirections.actionEventsViewFragmentToOpenArticleFragment(
								articleId = thisArticle.articleId
							)
					}
					else -> {
						error("Can't figure out which directions to use")
					}
				}
				binding.root.findNavController().navigate(action)
			}
			binding.executePendingBindings()
		}
	}

	class CardClubViewHolder(private var binding: CardClubBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(club: Club) {
			binding.club = club
			binding.clubCardConstraintLayout.setOnClickListener {
				Log.d(ContentValues.TAG, "onBindViewbinding: club click")
				val action =
					FragmentClubScrollerDirections.actionClubScrollerFragmentToOpenClubFragment(
						clubId = club.clubId
					)
				binding.root.findNavController().navigate(action)
			}
			binding.executePendingBindings()
		}
	}

	class CardWeatherViewHolder(private var binding: CardWeatherBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(weather: WeatherData) {
			weather.current_temp += "°F"
			binding.weatherData = weather
			binding.executePendingBindings()
		}
	}

	class CardAlertViewHolder(private var context: Context, private var binding: CardAlertBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(alert: Alert) {
			binding.alert = alert

			val cardConstraint: ConstraintLayout = binding.alertCardConstraintLayout
			val cardFlow: Flow = binding.buttonsFlow
			for (button in alert.links) {
				var linkButton = Button(context)
				linkButton.text = button.key
				linkButton.id = View.generateViewId()
				cardConstraint.addView(linkButton)
				cardFlow.addView(linkButton)
				linkButton.setOnClickListener {
					val queryUrl: Uri = Uri.parse(button.value)
					val intent = Intent(Intent.ACTION_VIEW, queryUrl)
					context.startActivity(intent)
				}
			}

			binding.executePendingBindings()
		}
	}

	class CardLunchViewHolder(private var binding: CardLunchBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(lunchData: LunchData) {
			binding.lunchData = lunchData
		}
	}

	class CardErrorUnknownViewHolder(private var binding: CardErrorUnknownBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(error: Error) {
			binding.error = error
		}
	}

	class CardErrorWarningViewHolder(private var binding: CardErrorWarningBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(error: Error) {
			binding.error = error
		}
	}

	class CardErrorProblemViewHolder(private var binding: CardErrorProblemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(error: Error) {
			binding.error = error
		}
	}

	companion object DiffCallback : DiffUtil.ItemCallback<FeedData>() {
		override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
			return when (newItem.itemType) {
				"Article" -> {
					oldItem.article.articleId == newItem.article.articleId
				}
				"Club" -> {
					oldItem.club.clubId == newItem.club.clubId
				}
				"WeatherData" -> {
					oldItem.weatherData.time == newItem.weatherData.time
				}
				"Alert" -> {
					oldItem.alert.text == newItem.alert.text
				}
				"LunchData" -> {
					oldItem.lunchData.time == newItem.lunchData.time
				}
				else -> {
					false
				}
			}
		}

		override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
			return when (newItem.itemType) {
				"Article" -> {
					oldItem.article.text == newItem.article.text
				}
				"Club" -> {
					oldItem.club.clubText == newItem.club.clubText
				}
				"WeatherData" -> {
					oldItem.weatherData.time == newItem.weatherData.time
				}
				"Alert" -> {
					oldItem.alert.text == newItem.alert.text
				}
				"LunchData" -> {
					oldItem.lunchData.time == newItem.lunchData.time
				}
				else -> {
					false
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			0 -> {
				CardNewsViewHolder(
					CardNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				)
			}
			1 -> {
				CardClubViewHolder(
					CardClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				)
			}
			2 -> {
				CardWeatherViewHolder(
					CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				)
			}
			3 -> {
				CardAlertViewHolder(
					parent.context,
					CardAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				)
			}
			4 -> {
				CardLunchViewHolder(
					CardLunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				)
			}
			else -> { // TODO: "Show unknown objects" option in settings to make unknown cards show, otherwise make them hidden
				CardErrorUnknownViewHolder(
					CardErrorUnknownBinding.inflate(
						LayoutInflater.from(parent.context),
						parent,
						false
					)
				)
			}
		}
	}

	override fun getItemViewType(position: Int): Int {
		val thisItem = getItem(position)
		Log.d(TAG, "getItemViewType: getting item view type for $thisItem")
		return when (thisItem.itemType) {
			"Article" -> {
				0 // Article
			}
			"Club" -> {
				1 // Club
			}
			"WeatherData" -> {
				2 // Weather Data
			}
			"Alert" -> {
				3 // Alert
			}
			"LunchData" -> {
				4 // Lunch Menu Data
			}
			else -> {
				-1 // I Don't Know What This Thing Is
			}
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val thisItem = getItem(position)
		val thisItemViewType = getItemViewType(position)
		Log.d(TAG, "onBindViewHolder: position $position is ${getItemViewType(position)}")
		when (thisItemViewType) {
			0 -> {
				Log.d(TAG, "onBindViewHolder: binding article $thisItem")
				(holder as CardNewsViewHolder).bind(thisItem.article)
			}
			1 -> {
				Log.d(TAG, "onBindViewHolder: binding club $thisItem")
				(holder as CardClubViewHolder).bind(thisItem.club)
			}
			2 -> {
				Log.d(TAG, "onBindViewHolder: binding weather $thisItem")
				(holder as CardWeatherViewHolder).bind(thisItem.weatherData)
			}
			3 -> {
				Log.d(TAG, "onBindViewHolder: binding alert $thisItem")
				(holder as CardAlertViewHolder).bind(thisItem.alert)
			}
			4 -> {
				Log.d(TAG, "onBindViewHolder: binding lunchData $thisItem")
				(holder as CardLunchViewHolder).bind(thisItem.lunchData)
			}
			else -> {
				Log.d(TAG, "onBindViewHolder: binding error $thisItem")
				(holder as CardErrorUnknownViewHolder).bind(thisItem.error)
			}
		}
	}
}