package io.xavatarlabs.atlaskeys.engine.kengine


// Android
import android.content.Context 


class Loader(private val context: Context){
  private val DIRECTORY = "layouts"
  fun load(id: String): String{
    val path = "$DIRECTORY/$id.json"
    return context.assets
      .open(path)
      .bufferedReader()
      .use{ it.readText() }
  }
}

// TODO:
// Support multiple layout sources.
// Possible sources:
// - built-in assets
// - downloaded layouts
// - user layouts