package yuuto.yuutolib.block.traits

import net.minecraft.util.IIcon
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side;
/**
 * @author Jacob
 */
trait TBlockMultiBase extends TBlockMulti{
  
  var icons:Array[IIcon] = null;
  
  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(reg:IIconRegister)={
    if(textureNames == null || textureNames.length < 1){
      setBlockIcon(reg.registerIcon(getTextureName()));
    }else{
      icons = new Array[IIcon](textureNames.length);
      for(i<-0 until icons.length){
        icons(i) = reg.registerIcon(getTextureName(i));
      }
      setBlockIcon(icons(0));
    }
  }
  
  @SideOnly(Side.CLIENT)
  override def getIcon(side:Int, meta:Int):IIcon={
    if(icons == null || icons.length < 1){
      return getBlockIcon();
    }else{
      return icons(if(meta < 0){0}else{if(meta >= icons.length){icons.length-1}else{meta}});
    }
  }
  
}