package Model;

import Model.UpdateStrategy.IUpdateStrategy;
import Model.UpdateStrategy.UpdateStrategyDefault;

public class ForceNode<T extends Edge> extends Node <T>{

    double[] coordinate = {0, 0}, resultant = {0, 0};

    IUpdateStrategy updateStrategy = new UpdateStrategyDefault(0.9);

    public ForceNode(int index) {
        super(index);

        coordinate = new double[]{0, 0};
    }

    public void addForceVector(double[] vector)
    {
        this.resultant[0] += vector[0];
        this.resultant[1] += vector[1];
    }

    public void update()
    {
        updateStrategy.update(this);
    }

    public void setCoordinate(double[] coordinate) {
        this.coordinate[0] = coordinate[0];
        this.coordinate[1] = coordinate[1];
    }

    public double[] getCoordinate() {
        return coordinate;
    }

    public void setResultant(double[] resultant) {
        this.resultant[0] = resultant[0];
        this.resultant[1] = resultant[1];
    }

    public double[] getResultant() {
        return resultant;
    }

    public void setUpdateStrategy(IUpdateStrategy strategy)
    {
        updateStrategy = strategy;
    }
}
