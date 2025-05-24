package com.alican.navigation3.extension

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey


fun <T : NavKey> SnapshotStateList<NavKey>.addRouteSafely(route: T) {
    if (!this.contains(route)) {
        this.add(route)
    }
}