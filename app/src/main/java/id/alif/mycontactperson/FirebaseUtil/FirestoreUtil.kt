package id.alif.mycontactperson.FirebaseUtil

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.kotlinandroidextensions.Item
import id.alif.mycontactperson.adapter.ContactAdapter

object FirestoreUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val userRef = firestoreInstance.collection("users")

    fun initCurrentUser(namaDepan: String = "", namaBelakang: String = "",
                        noTelp: String = "", email:String = "", onComplete: (String) -> Unit){

        val userFieldMap = mutableMapOf<String, Any>()
        if(namaDepan.isNotBlank()) userFieldMap["namaDepan"] = namaDepan
        if(namaBelakang.isNotBlank()) userFieldMap["namaBelakang"] = namaBelakang
        if(noTelp.isNotBlank()) userFieldMap["noTelp"] = noTelp
        if(email.isNotBlank()) userFieldMap["email"] = email

        userRef.document(noTelp)
            .set(userFieldMap)
            .addOnSuccessListener {
                onComplete("Sukses")
            }
        }

    fun myContactListener(onListen: (List<Item>)->Unit): ListenerRegistration{

        return userRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("FIRESTORE", "User Listener Err   or  .", firebaseFirestoreException)
                return@addSnapshotListener
            }
            val items = mutableListOf<Item>()
            querySnapshot!!.documents.forEach {
                items.add(ContactAdapter(it.toObject(Users::class.java)!!))
            }
            //    querySnapshot!!.doc
            onListen(items)
        }

    }

}