package yuuto.yuutolib.item.traits

import net.minecraft.util.IIcon
import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.renderer.texture.IIconRegister
import cpw.mods.fml.relauncher.Side

/**
 * @author Jacob
 */
trait TItemMultiBase extends TItemMulti{
  
  var icons:Array[IIcon] = null;
  
  @SideOnly(Side.CLIENT)
  override def registerIcons(reg:IIconRegister)={
    if(textureNames == null || textureNames.length < 1){
      setItemIcon(reg.registerIcon(super.getIconString()));
    }else{
      icons = new Array[IIcon](textureNames.length);
      for(i<-0 until icons.length){
        icons(i) = reg.registerIcon(getIconString(i));
      }
      setItemIcon(icons(0));
    }
  }
  
  @SideOnly(Side.CLIENT)
  override def getIconFromDamage(meta:Int):IIcon={
    if(icons == null || icons.length < 1){
      return getItemIcon();
    }else{
      return icons(if(meta < 0){0}else{if(meta >= icons.length){icons.length-1}else{meta}});
    }
  }
}