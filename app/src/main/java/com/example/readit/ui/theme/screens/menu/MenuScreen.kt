package com.example.readit.ui.theme.screens.menu



import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.readit.R
import com.example.readit.navigation.ADD_BOOK
import com.example.readit.navigation.ADD_PRODUCTS_URL
import com.example.readit.navigation.ROUT_HOME
import com.example.readit.navigation.VIEW_BOOKS
import com.example.readit.navigation.VIEW_PRODUCTS_URL
import com.example.readit.ui.theme.Brown
import com.example.readit.ui.theme.Lavender
import com.example.readit.ui.theme.PurpleGrey80
import com.example.readit.ui.theme.screens.home.HomeScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController:NavController){


    //Navigation Drawer

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val menuList = listOf(
        Icons.Filled.Home to "Home" ,
        Icons.Filled.Favorite to "Contacts" ,
        Icons.Filled.Info to "History",
        Icons.Filled.Settings to "Settings",
        Icons.Filled.AccountCircle to "Profile"
    )

    var selectedItem by remember { mutableStateOf(-1) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                menuList.forEachIndexed { index, pair ->
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(imageVector = pair.first, contentDescription = pair.second)
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = pair.second)
                            }
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index })
                }
            }
        }) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TopAppBar(
                title = {
                    Text(
                        text = "ReadiT",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(Brown),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )

                    }
                },

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )

                    }
                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )

                    }
                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )

                    }


                }
            )
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                //Start of card
                Card(modifier = Modifier
                    .clickable {  }
                    .fillMaxWidth()
                    .height(650.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    //Row 1
                    Row (modifier = Modifier.padding(20.dp)){
                        //Card1
                        Card(modifier = Modifier
                            .width(160.dp)
                            .height(165.dp)
                            .clickable { navController.navigate(ROUT_HOME) },
                            elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.homeb),
                                        contentDescription ="home" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Home",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card1 ends
                        Spacer(modifier = Modifier.width(20.dp))


                        //Card2 starts
                        Card(modifier = Modifier
                            .width(160.dp)
                            .height(165.dp)
                            .clickable { navController.navigate(ROUT_HOME) }, elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.bookicon),
                                        contentDescription ="home" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Books",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card2 ends
                    }

                    //End of Row 1
                    //Row 2
                    Row (modifier = Modifier.padding(20.dp)){
                        //Card1
                        Card(modifier = Modifier
                            .width(160.dp)
                            .height(165.dp), elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth().clickable { navController.navigate( ADD_BOOK) },
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.add),
                                        contentDescription ="book" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Books",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card1 ends
                        Spacer(modifier = Modifier.width(20.dp))


                        //Card2 starts
                        Card(modifier = Modifier
                            .width(160.dp)
                            .height(165.dp), elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth().clickable { navController.navigate(VIEW_BOOKS ) },
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.view),
                                        contentDescription ="view" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "View Books",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card2 ends
                    }

                    //End of Row 2
                    //Row 3
                    Row (modifier = Modifier.padding(20.dp)){
                        //Card1
                        Card(modifier = Modifier.clickable { navController.navigate(ADD_PRODUCTS_URL ) }
                            .width(160.dp)
                            .height(165.dp), elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.add),
                                        contentDescription ="Book Image" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Add Book",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card1 ends
                        Spacer(modifier = Modifier.width(20.dp))


                        //Card2 starts
                        Card(modifier = Modifier.clickable { navController.navigate(VIEW_PRODUCTS_URL ) }
                            .width(160.dp)
                            .height(165.dp), elevation = CardDefaults.cardElevation(27.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(10.dp))

                                Box (modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){
                                    Image(painter = painterResource(id = R.drawable.view),
                                        contentDescription ="bookimage" ,
                                        modifier = Modifier
                                            .size(80.dp))

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Book Image",
                                    fontSize = 24.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center)

                            }

                        }
                        //Card2 ends
                    }

                    //End of Row 3



                }
                //End of card
            }

        }
    }


}





@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Other")
    }
}


@Composable
@Preview(showBackground = true)
fun MenuScreenPreview(){

    MenuScreen(navController = rememberNavController())
    
}