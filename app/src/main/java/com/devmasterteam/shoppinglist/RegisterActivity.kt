package com.devmasterteam.shoppinglist

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.devmasterteam.shoppinglist.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    val CODE_IMAGE = 101
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsert.setOnClickListener(this)
        binding.imgFotoProduct.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.btn_insert -> insertProduct()
            R.id.img_foto_product -> openGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val	inputStream	= data.data?.let { contentResolver.openInputStream(it) }
                imageBitMap = BitmapFactory.decodeStream(inputStream)
                binding.imgFotoProduct.setImageBitmap(imageBitMap)
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), CODE_IMAGE)
    }

    private fun insertProduct() {
        val product = binding.txtName.text.toString()
        val qtd = binding.txtQuantity.text.toString()
        val value = binding.txtValue.text.toString()

        if (product.isNotEmpty() && qtd.isNotEmpty() && value.isNotEmpty()) {
            val prod = Product(product, qtd.toInt(), value.toDouble(), imageBitMap)
            productsGlobal.add(prod)

            binding.txtName.text.clear()
            binding.txtQuantity.text.clear()
            binding.txtValue.text.clear()
            binding.imgFotoProduct.setImageResource(android.R.drawable.ic_menu_camera)
        } else
        {
            binding.txtName.error = if (binding.txtName.text.isNullOrEmpty()) "Prrencha o nome do produto" else null
            binding.txtQuantity.error = if (binding.txtQuantity.text.isNullOrEmpty()) "Prrencha a quantidade" else null
            binding.txtValue.error = if (binding.txtValue.text.isNullOrEmpty()) "Prrencha o valor" else null
        }
    }
}