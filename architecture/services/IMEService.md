══════════════════════════════════════════════
                 IMEService.kt
══════════════════════════════════════════════

ROLE:
Android Input Method entry point.

RESPONSIBILITIES:
- Starts the keyboard
- Creates Keyboard view
- Provides InputConnection
- Handles Android lifecycle
- Passes EditorInfo information


KNOWS:
Keyboard


FLOW:
Android
  |
  ▼
IMEService
  |
  ▼
Keyboard