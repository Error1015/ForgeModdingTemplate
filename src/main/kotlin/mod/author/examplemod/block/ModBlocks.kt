package org.error1015.dragonloot.block

import mod.author.examplemod.ID
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(BuiltInRegistries.BLOCK, ID)

    val ExampleBlock: Block by BLOCKS.register("example_block") { -> Block(BlockBehaviour.Properties.of()) }
}