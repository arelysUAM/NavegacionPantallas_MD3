package ni.edu.uam.navpantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DetalleInfo(
    interesesSeleccionados: List<String>,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF1F7))
    ) {
        BarraTitulo(
            titulo = "Detalle de Información"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Información",
                            tint = Color(0xFFE91E63)
                        )

                        Text(
                            text = "Intereses seleccionados",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF880E4F)
                        )
                    }

                    Text(
                        text = "Aquí puedes ver información más detallada sobre los intereses elegidos en el perfil y el nivel de gusto de cada uno.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF5D4037),
                        textAlign = TextAlign.Justify
                    )

                    if (interesesSeleccionados.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFFFFF1F7),
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(18.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Aún no se han seleccionado intereses en el perfil.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF880E4F),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        interesesSeleccionados.forEach { interes ->
                            TarjetaDetalleInteres(
                                interes = interes,
                                descripcion = obtenerDescripcionInteres(interes),
                                nivel = obtenerNivelInteres(interes)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = onRegresar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Regresar"
                )

                Text(
                    text = "Regresar",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun TarjetaDetalleInteres(
    interes: String,
    descripcion: String,
    nivel: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFF1F7),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Interés",
                tint = Color(0xFFE91E63)
            )

            Text(
                text = interes,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF880E4F)
            )
        }

        Text(
            text = descripcion,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF5D4037),
            textAlign = TextAlign.Justify
        )

        Text(
            text = "Nivel de gusto: $nivel/5",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFAD1457)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 1..5) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Nivel $i",
                    tint = if (i <= nivel) Color(0xFFE91E63) else Color(0xFFD7B8C5),
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

fun obtenerDescripcionInteres(interes: String): String {
    return when (interes) {
        "Videojuegos" -> "Disfruta jugar videojuegos porque permiten relajarse, compartir con amigos y vivir experiencias interactivas."
        "Música" -> "La música es una forma de acompañar diferentes momentos del día, expresar emociones y concentrarse mejor."
        "Arte" -> "El arte permite desarrollar la creatividad, expresar ideas personales y representar emociones de una forma visual."
        "Programación" -> "La programación ayuda a crear soluciones, desarrollar aplicaciones y convertir ideas en proyectos funcionales."
        "Lectura" -> "La lectura permite aprender, imaginar nuevas historias y mejorar la comprensión de diferentes temas."
        "Películas" -> "Las películas son una forma de entretenimiento que permite conocer historias, personajes y mundos diferentes."
        else -> "Este interés forma parte de los gustos personales y ayuda a conocer mejor a la persona."
    }
}

fun obtenerNivelInteres(interes: String): Int {
    return when (interes) {
        "Videojuegos" -> 5
        "Música" -> 4
        "Arte" -> 4
        "Programación" -> 5
        "Lectura" -> 3
        "Películas" -> 4
        else -> 3
    }
}