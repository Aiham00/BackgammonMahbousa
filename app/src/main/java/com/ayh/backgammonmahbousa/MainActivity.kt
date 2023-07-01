package com.ayh.backgammonmahbousa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ayh.backgammonmahbousa.ui.theme.BackgammonMahbousaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackgammonMahbousaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BuildBackGammonBoard("Android")
                }
            }
        }
    }
}

@Composable
fun BuildBackGammonBoard(name: String) {
    BoxWithConstraints{
        val brickSize = minWidth/16
        val constraints = decoupledConstraints()
        ConstraintLayout(constraints){

            Image(
                painter = painterResource(R.drawable.board),
                contentDescription = "Board",
                contentScale = ContentScale.FillBounds,
                modifier =Modifier.layoutId("background")


                /* Set image size to 40 dp
                .size(40.dp)
                 Clip image to be shaped as a circle
                .clip(CircleShape)*/
            )
            val matrix = createBoardMatrix()
            buildUpperSide(matrix,brickSize)
        }
    }


}

@Composable
fun buildUpperSide(matrix: ArrayList<ArrayList<Int>>,brickSize: Dp) {
    Row(modifier =Modifier.layoutId("upper_row")
        ,horizontalArrangement = Arrangement.Center ){
        for (i in 0..11){
            buildOneColumn(matrix[i],brickSize)
        }
    }
    Row(modifier =Modifier.layoutId("lower_row"),
        verticalAlignment = Alignment.Bottom){
        for (i in 12..23){
            buildOneColumn(matrix[i],brickSize)
        }
    }
}

@Composable
fun buildOneColumn(list: ArrayList<Int>, brickSize: Dp) {
    Column(){
        for(item in list){
            /*Icon(
                Icons.Rounded.AddCircle,
                contentDescription = "Circle",
                Modifier.size(brickSize)
            )*/


                    Badge(
                        modifier = Modifier.size(brickSize)
                            .clip(CircleShape)
                    ){
                        val badgeNumber = item.toString()
                        Text(
                            badgeNumber,
                            modifier = Modifier.semantics {
                                contentDescription = "$badgeNumber new notifications"
                            }
                        )
                    }

        }
    }
}
private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val background = createRefFor("background")
        val upperRow = createRefFor("upper_row")
        val lowerRow = createRefFor("lower_row")

        constrain(background) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(upperRow) {
            top.linkTo(parent.top,margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(lowerRow) {
            bottom.linkTo(parent.bottom,margin = 38.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}
private fun createBoardMatrix():ArrayList<ArrayList<Int>>{
    return arrayListOf<ArrayList<Int>>(
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(0,1),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0),
        arrayListOf(1,0)
    )

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BackgammonMahbousaTheme {
        BuildBackGammonBoard("Android")
    }
}