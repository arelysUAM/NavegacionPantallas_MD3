package ni.edu.uam.navpantallas

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private suspend fun cargarImagenBitmap(
    contexto: Context,
    uriString: String
): ImageBitmap? = withContext(Dispatchers.IO) {
    runCatching {
        contexto.contentResolver.openInputStream(uriString.toUri()).use { stream ->
            BitmapFactory.decodeStream(stream)?.asImageBitmap()
        }
    }.getOrNull()
}

@Composable
fun Perfil(
    fotoPerfilUri: String?,
    onFotoPerfilChange: (String?) -> Unit,
    interesesSeleccionados: List<String>,
    onInteresesChange: (List<String>) -> Unit,
    onIrDetalle: () -> Unit,
    onRegresar: () -> Unit
) {
    val selectorFotoPerfil = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            onFotoPerfilChange(uri.toString())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF1F7))
    ) {
        BarraTitulo(
            titulo = "Pantalla de Perfil"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    SelectorFotoPerfil(
                        imagenUri = fotoPerfilUri,
                        onSeleccionarImagen = {
                            selectorFotoPerfil.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    )

                    Text(
                        text = "Arelys Castillo",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF880E4F),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "@arelys123",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFAD1457),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Información",
                            tint = Color(0xFFE91E63)
                        )

                        Text(
                            text = "Soy una persona creativa, curiosa y tranquila. Me gusta aprender cosas nuevas, crear proyectos, jugar videojuegos y compartir intereses con mis amigos.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF5D4037),
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Text(
                        text = "Intereses",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF880E4F),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ChipInteres(
                                texto = "Videojuegos",
                                seleccionado = interesesSeleccionados.contains("Videojuegos"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Videojuegos"
                                        )
                                    )
                                }
                            )

                            ChipInteres(
                                texto = "Música",
                                seleccionado = interesesSeleccionados.contains("Música"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Música"
                                        )
                                    )
                                }
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ChipInteres(
                                texto = "Arte",
                                seleccionado = interesesSeleccionados.contains("Arte"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Arte"
                                        )
                                    )
                                }
                            )

                            ChipInteres(
                                texto = "Programación",
                                seleccionado = interesesSeleccionados.contains("Programación"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Programación"
                                        )
                                    )
                                }
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ChipInteres(
                                texto = "Lectura",
                                seleccionado = interesesSeleccionados.contains("Lectura"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Lectura"
                                        )
                                    )
                                }
                            )

                            ChipInteres(
                                texto = "Películas",
                                seleccionado = interesesSeleccionados.contains("Películas"),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onInteresesChange(
                                        cambiarSeleccion(
                                            interesesSeleccionados,
                                            "Películas"
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onIrDetalle,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Ir a detalle"
                )

                Text(
                    text = "Ver más detalles",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

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
fun SelectorFotoPerfil(
    imagenUri: String?,
    onSeleccionarImagen: () -> Unit
) {
    val contexto = LocalContext.current

    val imagenCargada by produceState<ImageBitmap?>(
        initialValue = null,
        key1 = imagenUri
    ) {
        value = if (imagenUri.isNullOrBlank()) {
            null
        } else {
            cargarImagenBitmap(contexto, imagenUri)
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        if (imagenCargada == null) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFE4EF))
                    .border(
                        width = 3.dp,
                        color = Color(0xFFE91E63),
                        shape = CircleShape
                    )
                    .clickable(onClick = onSeleccionarImagen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Foto de perfil por defecto",
                    modifier = Modifier.size(72.dp),
                    tint = Color(0xFFE91E63)
                )
            }
        } else {
            Image(
                bitmap = imagenCargada!!,
                contentDescription = "Foto de perfil seleccionada",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(
                        width = 3.dp,
                        color = Color(0xFFE91E63),
                        shape = CircleShape
                    )
                    .clickable(onClick = onSeleccionarImagen),
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = onSeleccionarImagen,
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.PhotoCamera,
                contentDescription = "Seleccionar foto de perfil",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ChipInteres(
    texto: String,
    seleccionado: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FilterChip(
        selected = seleccionado,
        onClick = onClick,
        modifier = modifier,
        label = {
            Text(texto)
        },
        leadingIcon = {
            Icon(
                imageVector = if (seleccionado) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.Star
                },
                contentDescription = null
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFFE91E63),
            selectedLabelColor = Color.White,
            selectedLeadingIconColor = Color.White,
            containerColor = Color(0xFFFFF1F7),
            labelColor = Color(0xFF880E4F),
            iconColor = Color(0xFFE91E63)
        )
    )
}

fun cambiarSeleccion(
    listaActual: List<String>,
    interes: String
): List<String> {
    return if (listaActual.contains(interes)) {
        listaActual - interes
    } else {
        listaActual + interes
    }
}