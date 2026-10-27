package appeng.util.prioitylist;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import appeng.api.storage.data.IAEItemStack;

public class IntersectPriorityList implements IPartitionList<IAEItemStack> {

    private final IPartitionList<IAEItemStack> partitionListA;
    private final IPartitionList<IAEItemStack> partitionListB;

    public IntersectPriorityList(IPartitionList<IAEItemStack> partitionListA,
            IPartitionList<IAEItemStack> partitionListB) {
        this.partitionListA = partitionListA;
        this.partitionListB = partitionListB;
    }

    @Override
    public boolean isListed(IAEItemStack input) {
        return partitionListA.isListed(input) && partitionListB.isListed(input);
    }

    @Override
    public boolean isEmpty() {
        return partitionListA.isEmpty() && partitionListB.isEmpty();
    }

    @Override
    public Iterable<IAEItemStack> getItems() {
        if (partitionListA.getItems() == null) return partitionListB.getItems();
        if (partitionListB.getItems() == null) return partitionListA.getItems();

        HashSet<IAEItemStack> itemsInA = Sets.newHashSet(partitionListA.getItems());
        HashSet<IAEItemStack> itemsInB = Sets.newHashSet(partitionListB.getItems());

        Set<IAEItemStack> intersection = new HashSet<>(itemsInA);
        intersection.retainAll(itemsInB);
        return intersection;
    }
}
