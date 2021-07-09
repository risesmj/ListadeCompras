package livrokotlin.com.listadecompras.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import livrokotlin.com.listadecompras.Produto
import livrokotlin.com.listadecompras.R

class ListaAdapter(var dataSet: List<Produto>): RecyclerView.Adapter<ListaAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val qtd: TextView = view.findViewById(R.id.tvQtd)
        val preco: TextView = view.findViewById(R.id.tvPreco)
        val text: TextView = view.findViewById(R.id.tvTitulo)
        val image: ImageView = view.findViewById(R.id.ivIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista_compras, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) : Unit {
        val preco = dataSet[position].preco.toString()
        val qtd = dataSet[position].quantidade.toString()

        holder.text.setText(dataSet[position].titulo)
        holder.preco.setText("R$ $preco")
        holder.qtd.setText("$qtd Qtd.")
        holder.image.setImageBitmap(dataSet[position].foto)
    }
    override fun getItemCount() = dataSet.size


}