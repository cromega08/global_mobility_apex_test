package cromega.studio.globalmobilityapextest.models

/**
 * Data class that contains some file information of the GIF.
 *
 * @property width [String] that contains the width of the GIF
 * @property height [String] that contains the height of the GIF
 * @property size [String] that contains the file size of the GIF
 * @property url [String] that contains the URL to request the current GIF
 */
data class Image(
    val width: String,
    val height: String,
    val size: String,
    val url: String
)
