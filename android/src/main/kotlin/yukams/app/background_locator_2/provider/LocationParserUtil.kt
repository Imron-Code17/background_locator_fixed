package yukams.app.background_locator_2.provider

import android.location.Location
import android.os.Build
import com.google.android.gms.location.LocationResult
import yukams.app.background_locator_2.Keys
import java.util.HashMap

class LocationParserUtil {
    companion object {
        fun getLocationMapFromLocation(location: Location): HashMap<String, Any> {
            var speedAccuracy = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                speedAccuracy = location.speedAccuracyMetersPerSecond
            }
            var isMocked = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                isMocked = location.isFromMockProvider
            }

            // ✅ EXPLICITLY CREATE HASHMAP WITH STRING, ANY
            val result = HashMap<String, Any>()
            result[Keys.ARG_IS_MOCKED] = isMocked
            result[Keys.ARG_LATITUDE] = location.latitude
            result[Keys.ARG_LONGITUDE] = location.longitude
            result[Keys.ARG_ACCURACY] = location.accuracy
            result[Keys.ARG_ALTITUDE] = location.altitude
            result[Keys.ARG_SPEED] = location.speed
            result[Keys.ARG_SPEED_ACCURACY] = speedAccuracy
            result[Keys.ARG_HEADING] = location.bearing
            result[Keys.ARG_TIME] = location.time.toDouble()
            result[Keys.ARG_PROVIDER] = location.provider
            
            return result
        }

        fun getLocationMapFromLocation(location: LocationResult?): HashMap<String, Any>? {
            val firstLocation = location?.lastLocation ?: return null

            var speedAccuracy = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                speedAccuracy = firstLocation.speedAccuracyMetersPerSecond
            }
            var isMocked = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                isMocked = firstLocation.isFromMockProvider
            }

            // ✅ EXPLICITLY CREATE HASHMAP WITH STRING, ANY
            val result = HashMap<String, Any>()
            result[Keys.ARG_IS_MOCKED] = isMocked
            result[Keys.ARG_LATITUDE] = firstLocation.latitude
            result[Keys.ARG_LONGITUDE] = firstLocation.longitude
            result[Keys.ARG_ACCURACY] = firstLocation.accuracy
            result[Keys.ARG_ALTITUDE] = firstLocation.altitude
            result[Keys.ARG_SPEED] = firstLocation.speed
            result[Keys.ARG_SPEED_ACCURACY] = speedAccuracy
            result[Keys.ARG_HEADING] = firstLocation.bearing
            result[Keys.ARG_TIME] = firstLocation.time.toDouble()
            
            return result
        }
    }
}