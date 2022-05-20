package gorosheg.episodes.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gorosheg.episodes.R
import gorosheg.myapplication.model.Episode

internal class EpisodesRecyclerViewHolder(charView: View) : RecyclerView.ViewHolder(charView) {

    private val episodeName: TextView = charView.findViewById(R.id.name)
    private val airDate: TextView = charView.findViewById(R.id.airDate)

    fun bind(episode: Episode) {
        episodeName.text = episode.name
        airDate.text = episode.airDate
    }
}