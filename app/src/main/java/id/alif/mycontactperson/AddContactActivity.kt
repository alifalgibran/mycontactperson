package id.alif.mycontactperson

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import id.alif.mycontactperson.FirebaseUtil.FirestoreUtil
import kotlinx.android.synthetic.main.activity_add_contact.*
import org.jetbrains.anko.*

class AddContactActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)


        simpan.setOnClickListener {
            progressBar = ProgressDialog(this@AddContactActivity)
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressBar.setTitle("Sedang Mengupload...")
            progressBar.progress = 0
            progressBar.show()
            progressBar.max = 100
           FirestoreUtil.initCurrentUser(nama_depan.text.toString() , nama_belakang.text.toString(), noTelp.text.toString()
                              ,email.text.toString()){
               if(it == "Sukses") {
                   toast("Tersimpan")
                   progressBar.dismiss()
                   startActivity(intentFor<MainActivity>().newTask().clearTask())
               }else{
                   toast("No Telpon Telah Tersedia Dikontak Lain")
               }
           }
        }
    }
}
