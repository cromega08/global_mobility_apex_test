package cromega.studio.globalmobilityapextest.api.generic

/**
 * Object to keep the constant data to be used by the Giphy API architecture in general.
 *
 * @property GIPHY_BASE_URL base URL for all the requests to the Giphy API
 * @property GIPHY_API_KEY API_KEY for authorization on Giphy API services
 * @property DATA_LIMIT max amount of results to be received through Giphy API endpoints
 */
object GiphyApiConstants
{
    const val GIPHY_BASE_URL = "https://api.giphy.com/v1/gifs/"
    const val GIPHY_API_KEY = "/Your API_KEY here/"
    const val DATA_LIMIT = 20
}