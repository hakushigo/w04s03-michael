package com.example.compostingadv

import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.compostingadv.ui.theme.CompostingadvTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompostingadvTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Button(onClick = {
                            NotifyMe(
                                "Hello This is A notification",
                                "Just saying",
                                NotificationManager.IMPORTANCE_HIGH)
                        },
                            modifier = Modifier.padding(10.dp)) {
                            Text("Notify Myself")
                        }

                        ContactCards()
                        RowAlignment()
                        ColumnAlignment()
                        Column(
                            modifier = Modifier.height(250.dp)
                        ) {
                            BoxAlignment()
                        }
                        com.example.compostingadv.Arrangement()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun NotifyMe(title: String, body: String, priority: Int){

        // build the notification
        val builder = NotificationCompat.Builder(this, getString(R.string.not_chanid_id))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(priority)
            .build()

        val notman : NotificationManager =  getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_DENIED){
            notman.notify(19998, builder)
        }else{
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }
}

/// customized
@Preview
@Composable
fun ContactCards()
{
    Row (
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "Face of someone",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "This guy is online",
                tint = Color.Green,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text (
                text = "Michael DeGaulle",
                fontWeight = FontWeight.Bold
            )
            Text("Online")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    CompostingadvTheme {
        ContactCards()
    }
}

// followed as it is
@Composable
@Preview(showBackground = true)
fun RowAlignment(){
    Column(modifier = Modifier.padding(16.dp)){
        Row (
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Top",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ButtonA()
            ButtonB()
            ButtonC()
        }
        Spacing()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Top",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ButtonA()
            ButtonB()
            ButtonC()
        }
        Spacing()
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Top",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ButtonA()
            ButtonB()
            ButtonC()
        }
        Spacing()
    }
}


/**
 * Box Alignment
 */
@Composable
fun ColumnAlignment(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column(horizontalAlignment = Alignment.Start){
            Text("Start")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text("CenterHorizontally")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
        Column(horizontalAlignment = Alignment.End){
            Text("End")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
    }
}

@Composable
fun BoxAlignment()
{
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()){
        ButtonWithText(Text = "TopStart", modifier = Modifier.align(Alignment.TopStart))
        ButtonWithText(Text = "TopCenter", modifier = Modifier.align(Alignment.TopCenter))
        ButtonWithText(Text = "TopEnd", modifier = Modifier.align(Alignment.TopEnd))
        ButtonWithText(Text = "CenterStart", modifier = Modifier.align(Alignment.CenterStart))
        ButtonWithText(Text = "Center", modifier = Modifier.align(Alignment.Center))
        ButtonWithText(Text = "CenterEnd", modifier = Modifier.align(Alignment.CenterEnd))
        ButtonWithText(Text = "BottomStart", modifier = Modifier.align(Alignment.BottomStart))
        ButtonWithText(Text = "BottomCenter", modifier = Modifier.align(Alignment.BottomCenter))
        ButtonWithText(Text = "BottomEnd", modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun ButtonWithText(Text: String, modifier: Modifier)
{
    Button(
        onClick = {},
        modifier = modifier
    ){
        Text(Text)
    }

}

@Composable
fun RowButtonMaxWidth(whatArrangment : Arrangement.Horizontal)
{
    Row(
        horizontalArrangement = whatArrangment,
        modifier = Modifier.fillMaxWidth()
    ) {
        for( x in 1..3){
            Button(onClick = {}, shape = RectangleShape, modifier = Modifier
                .size(75.dp)
                .padding(10.dp)) {
                
            }
        }
    }
}

@Composable
fun Arrangement()
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        RowButtonMaxWidth(Arrangement.Start)
        RowButtonMaxWidth(Arrangement.End)
        RowButtonMaxWidth(Arrangement.Center)
        RowButtonMaxWidth(Arrangement.SpaceEvenly)
        RowButtonMaxWidth(Arrangement.SpaceAround)
        RowButtonMaxWidth(Arrangement.SpaceBetween)
    }
}

@Composable
fun Spacing() {
    Spacer(modifier = Modifier.width(20.dp))
}

// followed as it is
/**
 * Buttons
 */
@Composable
fun ButtonA(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_200)),
        modifier = Modifier.size(100.dp)
        ){
        Text("A")
    }
}

@Composable
fun ButtonB(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
        modifier = Modifier.size(80.dp)
    ){
        Text("B")
    }
}

@Composable
fun ButtonC(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700)),
        modifier = Modifier.size(60.dp),
    ){
        Text("A")
    }
}