══════════════════════════════════════════════
              KeyboardEngine.kt
══════════════════════════════════════════════

ROLE:
The brain of AtlasKeys.


RESPONSIBILITIES:

- Coordinates keyboard behaviour
- Receives input events
- Changes state
- Switches layouts
- Executes actions


CONTROLS:


    LayoutManager

    ActionRegistry

    Feedback

    KeyboardState

    ConfigManager


EXAMPLES:


switchLayout("emoji")


execute(Action.DELETE)


updateState {
    shift = true
}