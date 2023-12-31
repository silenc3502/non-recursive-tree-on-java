import dataStructure.tree.DependencyTree;
import dataStructure.tree.PresetDependencyTree;

public class Main {
    public static void main(String[] args) {

        PresetDependencyTree presetDepsTreeRoot = new PresetDependencyTree();
        // 먼저 디펜던시들을 미리 사전에 준비해놓습니다.
        presetDepsTreeRoot.presetData();
        System.out.println(presetDepsTreeRoot);

        /*
        전략: 실험적으로 Deps Tree 노드를 만듭니다.
        종단은 자식이 없는 즉 0개의 자식 노드가 만들어지는 것과 같습니다.
        여기서 실제 상황을 재현하려면 insert 상황에서부터 자식 개수를 보면서 내려가야 한다는 것입니다.
         */

        DependencyTree depsTreeRoot = new DependencyTree(presetDepsTreeRoot);
        depsTreeRoot.buildDepsTree(presetDepsTreeRoot);

        System.out.println("After All");
        System.out.println(depsTreeRoot);
    }
}
