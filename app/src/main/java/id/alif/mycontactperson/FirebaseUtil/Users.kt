package id.alif.mycontactperson.FirebaseUtil

data class Users(val namaDepan: String?, val namaBelakang: String?,
                 val noTelp: String?, val email:String?){
    constructor():this("","","","")
}