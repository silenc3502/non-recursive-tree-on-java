package dataStructure.tree;

import dataStructure.stack.ContextStack;

import java.util.ArrayList;
import java.util.List;

public class DependencyTree {
    final Integer numberData;
    int childrenCount = 0;
    final List<DependencyTree> childrenList = new ArrayList<>();
    final ContextStack<DependencyTree> depsTreeStore = new ContextStack<>();
    final ContextStack<PresetDependencyTree> presetDepsTreeStore = new ContextStack<>();
    int currentReferencedChildIdx = 0;

    public DependencyTree(PresetDependencyTree presetDependencyTree) {
        this.childrenCount = presetDependencyTree.children.size();
        this.numberData = presetDependencyTree.numberData;
    }

    public void buildDepsTree(PresetDependencyTree presetDependencies) {
        // 하위 dependency를 완성하러 이동해야 하는데
        // 이때 현재 상태를 유지하도록 Stack을 사용해야 재귀호출을 회피할 수 있습니다.
        // 그냥 실행하면 아래와 같은 형태가 됩니다.
        /*
        DependencyTree{
            numberData=777, randomChildrenCount=0,
            children=[
                DependencyTree{
                    numberData=10, randomChildrenCount=2,
                    children=[]
                }
            ]
         }
         */
        // 현재 tree 노드에서 다음 할당을 수행할 때
        // 현재 작업하던 Context를 잃어버리므로
        // 현재 작업 Context를 Stack에 저장해야 합니다.

        // 그러므로 조건문을 사용하여 하위로 지속적인 탐색을 하기 위해 currentRoot 개념이 필요합니다.
        // 자바에서는 자기 자신을 의미하는 this로 이를 대체할 수 있습니다.
        // 고로 아래와 같이 dependency에 해당하는 자식 개수가 0이 아닌 동안 루프를 유지하면 됩니다.
        DependencyTree currentRoot = this;
        PresetDependencyTree targetDependency = presetDependencies;

        depsTreeStore.push(currentRoot);
        presetDepsTreeStore.push(targetDependency);

        int loopCount = 0;

        while (!depsTreeStore.isEmpty()) {
            System.out.println("loop count: " + loopCount++);

            if (currentRoot.currentReferencedChildIdx < currentRoot.childrenCount) {
                presetDepsTreeStore.push(targetDependency);
                targetDependency = findTargetDepsByIdx(currentRoot, targetDependency);

                DependencyTree child = makeDependencyTree(targetDependency);
                currentRoot.currentReferencedChildIdx++;
                currentRoot.childrenList.add(child);

                depsTreeStore.push(currentRoot);
                currentRoot = child;
            } else {
                currentRoot = depsTreeStore.pop();
                targetDependency = presetDepsTreeStore.pop();
            }
        }
    }

    private PresetDependencyTree findTargetDepsByIdx (DependencyTree currentRoot, PresetDependencyTree targetDependency) {

        if (currentRoot.currentReferencedChildIdx >= currentRoot.childrenCount) {
            System.out.println("finished to build deps!");
            return null;
        }

        if (targetDependency.randomChildrenCount != 0) {
            return targetDependency.children.get(currentRoot.currentReferencedChildIdx);
        }

        return null;
    }

    private DependencyTree makeDependencyTree (PresetDependencyTree nextTargetDeps) {
        final DependencyTree child = new DependencyTree(nextTargetDeps);
        return child;
    }

    @Override
    public String toString() {
        return "\nDependencyTree{" +
                "numberData=" + numberData +
                ", childrenCount=" + childrenCount +
                ", childrenList=" + childrenList +
                ", currentReferencedChildIdx=" + currentReferencedChildIdx +
                '}' + '\n';
    }
}
