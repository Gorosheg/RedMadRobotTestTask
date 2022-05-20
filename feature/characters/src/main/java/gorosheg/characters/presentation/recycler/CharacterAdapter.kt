package gorosheg.characters.presentation.recycler

import android.view.View
import gorosheg.characters.R
import gorosheg.myapplication.model.Character
import gorosheg.myapplication.recycler.BaseRecyclerAdapter

internal class CharacterAdapter(
    private val onCharClick: (Character) -> Unit
) : BaseRecyclerAdapter<Character, CharactersRecyclerViewHolder>() {

    override val layout: Int = R.layout.list_of_characters

    override fun createViewHolder(view: View): CharactersRecyclerViewHolder {
        return CharactersRecyclerViewHolder(view, onCharClick)
    }

    override fun onBindViewHolder(holder: CharactersRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}