package yuuto.yuutolib.item

import net.minecraft.item.Item
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.IIcon
import yuuto.yuutolib.ref.LibConstants
import java.util.Random

/**
 * @author Jacob
 */
class ModItem(tab:CreativeTabs, modId:String, itemName:String) extends Item{
  setUnlocalizedName(modId.toLowerCase()+":"+itemName);
  setCreativeTab(tab);
  
  override def setUnlocalizedName(name:String):Item={
      super.setUnlocalizedName(name);
      this.setTextureName(name);
      return this;
  }
  
  def getItemIcon():IIcon={
    return itemIcon;
  }
  def setItemIcon(icon:IIcon)={
    itemIcon = icon;
  }
  
  def getRandom():Random=LibConstants.LIB_RANDOM;
}