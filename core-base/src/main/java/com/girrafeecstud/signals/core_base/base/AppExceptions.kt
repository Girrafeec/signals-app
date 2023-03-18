package com.girrafeecstud.signals.core_base.base

import java.io.IOException

class NoNetworkConnectionException: IOException()
class GpsIsNotEnabledException: IOException()
class LocationPermissionsNotGrantedException: IOException()

object RouteBuildingException: IOException()