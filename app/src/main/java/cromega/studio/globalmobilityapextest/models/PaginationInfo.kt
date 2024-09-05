package cromega.studio.globalmobilityapextest.models

/**
 * Data class that contains basic information about the amount of [data]/GIFs
 * returned by the Giphy endpoints.
 *
 * @property total_count [Int] value that represent the total amount of GIFs that can be
 * retrieved on a response
 * @property count [Int] value that represent the amount of data received on the response
 * @property offset [Int] value that represent the current page info received
 */
data class PaginationInfo(
    val total_count: Int,
    val count: Int,
    val offset: Int
)
