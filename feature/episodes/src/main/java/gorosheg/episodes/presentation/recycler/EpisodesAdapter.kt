package gorosheg.episodes.presentation.recycler

import android.view.View
import gorosheg.episodes.R
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.recycler.BaseRecyclerAdapter

internal class EpisodesAdapter : BaseRecyclerAdapter<Episode, EpisodesRecyclerViewHolder>() {

    override val layout: Int = R.layout.list_of_episodes

    override fun createViewHolder(view: View): EpisodesRecyclerViewHolder {
        return EpisodesRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}