import com.google.gson.annotations.SerializedName

data class Wand (
	@SerializedName("wood") val wood : String,
	@SerializedName("core") val core : String,
	@SerializedName("length") val length : Float
)