package yuuto.yuutolib.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.IIcon
import yuuto.yuutolib.ref.LibConstants
import java.util.Random

/**
 * @author Jacob
 */
class ModBlock(mat:Material, tab:CreativeTabs, modId:String, unlocName:String) extends Block(mat){
  setCreativeTab(tab);
  setBlockName(modId.toLowerCase()+":"+unlocName)
  
  override def setBlockName(name:String):Block = {
    super.setBlockName(name);
    this.setBlockTextureName(name);
    return this;
  }
  
  def getBlockIcon():IIcon={
    return this.blockIcon
  }
  def setBlockIcon(icon:IIcon)={
    this.blockIcon=icon;
  }
  override def getTextureName():String={
    return super.getTextureName();
  }
  
  def getRandom():Random=LibConstants.LIB_RANDOM;
}