package io.xavatarlabs.atlaskeys.engine.runtime

import io.xavatarlabs.atlaskeys.keyboard.layout.models.Metadata 
import io.xavatarlabs.atlaskeys.keyboard.layout.Category 

data class RLayout(
  val id: String,
  val name: String,
  val version: Int,
  val rows: List<RRow>, //<ResolvedRow> is deleted weirdly by my keyboard
  val metadata: Metadata,
  val category: Category
)