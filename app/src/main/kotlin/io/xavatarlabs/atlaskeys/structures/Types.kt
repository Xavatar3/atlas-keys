package io.xavatarlabs.atlaskeys.structures

enum class Types {
  // Text
  CHAR, SPACE, TAB, ENTER,

  // Editing
  DELETE, COPY, CUT, PASTE, SELECT_ALL,
  UNDO, REDO,

  // Shift / Layout
  SHIFT, CAPS_LOCK, SYMBOLS, ABC, NUMBERS,
  FUNCTION,

  // Navigation
  ARROW_DOWN, HOME, END, PAGE_UP, PAGE_DOWN,
  ARROW_LEFT, ARROW_RIGHT, ARROW_UP,

  // Keyboard
  LANGUAGE, GLOBE, EMOJI, CLIPBOARD, MIC,
  SETTINGS, BACK,

  // IME Action (Done, Go, Search, Send, Next...)
  ACTION,

  // Placeholder
  NONE
}
