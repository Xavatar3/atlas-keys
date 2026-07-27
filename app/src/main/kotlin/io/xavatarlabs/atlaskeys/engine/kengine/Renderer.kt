package io.xavatarlabs.atlaskeys.engine

// Android
import android.view.Gravity 
import android.content.Context 
import android.widget.TextView 
import android.widget.LinearLayout 

// AtlasKeys
import io.xavatarlabs.atlaskeys.engine.runtime.* 
import io.xavatarlabs.atlaskeys.keyboard.KeyStore 
import io.xavatarlabs.atlaskeys.keyboard.LayoutKeys 
import io.xavatarlabs.atlaskeys.keyboard.key.KeyView 
import io.xavatarlabs.atlaskeys.keyboard.input.InputEngine 


class Renderer(private val context: Context){
  private lateinit var store: LayoutKeys
  private val match = LinearLayout.LayoutParams.MATCH_PARENT

  private fun Row(row: RRow): LinearLayout{
    val layout = LinearLayout(context)
    layout.orientation = LinearLayout.HORIZONTAL
    for (key in row.keys) {
      val params = LinearLayout.LayoutParams(0, match, key.width)
      layout.addView(Key(key), params)
    }
    return layout
  }

  // TODO:
  // Move view styling to ThemeRenderer.
  // Renderer should only create structure.
  private fun Key(key: RKey): KeyView{
    //val label = key.label.ifEmpty { key.id }
    return KeyView(context).apply {
      bind(key)
      store.add(key.id, this)
      setOnKeyClick(InputEngine::dispatch)
    }
  }

  fun render(layout: RLayout): LinearLayout{
    val keyboard = LinearLayout(context)
    val rows = layout.rows
    //KeyStore.get(layout.category).clear()
    store = KeyStore.get(layout.category)
    keyboard.orientation = LinearLayout.VERTICAL
    rows.forEach{ row -> keyboard.addView(
      Row(row),
      LinearLayout.LayoutParams(match, 0, 1f)
    )}
    return keyboard
  }
}