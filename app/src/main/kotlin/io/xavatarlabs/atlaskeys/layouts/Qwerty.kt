package io.xavatarlabs.atlaskeys.layout

import io.xavatarlabs.atlaskeys. structures.Key
import io.xavatarlabs.atlaskeys. structures.Types

val qwertyMatrix: List<List<Key>> = listOf(

  listOf(
    Key("Q"), Key("W"), Key("E"), Key("R"), Key("T"),
    Key("Y"), Key("U"), Key("I"), Key("O"), Key("P")
  ),

  listOf(
    Key("A"), Key("S"), Key("D"), Key("F"), Key("G"),
    Key("H"), Key("J"), Key("K"), Key("L")
  ),

  listOf(
    Key("SHIFT", type = Types.SHIFT, widthWeight = 1.5f),
    Key("Z"), Key("X"), Key("C"),
    Key("V"), Key("B"), Key("N"),
    Key("M"),
    Key("DEL", type = Types.DELETE, widthWeight = 1.5f)
  ),

  listOf(
    Key("123", widthWeight = 1.2f),
    Key("SPACE", type = Types.SPACE, widthWeight = 5f),
    Key("ENTER", type = Types.ENTER, widthWeight = 1.8f)
  )
)