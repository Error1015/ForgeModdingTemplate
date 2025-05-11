package mod.author.examplemod

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import org.error1015.dragonloot.block.ModBlocks
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import kotlin.time.Duration.Companion.seconds

const val ID = "examplemod"
const val NAME = "Example Mod"

@Mod(ID)
object ExampleModMain {
    val scope = CoroutineScope(Dispatchers.Default)

    init {
        ModBlocks.BLOCKS.register(MOD_BUS)
        MOD_BUS.addListener(::onClientSetup)
    }

    fun onClientSetup(event: FMLClientSetupEvent) {
        scope.launch {
            println("Hello Minecraft ! from $NAME")

            delay(5.seconds)

            println("Can you see me?")
        }
    }
}