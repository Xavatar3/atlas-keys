package io.xavatarlabs.atlaskeys.layout

import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.KeyRow
import io.xavatarlabs.atlaskeys.structures.Types

val qwertyMatrix: List<KeyRow> = listOf(
  KeyRow(
    height = 56,
    listOf(
      Key("Q"), Key("W"), Key("E"), Key("R"),
      Key("T"), Key("Y"), Key("U"), Key("I"), 
      Key("O"), Key("P")
    )
  ),
  
  KeyRow(
    height = 56,
    listOf(
      Key("A"), Key("S"), Key("D"), Key("F"),
      Key("G"), Key("H"), Key("J"), Key("K"),
      Key("L")
    )
  ),
  
  KeyRow(
    height = 56,
    listOf(
      Key("⇧",type = Types.SHIFT, width = 1.5f),
      Key("Z"), Key("X"), Key("C"), Key("V"),
      Key("B"), Key("N"), Key("M"),
      Key("⌫", type=Types.DELETE, width=1.5f)
    )
  ),
  
  KeyRow(
    height = 56,
    listOf(
      Key("0-9", width = 1.2f),
      Key("⎵", type=Types.SPACE, width=5f),
      Key("⏎", type=Types.ENTER, width=1.8f)
    )
  )
)



/**
* Shift
  ⬆️  ▲
Return / Enter
↵ ↲ ↵  ⏎
Delete
⌦
Tab
⇥
Caps Lock
⇪
Space
␣ ▭  ·  
*/