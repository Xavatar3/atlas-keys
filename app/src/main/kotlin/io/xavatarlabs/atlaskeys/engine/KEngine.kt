package io.xavatarlabs.atlaskeys.engine

// Android
import android.view.View 

// Kotlin
import kotlinx.coroutines.launch 
import kotlinx.coroutines.Dispatchers 
import kotlinx.coroutines.CoroutineScope 

// AtlasKeys
import io.xavatarlabs.atlaskeys.core.Atlas 
import io.xavatarlabs.atlaskeys.engine.kengine.* 
import io.xavatarlabs.atlaskeys.engine.kengine.runtime.RLayout 
import io.xavatarlabs.atlaskeys.keyboard.layout.LayoutId 


class KEngine {
    private var hasPrewarmed = false
    private val validator = Validator()
    private val context = Atlas.context 
    private val loader = Loader(context)
    private val renderer = Renderer(context)
    private var lastGoodLayoutId: String? = null
    private val engineScope = CoroutineScope(Dispatchers.IO)

    fun render(layout: LayoutId): View {
        // later just attach Repository
        Repository.load(layout.id)?.let {
            lastGoodLayoutId = layout.id
            return renderer.render(it)
        }
        // later loader will stop using ids and maybe auto detect
        return try {
            val resolved = resolve(layout.id)
            lastGoodLayoutId = layout.id
            if (!hasPrewarmed) {
                hasPrewarmed = true
                prewarmOthers(except = layout.id)
            }
            renderer.render(resolved)
        } catch (error: KError) {
            lastGoodLayoutId
                ?.let { fallbackId -> Repository.load(fallbackId) }
                ?.let { fallback -> renderer.render(fallback) }
                ?: throw error // nothing has ever worked - a bundled asset is broken;
        }
    }

    private fun resolve(id: String): RLayout {
        val json = loader.load(id)
        val model = Parser.parse(json)
        validator.validate(model)
            .takeIf { errors -> errors.any() }
            ?.let { errors -> throw KError(errors.joinToString("\n")) }
        val runtime = Resolver.resolve(model)
        Repository.save(id, runtime) // later save view if clean
        return runtime
    }

    private fun prewarmOthers(except: String) {
        engineScope.launch {
            context.assets.list("layouts")?.forEach { filename ->
                val id = filename.removeSuffix(".json")
                if (id != except && !Repository.contains(id)) {
                    runCatching { resolve(id) }
                }
            }
        }
    }
}