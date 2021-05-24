package gsihome.reyst.swipefordelete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(persons: List<Person>): RecyclerView.Adapter<PersonVH>() {

    private val items = mutableListOf<Person>().apply { addAll(persons) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
            .let {
                PersonVH(it) { position -> remove(position) }
            }
    }

    private fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}