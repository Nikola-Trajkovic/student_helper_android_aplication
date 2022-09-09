package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.viewholder

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.databinding.LayoutItemBeleskaBinding
import java.util.function.Consumer

class BeleskeViewHolder(private val itemBinding: LayoutItemBeleskaBinding,

                        var onArchiveClicked: Consumer<Int?>,
                        var onDeleteClicked: Consumer<Int?>,
                        var onEditClicked: Consumer<Int?>


) : RecyclerView.ViewHolder(itemBinding.root)  {

    init {
        itemView.findViewById<View>(R.id.archiveBtn).setOnClickListener { view: View? ->
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onArchiveClicked.accept(bindingAdapterPosition)
            }
        }
        itemView.findViewById<View>(R.id.deleteBtn).setOnClickListener { view: View? ->
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onDeleteClicked.accept(bindingAdapterPosition)
            }
        }
        itemView.findViewById<View>(R.id.editBtn).setOnClickListener { view: View? ->
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onEditClicked.accept(bindingAdapterPosition)
            }
        }
    }

    fun bind(beleske: Beleske) {

        itemBinding.naslovTv.text = beleske.naslov
        itemBinding.contentTv.text = beleske.content

    }
}