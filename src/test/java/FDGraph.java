import Controller.ForceController;
import Controller.Routines.EdgeSpringRoutine;
import Controller.Routines.IRoutine;
import Model.*;
import Model.UpdateStrategy.UpdateStrategyFixed;
import View.ForceGraphView;

import javax.swing.*;
import java.awt.*;

public class FDGraph {

    public static void main(String[] args) throws InterruptedException {
        JFrame window = new JFrame("FD Graph");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(600,600);

        ForceNode<Edge> node1 = new ForceNode(0);
        node1.setUpdateStrategy(new UpdateStrategyFixed());

        ForceNode<Edge> node2 = new ForceNode(1);
        node2.setCoordinate(new double[]{10, 0});
        node2.setUpdateStrategy(new UpdateStrategyFixed());

        Edge edge1 = new Edge<ForceNode>(node1, node2);

        node1.connect(edge1);

        ForceNode[] nodes = new ForceNode[]{node1, node2};
        Edge<ForceNode>[] edges = new Edge[]{edge1};

        final int[] nodeSize = {10, 10};
        ForceGraphView view = new ForceGraphView<ForceNode, Edge<ForceNode>>(nodes, edges) {

            public void drawNode(Graphics g, ForceNode n, int[] position) {
                g.setColor(Color.white);
                g.fillOval(position[0]-nodeSize[0]/2, position[1]-nodeSize[1]/2, nodeSize[0], nodeSize[1]);
                g.setColor(Color.black);
                g.drawOval(position[0]-nodeSize[0]/2, position[1]-nodeSize[1]/2, nodeSize[0], nodeSize[1]);
            }

            public void drawEdgeLabel(Graphics g, Edge<ForceNode> edge, int[] from, int[] to) {

            }

            public void drawEde(Graphics g, Edge e, int[] from, int[] to) {
                g.drawLine(from[0], from[1], to[0], to[1]);
            }
        };

        window.add(view);
        window.setVisible(true);

        ForceController forceController = new ForceController(nodes, edges,
                new IRoutine[]{
                new EdgeSpringRoutine(10 ,0.01)
        });

        while(true)
        {
            forceController.update();
            view.repaint();

            Thread.sleep(100);
        }
    }
}
