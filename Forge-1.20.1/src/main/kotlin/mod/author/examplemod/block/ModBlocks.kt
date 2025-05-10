package org.error1015.dragonloot.block

import mod.author.examplemod.ID
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModBlocks {
    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, ID)

    val ExampleBlock by BLOCKS.registerObject("example_block") { Block(BlockBehaviour.Properties.copy(Blocks.STONE)) }
}