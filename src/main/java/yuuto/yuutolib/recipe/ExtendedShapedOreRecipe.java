package yuuto.yuutolib.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ExtendedShapedOreRecipe extends ShapedOreRecipe{
	public ExtendedShapedOreRecipe(Block     result, Object[] recipe){ this(new ItemStack(result), recipe); }
    public ExtendedShapedOreRecipe(Item      result, Object[] recipe){ this(new ItemStack(result), recipe); }
    public ExtendedShapedOreRecipe(ItemStack result, Object[] recipe){
    	super(result, recipe);
    }

}
