package cromega.studio.globalmobilityapextest.models

/**
 * Data class to contain the complete result of the [GiphyApiInterface] endpoints.
 *
 * @property data [List]<[GifInfo]> that represent the selected GIFs returned by the Giphy API
 * @property pagination [PaginationInfo] that contains basic information about the amount of [data]/GIFs
 * returned
 */
data class GifFull(
    val data: List<GifInfo>,
    val pagination: PaginationInfo
)
