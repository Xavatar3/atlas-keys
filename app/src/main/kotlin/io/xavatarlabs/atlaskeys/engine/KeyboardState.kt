package io.xavatarlabs.atlaskeys.engine

data class KeyboardState(

var shift:Boolean=false,

var caps:Boolean=false,

var symbols:Boolean=false,

var numbers:Boolean=false,

var emoji:Boolean=false,

var language:String="en",

var predictionEnabled:Boolean=true

)