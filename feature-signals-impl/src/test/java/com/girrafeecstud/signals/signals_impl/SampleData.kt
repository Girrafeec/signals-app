package com.girrafeecstud.signals.signals_impl

internal val gpsNotEnabledResultResult = BusinessResult.Exception(exceptionType = ExceptionType.GPS_NOT_ENABLED)

internal val locationPermissionsNotGrantedResult = BusinessResult.Exception(exceptionType = ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED)

internal val routeBuildingErrorResult = BusinessResult.Exception(exceptionType = ExceptionType.ROUTE_BUILDING_ERROR)

internal val noInternetConnectionResult = BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION)

internal val internetConnectionTimeoutResult = BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT)

internal val location = UserLocation(60.029667, 30.634020)

internal val rescuers = listOf(
    Rescuer(
        rescuerId = "ce0c4cf7-9968-4155-bea2-e310b1248b08",
        rescuerFirstName = "Mike",
        rescuerLastName = "Wazowski",
        rescuerPhoneNumber = "+79234556985",
        rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/3/38/Mike1.png/revision/latest?cb=20210509121400",
        rescuerLocationLatitude = 60.030479,
        rescuerLocationLongitude = 30.633914
    ),
    Rescuer(
        rescuerId = "16866392-53b1-4398-8a11-5cadfb559e7d",
        rescuerFirstName = "Randall",
        rescuerLastName = "Boggs",
        rescuerPhoneNumber = "+79999999999",
        rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/8/86/Randall.png/revision/latest/scale-to-width-down/1000?cb=20160404023111",
        rescuerLocationLatitude = 60.029972,
        rescuerLocationLongitude = 30.631602
    )
)

internal val defaultRoutes = listOf(
    Route(
        startPoint = Location(60.030479, 30.633914),
        endPoint = Location(60.029667, 30.634020),
        routePoints = listOf(
            Location(60.030479, 30.633914),
            Location(60.029667, 30.634020)
        )
    ),
    Route(
        startPoint = Location(60.029972, 30.631602),
        endPoint = Location(60.029667, 30.634020),
        routePoints = listOf(
            Location(60.029972, 30.631602),
            Location(60.029667, 30.634020)
        )
    )
)

internal val builtRoutes = listOf(
    Rescuer(
        rescuerId = "ce0c4cf7-9968-4155-bea2-e310b1248b08",
        rescuerFirstName = "Mike",
        rescuerLastName = "Wazowski",
        rescuerPhoneNumber = "+79234556985",
        rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/3/38/Mike1.png/revision/latest?cb=20210509121400",
        rescuerLocationLatitude = 60.030479,
        rescuerLocationLongitude = 30.633914,
        route = Route(
            startPoint = Location(60.030479, 30.633914),
            endPoint = Location(60.029667, 30.634020),
            routePoints = listOf(
                Location(60.030479, 30.633914),
                Location(60.029667, 30.634020)
            )
        )
    ),
    Rescuer(
        rescuerId = "16866392-53b1-4398-8a11-5cadfb559e7d",
        rescuerFirstName = "Randall",
        rescuerLastName = "Boggs",
        rescuerPhoneNumber = "+79999999999",
        rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/8/86/Randall.png/revision/latest/scale-to-width-down/1000?cb=20160404023111",
        rescuerLocationLatitude = 60.029972,
        rescuerLocationLongitude = 30.631602,
        route = Route(
            startPoint = Location(60.029972, 30.631602),
            endPoint = Location(60.029667, 30.634020),
            routePoints = listOf(
                Location(60.029972, 30.631602),
                Location(60.029667, 30.634020)
            )
        )
    )
)
