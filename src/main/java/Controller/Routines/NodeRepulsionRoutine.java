package Controller.Routines;

import Maths.FastInverseSqrt;
import Model.ForceNode;

public class NodeRepulsionRoutine extends NodeToNodeRoutine {

    double rad, rad_2, strength;

    public NodeRepulsionRoutine(double rad, double strength)
    {
        this.rad = rad;
        this.strength = strength;
    }

    protected void addResultForce(ForceNode n1, ForceNode n2) {
        double[] from = n1.getCoordinate(), to = n2.getCoordinate();
        double[] vector = {from[0] - to[0], from[1] - to[1]};
        double inv = FastInverseSqrt.sqrt((vector[0]*vector[0]) + (vector[1]*vector[1]));

        vector[0] *= inv;
        vector[1] *= inv;

        if (1d / inv < rad)
        {
            n1.addForceVector(new double[]{vector[0] * strength, vector[1] * strength});
            n2.addForceVector(new double[]{-vector[0] * strength, -vector[1] * strength});
        }
    }
}
