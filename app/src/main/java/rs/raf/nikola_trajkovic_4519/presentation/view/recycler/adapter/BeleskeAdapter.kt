package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.databinding.LayoutItemBeleskaBinding
import rs.raf.nikola_trajkovic_4519.databinding.LayoutItemMovieBinding
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.diff.BeleskeDiffCallback
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.diff.RasporedDiffCallback
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.viewholder.BeleskeViewHolder
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.viewholder.RasporedViewHolder
import java.util.function.Consumer

class BeleskeAdapter(

    onArchivedClicked: Consumer<Beleske> ,
    onDeleteClicked: Consumer<Beleske> ,
    onEditClicked: Consumer<Beleske>

): ListAdapter<Beleske, BeleskeViewHolder>(BeleskeDiffCallback()) {

    private val onArchiveClicked: Consumer<Beleske>? = onArchivedClicked
    private val onDeleteClicked: Consumer<Beleske>? = onDeleteClicked
    private val onEditClicked: Consumer<Beleske>? = onEditClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskeViewHolder {
        val itemBinding = LayoutItemBeleskaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeleskeViewHolder(itemBinding,
            Consumer { position: Int? ->
                onArchiveClicked?.accept(
                    getItem(
                        position!!
                    )
                )
            },
            Consumer { position2: Int? ->
                onDeleteClicked?.accept(
                    getItem(
                        position2!!
                    )
                )
            },
            Consumer { position3: Int? ->
                onEditClicked?.accept(
                    getItem(
                        position3!!
                    )
                )
            })
    }

    override fun onBindViewHolder(holder: BeleskeViewHolder, position: Int) {

        holder.bind(getItem(position))

    }


}