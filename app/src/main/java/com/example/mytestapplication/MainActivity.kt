package com.example.mytestapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.mytestapplication.navigation.NavGraph
import com.example.mytestapplication.screen.common.crossInlineFunc
import com.example.mytestapplication.screen.common.inlineFunc
import com.example.mytestapplication.screen.common.noInlineFunc
import com.example.mytestapplication.ui.theme.MyTestApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestApplicationTheme {
                inlineFunc { Log.d("MyLog", "inlined block") }
                noInlineFunc { Log.d("MyLog", "noInlined block")  }
                crossInlineFunc {
                    Log.d("MyLog", "crossInlined block")
                    return@crossInlineFunc
                    //return неявный возврат вызовет ошибку компиляции
                }
                Surface {
                    NavGraph(rememberNavController())
                }
            }
        }
    }
}



