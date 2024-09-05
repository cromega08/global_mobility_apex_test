package cromega.studio.globalmobilityapextest.models

data class GifInfo(
    val id: String,
    val username: String,
    val title: String,
    val embed_url: String,
    val import_datetime: String,
    val trending_datetime: String,
    val images: ImageOptions
)
