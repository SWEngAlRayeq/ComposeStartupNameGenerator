package app.startup_name_generator.data.model

import com.google.gson.annotations.SerializedName

data class StartupResponse(
    @SerializedName("this") val thisThing: String,
    @SerializedName("that") val that: String
)