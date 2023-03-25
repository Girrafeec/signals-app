package com.girrafeecstud.signals.core_base.base


fun Double.roundTo(numDecimalPlaces: Int): Double =
    "%.${numDecimalPlaces}f".format(this).toDouble()