package mod.author.examplemod

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import mod.author.examplemod.block.ModBlocks
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import kotlin.time.Duration.Companion.seconds

const val ID = "examplemod"
const val NAME = "Example Mod"

@Mod(ID)
@EventBusSubscriber(modid = ID)
object ExampleMod {
    val scope = CoroutineScope(Dispatchers.Default)

    init {
        ModBlocks.BLOCKS.register(MOD_BUS)
    }

    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent) {
        scope.launch {
            println("Hello Minecraft ! from $NAME")

            delay(5.seconds)

            println("Can you see me?")
        }
    }
}