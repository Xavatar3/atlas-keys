package io.xavatarlabs.atlaskeys.engine

// Android
import android.view.View 

// AtlasKeys
import io.xavatarlabs.atlaskeys.core.Atlas 
import io.xavatarlabs.atlaskeys.engine.Parser 
import io.xavatarlabs.atlaskeys.engine.Loader 
import io.xavatarlabs.atlaskeys.engine.Renderer 
import io.xavatarlabs.atlaskeys.engine.Resolver 
import io.xavatarlabs.atlaskeys.engine.Validator 
import io.xavatarlabs.atlaskeys.engine.Repository 
import io.xavatarlabs.atlaskeys.keyboard.layout.LayoutId 


object KEngine {
  private val context = Atlas.context 
  private val loader = Loader(context)
  private val renderer = Renderer(context)

  fun render(layout: LayoutId): View {
    // later just attach Repository
    Repository.load(layout.id)?.let{  return renderer.render(it) }
    // later loader will stop using ids and maybe auto detect
    val json = loader.load(layout.id)
    val model = Parser.parse(json)
    Validator.validate(model)
      .takeIf{ errors -> errors.any() }
      ?.let{ errors -> throw KError(errors.joinToString("\n")) }
    val runtime = Resolver.resolve(model)
    Repository.save(layout.id, runtime) // later save view if clean
    return renderer.render(runtime)
  }
}