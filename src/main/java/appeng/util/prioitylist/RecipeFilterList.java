package appeng.util.prioitylist;

import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.integration.IntegrationRegistry;
import appeng.integration.IntegrationType;
import appeng.integration.abstraction.IGT;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.recipe.RecipeMap;

public class RecipeFilterList implements IPartitionList<IAEItemStack> {

    private RecipeMap<?> recipeMap = null;

    public RecipeFilterList(IAEStack<?> selectedMachine) {
        IGT gt = IntegrationRegistry.INSTANCE.getInstanceIfEnabled(IntegrationType.GT);
        if (gt == null || selectedMachine == null) return;
        IMetaTileEntity metaTileEntity = gt.asGTTileEntity(selectedMachine.getItemStackForNEI());
        recipeMap = gt.getRecipeMap(metaTileEntity);
    }

    @Override
    public boolean isListed(IAEItemStack input) {
        return recipeMap.containsInput(input.getItemStack());
    }

    @Override
    public boolean isEmpty() {
        return recipeMap == null;
    }

    @Override
    public Iterable<IAEItemStack> getItems() {
        return null;
    }
}
