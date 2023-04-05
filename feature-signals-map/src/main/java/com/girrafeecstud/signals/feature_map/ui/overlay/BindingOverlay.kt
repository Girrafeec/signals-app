/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.ui.overlay

import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

abstract class BindingOverlay : Overlay() {

    protected open fun onAttach(mapView: MapView?) {}

}