package livrokotlin.com.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class CadastroProdutoActivity : AppCompatActivity() {
    private var etProduto: EditText? = null
    private var etQtd: EditText? = null
    private var etPreco: EditText? = null
    private var btCadastrar: Button? = null
    private var ivFoto: ImageView? = null
    private var imageBipmap: Bitmap? = null

    val COD_IMAGE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        //Recupera os ID
        etProduto = findViewById(R.id.etProduto)
        etQtd = findViewById(R.id.etQuantidade)
        etPreco = findViewById(R.id.etPreco)
        btCadastrar = findViewById(R.id.btAdd)
        ivFoto = findViewById(R.id.ivFoto)

        //Prepara os listeners
        btCadastrar?.setOnClickListener { adicionar() }
        ivFoto?.setOnClickListener { selecionarImagem() }
    }

    fun adicionar(): Unit {
        val titulo = etProduto?.text.toString().trim()
        val quantidade: Int = etQtd?.text.toString().toInt()
        val preco: Double = etPreco?.text.toString().toDouble()

        if(quantidade <= 0){
            etQtd?.setError("O produto não pode ser vazio.")
            return
        }

        if(preco <= 0){
            etPreco?.setError("O preço não pode ser vazio.")
            return
        }

        if (titulo.isEmpty()) {
            etProduto?.setError("O produto não pode ser vazio.")
            return
        }

        //Adiciona na lista de produto
        listaProdutos.add(Produto(titulo,preco, quantidade,imageBipmap))

        //Limpa os campos
        etPreco?.text?.clear()
        etQtd?.text?.clear()
        etProduto?.text?.clear()
        imageBipmap = null

        Toast.makeText(this,"Produto adicionado com sucesso",Toast.LENGTH_SHORT).show()
    }

    fun selecionarImagem(): Unit{
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent,"Selecionar Imagem"),COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK && data != null){
            val inputStream = data.data?.let { contentResolver.openInputStream(it) }
            imageBipmap = BitmapFactory.decodeStream(inputStream)
            ivFoto?.setImageBitmap(imageBipmap)
        }

    }
}