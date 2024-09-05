package cromega.studio.globalmobilityapextest.models

/**
 * Data class that contains the different variants of a GIF.
 *
 * @property original [Image] instance that contains the information of the "original" GIF
 * @property downsized [Image] instance that contains the information of a "downsized" version
 * of the GIF
 * @property preview_gif [Image] instance that contains the information of a "preview" variant of
 * the GIF
 */
data class ImageOptions(
    val original: Image,
    val downsized: Image,
    val preview_gif: Image
)
