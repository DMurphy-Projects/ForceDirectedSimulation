package Controller;

import Controller.Routines.IRoutine;
import Model.Edge;
import Model.ForceNode;

public class ForceController {

    Edge<? extends ForceNode>[] edges;
    ForceNode[] nodes;

    IRoutine[] routines;

    public ForceController(ForceNode[] nodes, Edge<? extends ForceNode>[] edges, IRoutine[] routines)
    {
        this.nodes = nodes;
        this.edges = edges;
        this.routines = routines;
    }

    public void update()
    {
        for (IRoutine r: routines)
        {
            r.doRoutine(nodes, edges);
        }

        for (ForceNode n: nodes)
        {
            n.update();
        }
    }
}
