package io.xavatarlabs.atlaskeys.keyboard.layout.models

import io.xavatarlabs.atlaskeys.keyboard.layout.Category 

data class LModel(
  val id: String,
  val name: String,
  val version: Int,
  val parent: String?,
  val rows: List<RModel>,
  val defaults: Defaults,
  val metadata: Metadata,
  val category: Category,
  val requirements: Requirements
)
