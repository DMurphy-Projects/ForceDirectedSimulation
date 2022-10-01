package Model.UpdateStrategy;

import Model.ForceNode;

public class UpdateStrategyDefault implements IUpdateStrategy {

    double resultFriction;

    public UpdateStrategyDefault(double resultFriction)
    {
        this.resultFriction = resultFriction;
    }

    public void update(ForceNode node) {
        double[] coord = node.getCoordinate(), result = node.getResultant();
        coord[0] += result[0];
        coord[1] += result[1];

        node.setCoordinate(coord);

        result[0] *= resultFriction;
        result[1] *= resultFriction;

        node.setResultant(result);
    }
}
