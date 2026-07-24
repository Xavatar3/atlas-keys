package io.xavatarlabs.atlaskeys.layout

import io.xavatarlabs.atlaskeys.keyboard.Key
import io.xavatarlabs.atlaskeys.keyboard.KeyRow
import io.xavatarlabs.atlaskeys.keyboard.KeyType


object SymbolsLayout : BaseLayout() {

override val rows = listOf(
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
      Key("@"), Key("#"), Key("$"),
      Key("_"), Key("&"), Key("-"),
      Key("+"), Key("("), Key(")")
    )
  ),

  KeyRow(56,
    listOf(
      Key("*"), Key("\""), Key("'"),
      Key(":"), Key(";"), Key("!"),
      Key("?")
    )
  ),

  KeyRow(56,
    listOf(
      Key("abc", type = KeyType.ABC, width = 1.4f),
      Key("%"), Key(","), Key("."),
      Key("⎵", type = KeyType.SPACE, width = 3f),
      Key("⌫", type = KeyType.DELETE, width = 1.4f),
      Key("⏎", type = KeyType.ENTER, width = 1.6f)
    )
  )
)

  
}