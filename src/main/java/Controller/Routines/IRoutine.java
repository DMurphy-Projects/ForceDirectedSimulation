package Controller.Routines;

import Model.Edge;
import Model.ForceNode;

public interface IRoutine {

    void doRoutine(ForceNode[] nodes, Edge<? extends ForceNode>[] edges);
}
