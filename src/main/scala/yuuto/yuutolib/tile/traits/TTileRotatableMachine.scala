package yuuto.yuutolib.tile.traits

import yuuto.yuutolib.tile.ModTile;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Jacob
 */
trait TTileRotatableMachine extends ModTile{
  var facing:ForgeDirection = ForgeDirection.NORTH;
  
  def rotateAround(axis:ForgeDirection):Boolean={
    facing = facing.getRotation(axis);
    return true;
  }
  def setRotation(dir:ForgeDirection):Boolean={
    facing = dir;
    return facing == dir;
  }
  
  override def writeToNBT(nbt:NBTTagCompound)={
    super.writeToNBT(nbt);
    nbt.setByte("facing", facing.ordinal().asInstanceOf[Byte]);
  }
  override def readFromNBT(nbt:NBTTagCompound)={
    super.readFromNBT(nbt);
    facing = ForgeDirection.getOrientation(nbt.getInteger("facing"));
  }
  
  override def writeToNBTPacket(nbt:NBTTagCompound)={
    super.writeToNBTPacket(nbt);
    nbt.setByte("facing", facing.ordinal().asInstanceOf[Byte]);
  }
  override def readFromNBTPacket(nbt:NBTTagCompound)={
    super.readFromNBTPacket(nbt);
    facing = ForgeDirection.getOrientation(nbt.getInteger("facing"));
  }
  
}