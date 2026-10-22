package appeng.util.prioitylist;

import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.integration.IntegrationRegistry;
import appeng.integration.IntegrationType;
import appeng.integration.abstraction.IGT;
import net.minecraft.item.ItemStack;

public class RecipeFilterList implements IPartitionList<IAEItemStack> {

    private final IGT gt = IntegrationRegistry.INSTANCE.getInstanceIfEnabled(IntegrationType.GT);
    private final ItemStack selectedMachine;

    public RecipeFilterList(IAEStack<?> selectedMachine) {
        this.selectedMachine = selectedMachine.getItemStackForNEI();
    }

    @Override
    public boolean isListed(IAEItemStack input) {
        if (gt == null || selectedMachine == null) return false;
        return gt.isMachineInput(selectedMachine, input.getItemStack());
    }

    @Override
    public boolean isEmpty() {
        if (gt == null || selectedMachine == null) return false;
        return gt.isRecipeMapEmpty(selectedMachine);
    }

    @Override
    public Iterable<IAEItemStack> getItems() {
        return null;
    }
}
