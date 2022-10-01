package Controller.Routines;

import Model.Edge;
import Model.ForceNode;

public abstract class EdgeRoutine implements IRoutine {

    protected abstract void addResultForce(Edge<? extends ForceNode> edge);

    public void doRoutine(ForceNode[] nodes, Edge<? extends ForceNode>[] edges) {
        for (Edge<? extends ForceNode> edge: edges)
        {
            addResultForce(edge);
        }
    }
}
