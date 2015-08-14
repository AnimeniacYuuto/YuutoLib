package yuuto.yuutolib.item.traits

import java.util.List;

import yuuto.yuutolib.item.ModItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.item.Item
import cpw.mods.fml.relauncher.Side

/**
 * @author Jacob
 */
trait TItemMulti extends ModItem{
  var textureNames:Array[String] = null;
  var countSubItems = 0;
  
  def setTextureNames(names:String*):Item={
    textureNames = names.toArray;
    countSubItems = textureNames.length;
    return this;
  }
  @SideOnly(Side.CLIENT)
  def getIconString(meta:Int):String={
    if(textureNames == null || textureNames.length < 1){
      return super.getIconString;
    }
    return super.getIconString()+textureNames(meta);
  }
  
  def setSubItems(count:Int):Item={
    countSubItems = count;
    return this;
  }
  
  def subItemCount():Int={
    return countSubItems;
  }
  
  override def getSubItems(item:Item, tab:CreativeTabs, subItems:List[_])={
    val list = subItems.asInstanceOf[List[ItemStack]];
    for (ix<-0 until subItemCount()) {
      list.add(new ItemStack(this, 1, ix));
    }
  }
  
  override def getUnlocalizedName(stack:ItemStack):String=this.getUnlocalizedName()+"."+stack.getItemDamage();
  override def getMetadata (damageValue:Int):Int=damageValue;
}