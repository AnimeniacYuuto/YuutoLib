package yuuto.yuutolib.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ExtendedShapelessOreRecipe extends ShapelessOreRecipe{
	public ExtendedShapelessOreRecipe(Block result, Object[] recipe){ this(new ItemStack(result), recipe); }
    public ExtendedShapelessOreRecipe(Item  result, Object[] recipe){ this(new ItemStack(result), recipe); }

    public ExtendedShapelessOreRecipe(ItemStack result, Object[] recipe){
    	super(result, recipe);
    }
}
