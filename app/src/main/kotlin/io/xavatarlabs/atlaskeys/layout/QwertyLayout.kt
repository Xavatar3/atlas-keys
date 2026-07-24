package io.xavatarlabs.atlaskeys.layout

// Atlaskeys
import io.xavatarlabs.atlaskeys.keyboard.Key
import io.xavatarlabs.atlaskeys.keyboard.KeyRow
import io.xavatarlabs.atlaskeys.keyboard.KeyType


object QwertyLayout : BaseLayout() {


  override val rows = listOf(

    KeyRow(
      56,
      listOf(
        Key("Q"), Key("W"), Key("E"),
        Key("R"), Key("T"), Key("Y"),
        Key("U"), Key("I"), Key("O"),
        Key("P")
      )
    ),


    KeyRow(
      56,
      listOf(
        Key("A"), Key("S"), Key("D"),
        Key("F"), Key("G"), Key("H"),
        Key("J"), Key("K"), Key("L")
      )
    ),


    KeyRow(56,
      listOf(
        Key("⇧", type = KeyType.SHIFT, width = 1.5f),
        Key("Z"), Key("X"), Key("C"),
        Key("V"), Key("B"), Key("N"),
        Key("M"),
        Key("⌫", type = KeyType.DELETE, width = 1.4f)
      )
    ),


    KeyRow(56,
      listOf(
        Key("§", type = KeyType.SYMBOLS, width = 1.4f),
        Key("👻", type = KeyType.EMOJI, width = 1.2f),
        Key("⎵", type = KeyType.SPACE, width = 4.5f),
        Key("⏎", type = KeyType.ENTER, width = 1.6f
        )
      )
    )
  )
}