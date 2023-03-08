package com.girrafeecstud.society_safety_app.navigation.extensions

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import com.girrafeecstud.society_safety_app.navigation.DefaultFlowScreen
import com.girrafeecstud.society_safety_app.navigation.destination.FlowDestination
import com.girrafeecstud.society_safety_app.navigation.destination.NavigationDestination

fun NavController.setStartDestination(
    destination: NavigationDestination,
    graphId: Int
) {
    val graph = this.navInflater.inflate(graphId)
    graph.setStartDestination(destination.destinationId)
    // Add start destination args (default screen value)
    var bundle: Bundle? = null
    if (destination is FlowDestination) {
        bundle = Bundle()
        bundle.putSerializable("defaultScreen", destination.defaultScreen)
    }
    this.setGraph(graph = graph, startDestinationArgs = bundle)
}