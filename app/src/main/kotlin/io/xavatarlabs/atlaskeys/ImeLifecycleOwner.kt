package io.xavatarlabs.atlaskeys

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner

class ImeLifecycleOwner :
    LifecycleOwner,
    SavedStateRegistryOwner,
    ViewModelStoreOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val savedStateController =
        SavedStateRegistryController.create(this)

    private val internalViewModelStore = ViewModelStore()

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateController.savedStateRegistry

    override val viewModelStore: ViewModelStore
        get() = internalViewModelStore

    fun onCreate() {
        savedStateController.performRestore(null)

        lifecycleRegistry.handleLifecycleEvent(
            Lifecycle.Event.ON_CREATE
        )
    }

    fun onStart() {
        lifecycleRegistry.handleLifecycleEvent(
            Lifecycle.Event.ON_START
        )
    }

    fun onResume() {
        lifecycleRegistry.handleLifecycleEvent(
            Lifecycle.Event.ON_RESUME
        )
    }

    fun onDestroy() {
        lifecycleRegistry.handleLifecycleEvent(
            Lifecycle.Event.ON_DESTROY
        )

        internalViewModelStore.clear()
    }
}
