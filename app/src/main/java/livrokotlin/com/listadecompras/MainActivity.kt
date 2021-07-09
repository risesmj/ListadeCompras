package livrokotlin.com.listadecompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import livrokotlin.com.listadecompras.adapter.ListaAdapter


class MainActivity : AppCompatActivity() {
    private var etProduto: EditText? = null
    private var rvLista: RecyclerView? = null
    private var btCadastrar: Button? = null
    private var tvTotal: TextView? = null
    private var adapter: ListaAdapter = ListaAdapter(listaProdutos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Resgata os id de componente
        etProduto = findViewById(R.id.etProduto)
        btCadastrar = findViewById(R.id.btCadastrar)
        rvLista = findViewById(R.id.rvLista)
        tvTotal = findViewById(R.id.tvTotal)

        //Adiciona os listeners
        btCadastrar?.setOnClickListener { adicionar() }

        //prepara o recycle view
        val layoutManager = LinearLayoutManager(this)
        rvLista?.setLayoutManager(layoutManager)
        rvLista?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyItemInserted(listaProdutos.size)
        var total: Double = 0.0
        for (value in listaProdutos.iterator()) total += value.preco*value.quantidade
        tvTotal?.setText(total.toString())
    }

    fun adicionar(): Unit = startActivity(Intent(this,CadastroProdutoActivity::class.java))

}