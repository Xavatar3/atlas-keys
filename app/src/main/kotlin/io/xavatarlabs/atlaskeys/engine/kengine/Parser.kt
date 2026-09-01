package io.xavatarlabs.atlaskeys.engine.kengine

// JSON
import org.json.JSONObject 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.layout.models.* 
import io.xavatarlabs.atlaskeys.keyboard.layout.Category 


object Parser {
  private fun parseRows(rows: org.json.JSONArray, defaults: JSONObject?): List<RModel>{
    val list = mutableListOf<RModel>()
    repeat(rows.length()){ index ->
      list += RModel(
        parseKeys(
          rows
            .getJSONObject(index)
            .getJSONArray("keys"),
          defaults
        )
      )
    }
    return list
  }

  private fun parseKeys(keys: org.json.JSONArray, defaults: JSONObject?): List<KModel>{
    val list = mutableListOf<KModel>()
    repeat(keys.length()){ index ->
      val key = keys.getJSONObject(index)
      val array = key.optJSONArray("hint")
      list += KModel(
        id = key.getString("id"),
        icon = key.optString("icon"),
        label = key.optString("label", key.getString("id")),
        ktype = KType.valueOf(key.optString("type", defaults?.optString("type", "CHAR"))),
        // TODO:
        // Apply defaults in resolver
        width = key.optDouble("width", defaults?.optDouble("width", 1.0) ?: 1.0).toFloat(),
        enabled = key.optBoolean("enabled", defaults?.optBoolean("enabled", true) ?: true),
        visible = key.optBoolean("visible", defaults?.optBoolean("visible", true) ?: true),
        hint = mutableListOf<String>().apply {
          if(array != null){ repeat(array.length()){ add(array.getString(it)) } }
        }
      )
    }
    return list
  }

  private fun parseDefaults(json: JSONObject): Defaults{
    return Defaults(
      height = json.getInt("height"),
      enabled = json.getBoolean("enabled"),
      visible = json.getBoolean("visible"),
      width = json.getDouble("width").toFloat(),
      ktype = KType.valueOf(json.getString("type")),
      uppercaseLabel = json.getBoolean("uppercaseLabel")
    )
  }

  private fun parseMetadata(json: JSONObject): Metadata{
    val tags = mutableListOf<String>()
    val array = json.getJSONArray("tags")
    repeat(array.length()){ i -> tags += array.getString(i) }
    return Metadata(
      tags = tags,
      author = json.getString("author"),
      locale = json.getString("locale"),
      created = json.getString("created"),
      updated = json.getString("updated"),
      license = json.getString("license"),
      language = json.getString("language"),
      description = json.getString("description")
    )
  }

  private fun parseRequirements(json: JSONObject): Requirements{
    val features = mutableListOf<String>()
    val array = json.getJSONArray("features")
    repeat(array.length()){ i -> features += array.getString(i) }
    return Requirements(
      features = features,
      engineVersion = json.getInt("engineVersion"),
      layoutVersion = json.getInt("layoutVersion")
    )
  }

  // TODO:
  // Move JSON parsing to serialization layer.
  // Parser should not manually map every field forever.
  // kotlinx.serialization

  // TODO:
  // Add schema version migration.
  // Old layouts should upgrade automatically.
  fun parse(json: String): LModel {
    val layout = JSONObject(json)
    return LModel(
      rows = parseRows(
        layout.getJSONArray("rows"),
        layout.optJSONObject("defaults")
      ),
      id = layout.getString("id"),
      name = layout.optString("name"),
      version = layout.getInt("version"),
      parent = layout.optString("extends").ifEmpty { null },
      defaults =parseDefaults(layout.getJSONObject("defaults")),
      metadata = parseMetadata(layout.getJSONObject("metadata")),
      category = Category.valueOf(layout.optString("type", "custom").uppercase()),
      requirements = parseRequirements(layout.getJSONObject("requires"))
    )
  }
}

// AI is actually less sensitive to trailing commas than middle commas in json