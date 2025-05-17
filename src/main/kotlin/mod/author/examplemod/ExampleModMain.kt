package mod.author.examplemod

import mod.author.examplemod.block.ModBlocks
import net.neoforged.fml.common.Mod
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

const val ID = "examplemod"
const val NAME = "ExampleMod"

@Mod(ID)
object ExampleModMain {
    // TMD为什么不执行
    val logger: Logger = LoggerFactory.getLogger(ID)

    init {
        logger.info("Hello, World! $NAME")

        ModBlocks.BLOCKS.register(MOD_BUS)
    }
}