package dataStructure.tree;

import utility.random.CustomRandom;

import java.util.ArrayList;
import java.util.List;

public class PresetDependencyTree {
    Integer numberData;
    int randomChildrenCount = 0;
    final List<PresetDependencyTree> children ;

    public PresetDependencyTree() {
        final int ROOT_NUMBER_DATA = 777;

        this.numberData = ROOT_NUMBER_DATA;
        this.children = new ArrayList<>();
    }

    public PresetDependencyTree(int dependencyNumberData) {
        this.numberData = dependencyNumberData;
        this.children = new ArrayList<>();
    }

    public void presetData() {
        PresetDependencyTree currentRoot = this;

        currentRoot.children.add(makeRandomNode(currentRoot));
        currentRoot.children.add(makeRandomNode(currentRoot));
        currentRoot.children.add(makeRandomNode(currentRoot));

        PresetDependencyTree firstChild = findNodeByIdx(currentRoot, 0);
        PresetDependencyTree thirdChild = findNodeByIdx(currentRoot, 2);

        firstChild.children.add(makeRandomNode(firstChild));
        thirdChild.children.add(makeRandomNode(thirdChild));
        thirdChild.children.add(makeRandomNode(thirdChild));

        PresetDependencyTree thirdFirstChild = findNodeByIdx(thirdChild, 0);

        thirdFirstChild.children.add(makeRandomNode(thirdFirstChild));
        thirdFirstChild.children.add(makeRandomNode(thirdFirstChild));
        thirdFirstChild.children.add(makeRandomNode(thirdFirstChild));
        thirdFirstChild.children.add(makeRandomNode(thirdFirstChild));
    }

    private PresetDependencyTree makeRandomNode (PresetDependencyTree currentNode) {
        final int MIN = 1;
        final int MAX = 100;

        currentNode.randomChildrenCount++;
        return new PresetDependencyTree(CustomRandom.generateNumber(MIN, MAX));
    }

    private PresetDependencyTree findNodeByIdx (PresetDependencyTree currentRoot, int idx) {
        return currentRoot.children.get(idx);
    }

    @Override
    public String toString() {
        return "\nPresetDependencyTree{" +
                "numberData=" + numberData +
                ", randomChildrenCount=" + randomChildrenCount +
                ", children=" + children +
                '}' + '\n';
    }
}
