package id.alif.mycontactperson.adapter

import android.annotation.SuppressLint
import android.util.Log
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.OnItemLongClickListener
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.alif.mycontactperson.FirebaseUtil.Users
import kotlinx.android.synthetic.main.contact_list.*
import id.alif.mycontactperson.R


class ContactAdapter(val item: Users)
    : Item(){

    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.nama.text = item.namaDepan + " " + item.namaBelakang
        viewHolder.emailnya.text = item.email
        viewHolder.noTelpnya.text = item.noTelp

        Log.d("TAG2", item.namaBelakang)
    }

    override fun getLayout() = R.layout.contact_list
}