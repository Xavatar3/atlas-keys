package io.xavatarlabs.atlaskeys.layout

import android.content.Context

import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.structures.Types
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.KeyRow

class SymbolsLayout(
  context: Context,
  state: State,
  onKeyClick: (Key) -> Unit
) : KeyboardLayout(context, state, onKeyClick) {

  override fun buildRows(): List<KeyRow> {
    return listOf(
      KeyRow(56,
        listOf(
          Key("1"), Key("2"), Key("3"),
          Key("4"), Key("5"), Key("6"),
          Key("7"), Key("8"), Key("9"),
          Key("0")
        )
      ),
      
      KeyRow(56,
                listOf(
                    Key("@"),
                    Key("#"),
                    Key("$"),
                    Key("_"),
                    Key("&"),
                    Key("-"),
                    Key("+"),
                    Key("("),
                    Key(")")
                )
            ),

            KeyRow(
                56,
                listOf(
                    Key("*"),
                    Key("\""),
                    Key("'"),
                    Key(":"),
                    Key(";"),
                    Key("!"),
                    Key("?")
                )
            ),

      KeyRow(56,
        listOf(
          Key("abc", type = Types.ABC, width = 1.4f),
          Key("%"), Key(","), Key("."),
          Key("⎵", type = Types.SPACE, width = 3f),
          Key("/"), Key("⌫", type = Types.DELETE),
          Key("⏎", type = Types.ENTER, width = 1.6f)
          //Key("↵", Types.ENTER)
        )
      )
    )
  }
}
