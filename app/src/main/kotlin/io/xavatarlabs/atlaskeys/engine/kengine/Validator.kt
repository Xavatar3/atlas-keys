package io.xavatarlabs.atlaskeys.engine

// JSON
import org.json.JSONArray 
import org.json.JSONObject 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.layout.models.* 


object Validator {
  private lateinit var layout: LModel
  private val errors = mutableListOf<String>()

  private fun checkId(){ if(layout.id.isBlank()){ errors += "Missing layout id." } }

  private fun checkVersion(){ if(layout.version < 0){ errors += "Invalid layout version." } }

  private fun checkRows(){
    if(layout.rows.isEmpty()){ errors += "Missing rows."; return; }
    val ids = mutableSetOf<String>()
    layout.rows.forEach{ row -> checkKeys(row.keys, ids) }
  }

  private fun checkKeys(keys: List<KModel>, ids: MutableSet<String>){
    keys.forEach { key ->
      if(!ids.add(key.id)){ errors += "Duplicate key ${key.id}" }
    }
  }

  // TODO:
  // Move validation rules into LayoutSchema.
  // Validator should load rules dynamically.

  // TODO:
  // Add compatibility checks:
  // - engine version
  // - required features
  // - unsupported key types
  fun validate(model: LModel): MutableList<String>{
    layout = model
    errors.clear()
    checkId()
    checkVersion()
    checkRows()
    return errors
  }
}