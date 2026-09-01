package io.xavatarlabs.atlaskeys.engine.kengine.runtime

import io.xavatarlabs.atlaskeys.keyboard.layout.models.KType 


data class RKey(
  val id: String,
  val ktype: KType,
  val width: Float,
  val icon: String?,
  var label: String,
  val enabled: Boolean,
  val visible: Boolean,
  val hint: List<String>
)