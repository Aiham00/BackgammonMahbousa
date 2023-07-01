package com.ayh.backgammonmahbousa

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ayh.backgammonmahbousa.ui.theme.BackgammonMahbousaTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

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
        var brickSize = minWidth/16
        ConstraintLayout{
            val (background,upperRow,lowerRow, text) = createRefs()

            Image(
                painter = painterResource(R.drawable.board),
                contentDescription = "Board",
                contentScale = ContentScale.FillBounds,
                modifier =Modifier.constrainAs(background) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }

                /* Set image size to 40 dp
                .size(40.dp)
                 Clip image to be shaped as a circle
                .clip(CircleShape)*/
            )
            val matrix = createBoardMatrix()
            Row(modifier =Modifier.constrainAs(upperRow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                ,horizontalArrangement = Arrangement.Center ){
                for (i in 0..11){
                    buildOneColumn(matrix[i],brickSize)
                }
            }
            Row(modifier =Modifier.constrainAs(lowerRow) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            },
                verticalAlignment = Alignment.Bottom){
                for (i in 12..23){
                    buildOneColumn(matrix[i],brickSize)
                }
            }
        }
    }


}

/*@Composable
fun buildUpperSide(matrix: ArrayList<ArrayList<Int>>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally){

        Row(horizontalArrangement = Arrangement.Center ){
            for (i in 0..11){
                buildOneColumn(matrix[i])
            }
        }
        Row(verticalAlignment = Alignment.Bottom){
            for (i in 12..23){
                buildOneColumn(matrix[i])
            }
        }
    }
}*/

@Composable
fun buildOneColumn(list: ArrayList<Int>,size: Dp) {
    Column(){
        for(item in list){
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Favorite",
                Modifier.size(size)
            )
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