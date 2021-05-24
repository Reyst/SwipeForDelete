package gsihome.reyst.swipefordelete

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonVH(
    itemView: View,
    action: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    private val bg = itemView.findViewById<View>(R.id.bg)
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val btn = itemView.findViewById<View>(R.id.delete)
    val content: View =  itemView.findViewById(R.id.content)

    init {
        btn.setOnClickListener { action(adapterPosition) }
    }

    fun bind(person: Person) {
        name.text = person.name
        bg.alpha = person.alpha
    }

    fun showMenu(show: Boolean) {
        if (show) content.translationX = minOf(content.translationX, -80F)
        else content.translationX = 0F
    }
}