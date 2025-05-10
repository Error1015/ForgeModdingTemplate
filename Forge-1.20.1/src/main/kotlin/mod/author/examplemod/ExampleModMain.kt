package mod.author.examplemod

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import kotlin.time.Duration.Companion.seconds

const val ID = "examplemod"
const val NAME = "Example Mod"

@Mod(ID)
object ExampleModMain {
    val scope = CoroutineScope(Dispatchers.Default)

    init {
        MOD_BUS.addListener(::onCommonSetup)
    }

    fun onCommonSetup(event: FMLCommonSetupEvent) {

        scope.launch {
            println("Hello Minecraft ! from $NAME")

            delay(5.seconds)

            println("Can you see me?")
        }
    }
}