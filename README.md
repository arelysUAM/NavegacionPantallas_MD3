# NavPantallas

NavPantallas es una aplicación móvil simple desarrollada en Android Studio utilizando Kotlin y Jetpack Compose. Su objetivo principal es mostrar el uso básico de navegación entre pantallas mediante Navigation Compose, integrando una interfaz visual sencilla con una tarjeta de presentación personal.

## Descripción de la aplicación

La aplicación funciona como una pequeña tarjeta de presentación para conocer mejor a una persona. Permite navegar entre tres pantallas principales: Inicio, Perfil y Detalle de Información.

En la pantalla de Inicio se muestra un mensaje de bienvenida y un botón para acceder al perfil. La pantalla de Perfil presenta información personal como una foto, nombre, apodo, descripción e intereses. Además, permite seleccionar una imagen desde el dispositivo y marcar intereses visualmente. Finalmente, la pantalla de Detalle de Información muestra datos más específicos sobre los intereses seleccionados, incluyendo una breve descripción y un nivel de gusto del 1 al 5.

## Objetivo del proyecto

El objetivo de este proyecto es practicar la navegación entre pantallas en Jetpack Compose usando la librería `navigation-compose`. También se busca aplicar componentes visuales de Material Design 3, manejo básico de estados y selección de imágenes desde el dispositivo.

## Funcionalidades principales

- Navegación entre pantallas usando Navigation Compose.
- Pantalla de inicio con mensaje de bienvenida.
- Pantalla de perfil con tarjeta de presentación.
- Selección de imagen de perfil desde el dispositivo.
- Visualización de nombre, apodo y descripción personal.
- Selección de intereses mediante chips interactivos.
- Cambio visual de color en los intereses seleccionados.
- Pantalla de detalle con información ampliada sobre los intereses.
- Nivel de gusto representado del 1 al 5 con estrellas.
- Botones para avanzar y regresar entre pantallas.
- Diseño con una paleta de colores rosados y tarjetas blancas.

## Pantallas de la aplicación

### Pantalla de Inicio

La pantalla de Inicio presenta una bienvenida al usuario y explica brevemente el propósito de la aplicación. Desde esta pantalla se puede acceder al perfil mediante el botón "Ver perfil".

### Pantalla de Perfil

La pantalla de Perfil contiene una tarjeta personal con los siguientes elementos:

- Foto de perfil.
- Nombre de la persona.
- Apodo o usuario.
- Descripción breve.
- Lista de intereses seleccionables.
- Botón para ver más detalles.
- Botón para regresar a la pantalla de inicio.

Los intereses cambian a color rosado cuando son seleccionados, permitiendo identificar fácilmente cuáles fueron elegidos.

### Pantalla de Detalle de Información

La pantalla de Detalle de Información muestra los intereses que fueron seleccionados en la pantalla de Perfil. Por cada interés se presenta:

- Nombre del interés.
- Descripción detallada.
- Nivel de gusto del 1 al 5.
- Representación visual con estrellas.

Si no se selecciona ningún interés, la pantalla muestra un mensaje indicando que aún no hay intereses elegidos.

## Navegación de la aplicación

El flujo de navegación de la aplicación es el siguiente:

```text
Pantalla de Inicio → Pantalla de Perfil → Pantalla de Detalle de Información
