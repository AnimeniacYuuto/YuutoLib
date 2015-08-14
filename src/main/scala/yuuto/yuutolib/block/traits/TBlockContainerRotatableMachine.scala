package yuuto.yuutolib.block.traits

import yuuto.yuutolib.block.ModBlockContainer
import yuuto.yuutolib.tile.traits.TTileRotatableMachine
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.item.ItemStack
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.MathHelper

/**
 * @author Jacob
 */
trait TBlockContainerRotatable extends ModBlockContainer{
  
  override def rotateBlock(worldObj:World, x:Int, y:Int, z:Int, axis:ForgeDirection):Boolean={
    var tile:TileEntity = worldObj.getTileEntity(x, y, z);
    if(tile.isInstanceOf[TTileRotatableMachine]){
      return tile.asInstanceOf[TTileRotatableMachine].rotateAround(axis);
    }else{
      return false;
    }
  }
  
  override def initializeTile(tile:TileEntity, world:World, x:Int, y:Int, z:Int, player:EntityLivingBase, stack:ItemStack){
    System.out.println("called initializer");
    if(tile.isInstanceOf[TTileRotatableMachine])
      setRotation(tile.asInstanceOf[TTileRotatableMachine], world, x, y, z, player, stack);
    super.initializeTile(tile, world, x, y, z, player, stack);
  }
  def setRotation(tile:TTileRotatableMachine, world:World, x:Int, y:Int, z:Int, player:EntityLivingBase, stack:ItemStack){
    val l:Int = MathHelper.floor_double((player.rotationYaw * 4.0f / 360.0f) + 0.5D) & 3;
    if(l == 0)
      tile.setRotation(ForgeDirection.getOrientation(2));
    else if(l == 1)
      tile.setRotation(ForgeDirection.getOrientation(5));
    else if(l == 2)
      tile.setRotation(ForgeDirection.getOrientation(3));
    else if(l == 3)
      tile.setRotation(ForgeDirection.getOrientation(4));
  }
  
}