package gsihome.reyst.swipefordelete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val persons = listOf(
        Person("Sample Text 1", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 2", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 3", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 4", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 5", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 6", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 7", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 8", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 9", Random(System.currentTimeMillis()).nextFloat()),
        Person("Sample Text 10", Random(System.currentTimeMillis()).nextFloat()),

    )
    private val adapter = PersonAdapter(persons)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv)

        rv.adapter = adapter

    }
}

class PersonAdapter(persons: List<Person>): RecyclerView.Adapter<PersonVH>() {

    private val items = mutableListOf<Person>().apply { addAll(persons) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
            .let(::PersonVH)
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

class PersonVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val bg = itemView.findViewById<View>(R.id.bg)
    private val name = itemView.findViewById<TextView>(R.id.name)

    fun bind(person: Person) {
        name.text = person.name
        bg.alpha = person.alpha
    }

}


data class Person(
    val name: String,
    val alpha: Float,
)

