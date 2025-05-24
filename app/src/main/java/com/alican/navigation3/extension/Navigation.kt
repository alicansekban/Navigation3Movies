package com.alican.navigation3.extension

import androidx.compose.runtime.snapshots.SnapshotStateList


fun <T : Any> SnapshotStateList<Any>.addRouteSafely(route: T) {
    if (!this.contains(route)) {
        this.add(route)
    }
}

fun SnapshotStateList<Any>.removeRouteSafely() {
    if (this.isNotEmpty()) {
        this.removeLastOrNull()
    }
}