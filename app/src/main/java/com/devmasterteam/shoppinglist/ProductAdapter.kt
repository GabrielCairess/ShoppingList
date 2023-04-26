package com.devmasterteam.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class ProductAdapter(context: Context) : ArrayAdapter<Product>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v:View

        if(convertView != null){
            v = convertView
        }else{
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }


        val item = getItem(position)

        val txt_produto = v.findViewById<TextView>(R.id.txt_item_name)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_quantity)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_value)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        txt_qtd.text = item?.quantity.toString()
        txt_produto.text = item?.name.toString()

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        txt_valor.text = f.format(item?.value)

        if (item?.picture != null){
            img_produto.setImageBitmap(item?.picture)
        }

        return v
    }
}