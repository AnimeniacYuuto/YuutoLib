package yuuto.yuutolib.block

import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World;
import net.minecraft.item.ItemStack
import net.minecraft.entity.EntityLivingBase

/**
 * @author Jacob
 */
abstract class ModBlockContainer(mat:Material, tab:CreativeTabs, modId:String, unlocName:String) extends ModBlock(mat, tab, modId, unlocName) with ITileEntityProvider
{

    override def breakBlock(world:World, x:Int, y:Int, z:Int, block:Block, meta:Int)
    {
        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }

    override def onBlockEventReceived(world:World, x:Int, y:Int, z:Int, event:Int, args:Int):Boolean={
        super.onBlockEventReceived(world, x, y, z, event, args);
        val tileentity:TileEntity = world.getTileEntity(x, y, z);
        if(tileentity != null) 
          return tileentity.receiveClientEvent(event, args); 
        else 
          return false;
    }
    
  override def onBlockPlacedBy(world:World, x:Int, y:Int, z:Int, player:EntityLivingBase, stack:ItemStack) {
    super.onBlockPlacedBy(world, x, y, z, player, stack);
    val tile:TileEntity = world.getTileEntity(x, y, z);
    if(tile != null)
      initializeTile(tile, world, x, y, z, player, stack);
  }
  def initializeTile(tile:TileEntity, world:World, x:Int, y:Int, z:Int, player:EntityLivingBase, stack:ItemStack){
    
  }
}