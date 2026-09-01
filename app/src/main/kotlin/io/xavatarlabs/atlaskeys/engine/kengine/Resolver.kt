package io.xavatarlabs.atlaskeys.engine.kengine

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.layout.models.* 
import io.xavatarlabs.atlaskeys.engine.kengine.runtime.* 


object Resolver {
  private fun resolveKey(key: KModel): RKey{
    var label = key.label?: key.id ?: ""
    return RKey(
      id = key.id,
      label = label,
      ktype = key.ktype,
      width = key.width,
      enabled = key.enabled,
      visible = key.visible,
      hint = key.hint,
      icon = key.icon
    )
  }

  fun resolve(model: LModel): RLayout{
    return RLayout(
      id = model.id,
      name = model.name,
      version = model.version,
      metadata = model.metadata,
      category = model.category,
      rows = model.rows.map{ row -> RRow(keys = row.keys.map{ key -> resolveKey(key) }) }
    )
  }
}
//TODO:
//after merging runtime with models
//make a function that iterates json and sets
//the corresponding property. 