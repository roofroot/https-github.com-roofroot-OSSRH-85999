package cn.open.widget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.open.widget.ui.theme.SCREEN_DENSITY
import cn.open.widget.ui.theme.SCREEN_W
import cn.open.widget.ui.theme.WidgetTheme
import cn.open.widget_lib.ColorPalette

class MainActivity : ComponentActivity() {
//    var palette=ColorPalette(Color(0,34,0,225),Color(34,220,75,225),screenW = SCREEN_W/2, density = SCREEN_DENSITY)
var palette=ColorPalette(Color.Blue,Color.Cyan,screenW = SCREEN_W/2, density = SCREEN_DENSITY)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        palette.colorArr= arrayOf(0,0,0,255)
//        palette.colorArrBg= arrayOf(33,127,255,255)
        setContent {
            WidgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android",palette)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String,palette:ColorPalette) {

    Column(Modifier.fillMaxWidth()) {
        var fgColor: MutableState<Color> = remember {
            mutableStateOf(palette.fgColor)
        }
        var bgColor: MutableState<Color> = remember {
            mutableStateOf(palette.bgColor)
        }

        Text(text = (fgColor.value.blue*255).toString(),
            Modifier
                .background(bgColor.value)
                .height(50.dp), color = fgColor.value)
        Text(text = fgColor.toString(),
            Modifier
                .background(bgColor.value)
                .height(50.dp), color = fgColor.value)

        palette.PaletteWidget( fgColor =fgColor, bgColor = bgColor)
    }


}
