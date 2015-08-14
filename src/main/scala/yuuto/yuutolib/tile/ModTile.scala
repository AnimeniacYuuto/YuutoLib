package yuuto.yuutolib.tile

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * @author Jacob
 */
class ModTile extends TileEntity{
  
  override def getDescriptionPacket():Packet={
    val tagCompound:NBTTagCompound = new NBTTagCompound();
    writeToNBTPacket(tagCompound);
    if(tagCompound.hasNoTags()){
      return null;
    }
    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
  }
  
  override def onDataPacket(net:NetworkManager, pkt:S35PacketUpdateTileEntity)={
    super.onDataPacket(net, pkt);
    readFromNBTPacket(pkt.func_148857_g());
  }
  
  def writeToNBTPacket(nbt:NBTTagCompound)={
    
  }
  def readFromNBTPacket(nbt:NBTTagCompound)={
    
  }
}