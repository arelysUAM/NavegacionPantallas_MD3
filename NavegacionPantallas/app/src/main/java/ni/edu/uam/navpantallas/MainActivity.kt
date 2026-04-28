package ni.edu.uam.navpantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ni.edu.uam.navpantallas.ui.theme.NavPantallasTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NavPantallasTheme {
                AppNavigation()
            }
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var fotoPerfilUri by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var interesesSeleccionados by rememberSaveable {
        mutableStateOf(listOf<String>())
    }

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            PantallaInicio(
                onIrPerfil = {
                    navController.navigate("perfil")
                }
            )
        }

        composable("perfil") {
            Perfil(
                fotoPerfilUri = fotoPerfilUri,
                onFotoPerfilChange = { nuevaUri ->
                    fotoPerfilUri = nuevaUri
                },
                interesesSeleccionados = interesesSeleccionados,
                onInteresesChange = { nuevaLista ->
                    interesesSeleccionados = nuevaLista
                },
                onIrDetalle = {
                    navController.navigate("detalle")
                },
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

        composable("detalle") {
            DetalleInfo(
                interesesSeleccionados = interesesSeleccionados,
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }
    }
}