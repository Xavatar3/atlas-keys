package io.xavatarlabs.atlaskeys.keyboard.input


sealed class Actions {
  data object Emoji : Actions() 
  data object Enter : Actions() 
  data object Shift : Actions() 
  data object Space : Actions() 
  data object Delete : Actions() 
  data object Symbols : Actions() 
  data class Custom(val id: String): Actions()
  data class TypeWrite(val text: String): Actions() 
}