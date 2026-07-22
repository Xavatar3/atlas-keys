package io.xavatarlabs.atlaskeys.layout

import android.content.Context

import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.KeyRow


class SymbolsLayout(
    context: Context,
    state: State,
    onKeyClick: (Key) -> Unit
): KeyboardLayout(
    context,
    state,
    onKeyClick
) {

    override fun buildRows(): List<KeyRow> {

        return listOf(
            KeyRow(
                56,
                listOf(
                    Key("!"),
                    Key("@"),
                    Key("#")
                )
            )
        )
    }
}