import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wishlist.R
import com.example.wishlist.data.CardData
import com.example.wishlist.ui.theme.CardModel

@Composable
fun AddWishListItem(navController: NavController) {

    PhotoPickerScreen(navController)

    ExtendedFloatingActionButton(
        onClick = { navController.navigate("homescreen") },
        text = { Text("Go Back") },
        icon = { Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.app_name)) },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth() // Make the FAB span across the entire width
            .wrapContentHeight(align = Alignment.Bottom),
            containerColor = Color.LightGray,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoPickerScreen(navController: NavController) {

    val cardModel: CardModel = viewModel(factory = CardModel.Factory)

    val idNum = cardModel.cards.value.size + 1

    var itemName by remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var description by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    var photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
            println(selectedImageUri)
        }
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ){
            Text(
                text = "Add A New Item",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF639FD8)
                )
            )
            Spacer(modifier = Modifier.size(28.dp))

            Text(
                text = "Item Name",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF639FD8)
                )
            )
            TextField(
                value = itemName,
                onValueChange = {
                    itemName = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(12.dp), // Set the corner radius here
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.size(28.dp))

            Text(
                text = "Item Price",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF639FD8)
                )
            )
            TextField(
                value = price,
                onValueChange = {
                    price = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(12.dp), // Set the corner radius here
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )


            Spacer(modifier = Modifier.size(28.dp))
            Text(
                text = "Item Description",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF639FD8)
                )
            )
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.size(28.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF639FD8),  // Blue background color
                    contentColor = Color.White           // White text color
                ),
                onClick = {
                    photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
            ) {
                Text("Choose Photo from your Camera Roll")
            }

            Spacer(modifier = Modifier.size(28.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF639FD8),  // Blue background color
                    contentColor = Color.White           // White text color
                ),
                onClick = {
                    val newCard = CardData(
                        id = idNum,
                        title = itemName,
                        imagePath = selectedImageUri.toString(),
                        description = description,
                        price = price
                    )
                    cardModel.addCard(newCard)
                    navController.navigate("homescreen")
                    cardModel.repository.getCards()

                },
            ) {
                Text("Add Card")
            }
            Spacer(modifier = Modifier.size(28.dp))

        }
    }
}




