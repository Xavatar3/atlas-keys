USER
 |
 ▼
IMEService
 |
 ▼
Keyboard
 |
 ├───────────────┐
 ▼               ▼
Controls        Body
 |               |
 ▼               ▼
Action        Layout
System          |
                ▼
          LayoutManager
                |
      ┌─────────┼─────────┐
      ▼         ▼         ▼
 Loader     Parser    Validator
      |
      ▼
 Normalizer
      |
      ▼
 Renderer
      |
      ▼
 KeyView


Key Click

KeyView

 ↓

KeyDispatcher

 ↓

InputHandler

 ↓

ActionResolver

 ↓

ActionRegistry

 ↓

ActionPlugin

 ↓

ActionExecutor

 ↓

InputConnection







Keyboard
    │
    ▼
Layout
    │
    ├── LayoutLoader
    ├── LayoutRepository
    ├── LayoutValidator
    ├── LayoutResolver
    └── LayoutRenderer
             │
             ▼
         Android Views
         
 
 
 



JSON
   │
   ▼
LayoutLoader
   │
   ▼
JSONObject
   │
   ▼
LayoutParser
   │
   ▼
LayoutModel
   │
   ▼
LayoutResolver
   │
   ▼
ResolvedLayout
   │
   ▼
LayoutRenderer



assets/layouts/qwerty.json
            │
            ▼
     LayoutLoader
            │
            ▼
   LayoutRepository
            │
            ▼
   LayoutValidator
            │
            ▼
    LayoutResolver
            │
            ▼
       JSONObject




LayoutModel
│
├── id
├── name
├── version
├── parent
├── metadata
│     ├── language
│     ├── locale
│     ├── author
│     └── ...
├── requirements
│     ├── layoutVersion
│     ├── engineVersion
│     └── features
├── defaults
│     ├── type
│     ├── width
│     ├── height
│     └── ...
└── rows
      └── keys





qwerty.json

     |
     v

LayoutLoader

     |
     v

LayoutRepository

     |
     v

LayoutValidator

     |
     v

LayoutParser

     |
     v

LayoutModel

     |
     v

LayoutResolver

     |
     v

ResolvedLayout

     |
     v

LayoutRenderer

     |
     v

Android Views






OBJECT (long life)
===================

AtlasRuntime
ThemeRepository
LayoutRepository cache


CLASS (IME life)
================

KeyboardEngine
InputHandler
LayoutEngine
SuggestionEngine


VIEW (UI life)
==============

Keyboard
Body
Layout
KeyView
Controls



AtlasKeys/
│
├── apps/
│   ├── keyboard/
│   │   ├── KeyboardApp.kt
│   │   ├── Keyboard.kt
│   │   ├── IMEService.kt
│   │   └── ...
│   │
│   ├── games/
│   │   ├── GamesApp.kt
│   │   ├── Stats.kt
│   │   └── ...
│   │
│   └── stats/
│       ├── StatsApp.kt
│       └── ...
│
├── core/
│   ├── engine/
│   │   ├── State.kt
│   │   ├── Events.kt
│   │   └── Runtime.kt
│   │
│   ├── ui/
│   │   ├── Renderer.kt
│   │   └── Components.kt
│   │
│   ├── config/
│   │   └── ConfigManager.kt
│   │
│   └── storage/
│
├── features/
│   ├── layouts/
│   ├── themes/
│   ├── suggestions/
│   └── actions/
│
├── services/
│   ├── IMEService.kt
│   ├── AudioService.kt
│   └── InputService.kt
│
├── settings/
│   └── Settings.kt
│
└── App.kt


JSON
 ↓
Parser
 ↓
Model
 ↓
Validator
 ↓
Normalizer
 ↓
Compiler
 ↓
Runtime
 ↓
Renderer
 ↓
View


KeyView
│
├── KeySurface
│   ├── Label
│   ├── Hint
│   └── Icon
│
├── Touch
├── LongPress
├── Repeat
├── Preview
├── Popup
├── Animation
└── Theme


