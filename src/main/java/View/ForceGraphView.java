package View;

import Model.Edge;
import Model.ForceNode;
import ViewController.PanZoomController;

import java.awt.*;
import java.awt.event.*;

public abstract class ForceGraphView<T extends ForceNode, E extends Edge<T>> extends BaseJPanel {

    E[] edges;
    T[] nodes;

    PanZoomController panZoomController = new PanZoomController();

    Point mousePosition;

    public abstract void drawNode(Graphics g, T node, int[] position);
    public abstract void drawEde(Graphics g, E edge, int[] from, int[] to);
    public abstract void drawEdgeLabel(Graphics g, E edge, int[] from, int[] to);

    public ForceGraphView(T[] nodes, E[] edges)
    {
        this.nodes = nodes;
        this.edges = edges;

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    private int[] convert(double[] coord)
    {
        int[] point = new int[coord.length];

        for (int i=0;i<point.length;i++)
        {
            point[i] = (int) Math.floor(coord[i]);
        }

        return point;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(panZoomController.getTransform());

        g.setColor(Color.black);
        for (E e: edges)
        {
            drawEde(g, e, convert(e.getFrom().getCoordinate()), convert(e.getTo().getCoordinate()));
        }
        for (E e: edges)
        {
            drawEdgeLabel(g, e, convert(e.getFrom().getCoordinate()), convert(e.getTo().getCoordinate()));
        }
        for (T n: nodes)
        {
            drawNode(g, n, convert(n.getCoordinate()));
        }

        g2d.dispose();
    }

    public void mousePressed(MouseEvent e) {
        mousePosition = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        double scale = panZoomController.getScale();
        double[] vec = {
                (e.getX() - mousePosition.getX()) / scale,
                (e.getY() - mousePosition.getY()) / scale
        };

        panZoomController.pan(vec);

        mousePosition = e.getPoint();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int wheel = e.getWheelRotation() < 0 ? 1 : -1;
        double[] mouse = {e.getX() - this.getX(), e.getY() - this.getY()};

        panZoomController.zoomOnPosition(mouse, wheel);
    }
}
