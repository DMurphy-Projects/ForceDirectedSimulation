package Controller.Routines;

import Model.Edge;
import Model.ForceNode;

public abstract class NodeToNodeRoutine implements IRoutine {

    protected abstract void addResultForce(ForceNode n1, ForceNode n2);

    public void doRoutine(ForceNode[] nodes, Edge<? extends ForceNode>[] edges) {
        for (ForceNode n1: nodes)
        {
            for (ForceNode n2: nodes)
            {
                if (n1 == n2) continue;
                addResultForce(n1, n2);
            }
        }
    }
}
