package com.example.readit.ui.theme.screens.bookmanager

import android.content.Context
import android.net.Uri
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.readit.R
import com.example.readit.data.BookViewModel
import com.example.readit.models.Book
import com.example.readit.navigation.ADD_BOOK
import com.example.readit.navigation.ROUT_ABOUT
import com.example.readit.navigation.ROUT_HOME
import com.google.android.mediahome.books.BookItem
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ViewBookScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        val context = LocalContext.current
        val bookRepository = BookViewModel(navController, context)


        val emptyBookState = remember { mutableStateOf(Book()) }
        val emptyBooksListState = remember { mutableStateListOf<Book>() }

        val tasks = bookRepository.allBooks(emptyBookState, emptyBooksListState)

        Column (
            modifier = Modifier.padding(horizontal = 12.dp)
        ){

            Spacer(modifier = Modifier.height(30.dp))
            // home icon
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate(ROUT_HOME) },
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Home",
                    modifier = Modifier.clickable { navController.navigate(ROUT_HOME) },
                    fontWeight = FontWeight.SemiBold
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.navigate(ROUT_ABOUT)
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Help",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            navController.navigate(ROUT_ABOUT)
                        }
                    )
                }
            }
            //end of home icon
            //intro row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                //card holding icon
                Card (
                    modifier = Modifier
                        .size(70.dp),
                    shape = RoundedCornerShape(50)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.bookicon),
                        contentDescription = "top icon",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    )
                }
                //end of card holding icon

            }
            Text(
                text = "View Books",
                fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            // intro row end
            Spacer(modifier = Modifier.height(5.dp))

            Row (
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "View Books",
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = { navController.navigate(ADD_BOOK) },
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                ) {
                    Text(
                        text = "Add Book",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            val currentUser = FirebaseAuth.getInstance().currentUser
            val currentUserId = currentUser?.uid

            LazyColumn(){
                items(tasks){
                    if (it.userId == currentUserId){
                        BookItem(
                            name = it.name,
                            author = it.author,
                            title = it.title,
                            description = it.description,
                            userId = it.userId,
                            navController = navController,
                            bookRepository = bookRepository
                        )
                    }

                }
            }

        }
    }
}

@Composable
fun BookItem(
    name:String, author:String, title:String, description:String, userId:String,
    navController: NavController,
    bookRepository: BookViewModel
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 25.dp)
    ){
        Card (
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                Color.Gray
            )
        ){
            Column (
                modifier = Modifier.padding(8.dp)
            ){
                Text(
                    text = "Title : $name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Description : ->$description",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                //button row
                Row (
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    OutlinedButton(
                        onClick = {
                            bookRepository.updateBook(title)
                        },
                        shape = RoundedCornerShape(8.dp),

                        ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "update"
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Update"
                            )
                        }
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(
                            onClick = {
                                bookRepository.deleteBook(title)
                            },
                            colors = ButtonDefaults.buttonColors(
                                Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Delete",
                                    color = Color.White
                                )
                            }
                        }
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(
                            onClick = {
                                ROUT_HOME
                            },
                            colors = ButtonDefaults.buttonColors(
                                Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row {
                                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "read")
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Read",
                                    color = Color.White
                                )
                            }
                        }
                    }
                    fun HomeScreen(context: Context, uri: Uri): ByteArray? {
                        return try {
                            val inputStream = context.contentResolver.openInputStream(uri)
                            inputStream?.readBytes()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            null
                        }
                    }



                }
                //end of button row

            }
        }

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ViewBookScreenPreview(){
    ViewBookScreen(navController = rememberNavController())
}