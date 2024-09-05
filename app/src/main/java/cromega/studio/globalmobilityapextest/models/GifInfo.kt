package cromega.studio.globalmobilityapextest.models

/**
 * Data class that represents an individual GIF data.
 *
 * @property id [String] that represent the record identification of the GIF in Giphy services
 * @property username [String] that represent the creator/owner of the GIF
 * @property title [String] that represent the name of the GIF
 * @property embed_url [String] that indicate an endpoint to obtain HTML
 * to be embedded to render the GIF
 * @property import_datetime [String] that indicate the upload time of the GIF in Giphy
 * @property trending_datetime [String] that indicate the time when the GIF become "trending"
 * @property images [ImageOptions] object that contains the different variants of the GIF
 */
data class GifInfo(
    val id: String,
    val username: String,
    val title: String,
    val embed_url: String,
    val import_datetime: String,
    val trending_datetime: String,
    val images: ImageOptions
)
