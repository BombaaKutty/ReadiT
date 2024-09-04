package com.example.readit.data


import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.readit.models.Book
import com.example.readit.navigation.ROUT_LOGIN
import com.example.readit.navigation.VIEW_BOOKS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookViewModel(var navController: NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(ROUT_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadBook(
        name: String,
        author: String,
        title: String,
        description: String,
    ){
        val bookId = System.currentTimeMillis().toString()
        progress.show()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        val task = Book(name,author,title,description,userId?:"")
        val databaseRef = FirebaseDatabase.getInstance().getReference()
            .child("Books/$bookId")
        databaseRef.setValue(task).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                navController.navigate(VIEW_BOOKS)
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun deleteBook(bookId:String){
        val ref = FirebaseDatabase.getInstance().getReference()
            .child("Books/$bookId")
        ref.removeValue()
        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
    }

    fun updateBook(bookId:String){
        val ref = FirebaseDatabase.getInstance().getReference()
            .child("Books/$bookId")
        ref.removeValue()
        navController.navigate(VIEW_BOOKS)
    }

    fun allBooks(
        book: MutableState<Book>,
        books: SnapshotStateList<Book>): SnapshotStateList<Book> {
        progress.show()

        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Books")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                books.clear()
                for (snap in snapshot.children){
                    val retrievedTask = snap.getValue(Book::class.java)
                    book.value = retrievedTask!!
                    books.add(retrievedTask)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }

}