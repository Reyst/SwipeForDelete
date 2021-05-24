package gsihome.reyst.swipefordelete

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
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


        val callback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit
        }

        ItemTouchHelper(callback).attachToRecyclerView(rv)

    }
}


abstract class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }



    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val holder = (viewHolder as? PersonVH) ?: return

        val half = (holder.itemView.right - holder.itemView.left) / 2F

        Log.wtf("INSPECT", "dx: ${dX}, current: ${holder.content.translationX}")

        holder.content.translationX =
            if (dX < 0) maxOf(-half, dX)
            else minOf(0F, dX)


        //        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}