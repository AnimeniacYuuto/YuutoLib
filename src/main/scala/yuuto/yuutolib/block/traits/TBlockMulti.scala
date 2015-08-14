package yuuto.yuutolib.block.traits

import net.minecraft.block.Block;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import yuuto.yuutolib.block.ModBlock

/**
 * @author Jacob
 */
abstract trait TBlockMulti extends ModBlock{
  
  var textureNames:Array[String] = null;
  var subBlockCount = 0;
  
  def setTextureNames(names:String*):Block={
    textureNames = names.toArray;
    subBlockCount = textureNames.length;
    return this;
  }
  @SideOnly(Side.CLIENT)
  def getTextureName(meta:Int):String={
    if(textureNames == null || textureNames.length < 1){
      return super.getTextureName();
    }
    return super.getTextureName()+"."+textureNames(meta);
  }
  
  def setSubBlocks(count:Int):Block={
    subBlockCount = count;
    return this;
  }
  
  def subBlocks():Int={
    return subBlockCount;
  }
  
  override def getSubBlocks(item:Item, tab:CreativeTabs, subItems:List[_])={
    val list = subItems.asInstanceOf[List[ItemStack]];
    for (ix <- 0 until subBlocks()) {
      list.add(new ItemStack(this, 1, ix));
    }
  }
}