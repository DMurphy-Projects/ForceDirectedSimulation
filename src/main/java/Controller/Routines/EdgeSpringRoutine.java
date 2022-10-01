package Controller.Routines;

import Maths.FastInverseSqrt;
import Model.Edge;
import Model.ForceNode;

public class EdgeSpringRoutine extends EdgeRoutine {

    double optimalLength, strength;

    public EdgeSpringRoutine(double optimalLength, double strength)
    {
        this.optimalLength = optimalLength;
        this.strength = strength;
    }

    protected void addResultForce(Edge<? extends ForceNode> edge) {
        double[] from = edge.getFrom().getCoordinate(), to = edge.getTo().getCoordinate();
        double[] vector = {from[0] - to[0], from[1] - to[1]};
        double inv = FastInverseSqrt.sqrt((vector[0]*vector[0]) + (vector[1]*vector[1]));

        vector[0] *= inv;
        vector[1] *= inv;

        double springForce = -strength * (1d / (optimalLength * inv));

        if (1d / inv < optimalLength)
        {
            edge.getFrom().addForceVector(new double[]{-vector[0] * springForce, -vector[1] * springForce});
            edge.getTo().addForceVector(new double[]{vector[0] * springForce, vector[1] * springForce});
        }
        else
        {
            edge.getFrom().addForceVector(new double[]{vector[0] * springForce, vector[1] * springForce});
            edge.getTo().addForceVector(new double[]{-vector[0] * springForce, -vector[1] * springForce});
        }
    }
}
