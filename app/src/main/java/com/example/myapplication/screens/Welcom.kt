import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.SignupViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun Welcome(signupViewModel: SignupViewModel= viewModel()) {
    Surface(
        color= Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(Color.White)
    ) {
        Box(modifier=Modifier.fillMaxSize()){
            Image(
                painter= painterResource(id = R.drawable.bg),
                contentDescription="",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Text(text=signupViewModel.registrationUIState.value.email,
                fontSize = 10.sp
                )
//            SystemBackButtonHandler {
//                AppRouter.navigateTo(Screen.HomeScreen)
//            }
        }
    }

}
@Preview
@Composable
fun WelcomePreview(){
    Welcome()
}