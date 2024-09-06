
# Global Mobility Apex - Prueba

El presente proyecto es subido como "Prueba Tecnica" para la empresa "Global Mobility Apex", dentro de la postulacion para un puesto de "Android Developer".


## El Proyecto

El presente proyecto se creo para cumplir los siguientes requerimientos:

1. Configuración del Proyecto: Se genero un proyecto desde 0, con manejo y uso de dependencias.

2. Consumo de Servicio Web: Se implemento el consumo de servicios web desde la [API oficial de Giphy](https://developers.giphy.com/docs/api/#quick-start-guide) para obtener contenido multimedia de imagener GIF, a traves de las funcionalidades de la libreria [Retrofit](https://square.github.io/retrofit/).

3. Mapeo de Datos: Se realizo el mapeo de las respuestas mediante las funcionalidades de la libreria [Retrofit](https://square.github.io/retrofit/).

4. Interfaz de Usuario: Los datos obtenidos desde la [API oficial de Giphy](https://developers.giphy.com/docs/api/#quick-start-guide) son renderizados de manera adecuada, procurando la correcta ejecucion de los GIF.

5. Arquitectura: Pese al uso de una unica vista, se uso una arquitectura MVVM con los roles definidos para el manejo de los "State" y de la data involucrada en general.

6. Gestión de Estados: Mediante el uso de "States" y aprovechando las funcionalidades de Android, se realizo el manejo de los posibles escenarios presentes en la renderizacion de contenido, teniendo en cuenta los momentos de carga y los posibles errores que se puedan presentar.


## Tech Stack

El presente proyecto hace uso de las siguientes tecnologias:

- Lenguajes: [Kotlin](https://kotlinlang.org), [XML](https://developer.mozilla.org/es/docs/Web/XML/XML_introduction).

- Frameworks: [Android](https://developer.android.com), [Jetpack Compose](https://developer.android.com/compose).

- Dependencias: [Retrofit](https://square.github.io/retrofit/), [Coil](https://github.com/coil-kt/coil).

- Build Tools: [Gradle](https://gradle.org).

- IDE: [Android Studio](https://developer.android.com/studio).



## Instalacion

Para la instalacion de la aplicacion en un dispositivo movil, tan solo es necesario descargar el archivo "GlobalMobilityApex.apk" e instalarlo en el dispositivo movil con OS Android.

Para la instalacion, es posible que se deban habilitar ciertas configuraciones que varian de dispositivo en dispositvo, pero en general corresponde a "Permitir aplicaciones de origen desconocido".
## Correr Localmente

Para correr el presente proyecto localmente en necesario:

1. Clonar el presente proyecto en la maquina local.

2. Obtener una [API_KEY de Giphy](https://developers.giphy.com/docs/api/#quick-start-guide).

3. Abrir el proyecto en un IDE ideal para proyectos Android (se recomienda [Android Studio](https://developer.android.com/studio)) y esperar los procesos de compilacion normales del IDE.

4. Guardar la API_KEY en la variable "GiphyApiConstants.GIPHY_API_KEY".

- Nota: La decision de almacenar la API_KEY en codigo se realizo unicamente por acelerar el proceso de la prueba, ya que se entiende que en codigo real seria una falla grave de seguridad.
## Decisiones Esteticas

Debido al tiempo apretado, se decidio seguir los estandares basicos del [Material Design 3](https://m3.material.io), ya que estos vienen integrados de serie en [Jetpack Compose](https://developer.android.com/compose).
## Evidencias

Las imagenes de evidencias solicitadas de la aplicacion podran ser encontradas en este [enlace de Google Drive](https://drive.google.com/drive/folders/1JwlMh7u63QaYmd8zfGs9qVm9K7iDnGd_?usp=sharing).
## Authors

- [@Cromega08](https://www.github.com/Cromega08)

