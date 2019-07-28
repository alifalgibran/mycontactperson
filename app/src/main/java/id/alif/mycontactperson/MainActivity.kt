package id.alif.mycontactperson

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.alif.mycontactperson.FirebaseUtil.FirestoreUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var userListenerRegistration: ListenerRegistration
    private lateinit var peopleSection: Section
    private var shouldInitRecyclerView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListenerRegistration = FirestoreUtil.myContactListener(this::updateRecyclerView)

        fab_add.setOnClickListener {
            startActivity<AddContactActivity>()
        }

    }

    fun updateRecyclerView(items: List<Item>){
        fun init(){
            myRecycler.apply{
                adapter = GroupAdapter<ViewHolder>().apply{
                    peopleSection = Section(items)
                    add(peopleSection)
                }
            }
            shouldInitRecyclerView = false
        }
        fun updateItems() = peopleSection.update(items)
        if(shouldInitRecyclerView)
            init()
        else
            updateItems()

    }
}
