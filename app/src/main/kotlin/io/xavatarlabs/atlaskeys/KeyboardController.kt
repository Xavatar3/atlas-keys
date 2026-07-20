package io.xavatarlabs.atlaskeys.keyboard

import android.view.View
import android.widget.Button
import android.widget.FrameLayout

import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer

class KeyboardController(
  private val state: State,
  private val inputHandlerProvider: () -> InputHandler
) {

  lateinit var root: FrameLayout
    private set

  lateinit var body: FrameLayout
    private set

  lateinit var overlay: FrameLayout
    private set

  lateinit var settingsBtn: Button
    private set

  private lateinit var renderer: KeyboardRenderer

  fun bind(view: View) {

    root = view.findViewById(R.id.keyboard_root)
    body = view.findViewById(R.id.keyboard_body)
    overlay = view.findViewById(R.id.keyboard_overlay)
    settingsBtn = view.findViewById(R.id.btn_settings)

    renderer = KeyboardRenderer(state) { key ->

      KeyView(view.context).apply {

        tag = key

        bind(key, state)

        setOnClickListener {
          inputHandlerProvider()
            .handleKeyPress(key)
        }
      }
    }

    renderer.render(
      body,
      qwertyMatrix
    )
  }

  fun refresh() {
    renderer.refresh(root)
  }

  fun render() {
    renderer.render(
      body,
      qwertyMatrix
    )
  }
}