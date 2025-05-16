package mod.author.examplemod

import net.neoforged.fml.common.Mod
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val ID = "examplemod"
const val NAME = "Example Mod"

@Mod(ID)
object ExampleModMain {
    val logger: Logger = LoggerFactory.getLogger(ID)

    init {
        logger.info("Hello, World! $NAME")

        // ModBlocks.BLOCKS.register(MOD_BUS)
    }
}