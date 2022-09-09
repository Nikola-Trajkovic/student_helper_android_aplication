package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.viewholder

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.databinding.LayoutItemMovieBinding
import java.util.function.Consumer

class RasporedViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(raspored: Raspored) {
        itemBinding.predmetTv.text = raspored.predmet
        itemBinding.danTv.text = raspored.dan
        itemBinding.grupeTv.text = raspored.grupe
        itemBinding.tipTv.text = raspored.tip
        itemBinding.ucionicaTv.text = raspored.ucionica
        itemBinding.profesorTv.text = raspored.nastavnik
        itemBinding.terminTv.text = raspored.termin
    }

}