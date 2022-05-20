package gorosheg.myapplication.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gorosheg.myapplication.model.Item
import gorosheg.myapplication.utils.ItemsDiffCallback
import gorosheg.myapplication.utils.inflate

abstract class BaseRecyclerAdapter<T : Item<*>, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    abstract val layout: Int

    var items: List<T> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(ItemsDiffCallback(items, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view: View = parent.inflate(layout)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view: View): VH

    final override fun getItemCount(): Int = items.size
}