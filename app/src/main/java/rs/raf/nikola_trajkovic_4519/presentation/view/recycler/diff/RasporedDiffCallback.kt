package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.nikola_trajkovic_4519.data.models.Raspored

class RasporedDiffCallback : DiffUtil.ItemCallback<Raspored>() {

    override fun areItemsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.predmet == newItem.predmet
    }

}