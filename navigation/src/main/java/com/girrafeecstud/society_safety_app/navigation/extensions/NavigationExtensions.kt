package com.girrafeecstud.society_safety_app.navigation.extensions

import androidx.navigation.NavController
import com.girrafeecstud.society_safety_app.navigation.destination.NavigationDestination

fun NavController.setStartDestination(
    destination: NavigationDestination,
    graphId: Int
) {
    val graph = this.navInflater.inflate(graphId)
    graph.setStartDestination(destination.destinationId)
    this.graph = graph
}