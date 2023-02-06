package com.girrafeecstud.society_safety_app.core_base.base

import java.io.IOException

class NoNetworkConnectionException: IOException()
class GpsIsNotEnabledException: IOException()
class LocationPermissionsNotGrantedException: IOException()