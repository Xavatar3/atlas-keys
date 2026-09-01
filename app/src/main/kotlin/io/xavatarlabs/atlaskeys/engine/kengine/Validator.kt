package io.xavatarlabs.atlaskeys.engine.kengine

// JSON
import org.json.JSONArray 
import org.json.JSONObject 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.layout.models.* 


class Validator {
    private fun checkId(layout: LModel): List<String> =
        if (layout.id.isBlank()) listOf("Missing layout id.") else emptyList()
    private fun checkVersion(layout: LModel): List<String> =
        if (layout.version < 0) listOf("Invalid layout version.") else emptyList()

    private fun checkRows(layout: LModel): List<String> {
        if (layout.rows.isEmpty()) return listOf("Missing rows.")
        val ids = mutableSetOf<String>()
        val errors = mutableListOf<String>()
        layout.rows.forEach { row -> checkKeys(row.keys, ids, errors) }
        return errors
    }

    private fun checkKeys(keys: List<KModel>, ids: MutableSet<String>, errors: MutableList<String>) {
        keys.forEach { key ->
            if (!ids.add(key.id)) { errors += "Duplicate key ${key.id}" }
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
    fun validate(model: LModel): List<String> =
        checkId(model) + checkVersion(model) + checkRows(model)
}