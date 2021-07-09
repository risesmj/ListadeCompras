package livrokotlin.com.listadecompras

import android.graphics.Bitmap

data class Produto(val titulo: String,
                   val preco: Double,
                   val quantidade: Int,
                   val foto: Bitmap? = null)