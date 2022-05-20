package gorosheg.characters.presentation.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal fun RecyclerView.recyclerViewListener(getCharacters: (Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            recyclerView.onScrolled(getCharacters)
        }
    })
}

private inline fun RecyclerView.onScrolled(getCharacters: (Int) -> Unit) {
    val layoutManager = layoutManager as LinearLayoutManager
    val visibleItemCount: Int = layoutManager.childCount
    val totalItemCount = layoutManager.itemCount
    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
        getCharacters.invoke(totalItemCount / 20 + 1)
    }
}