package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            insert(p);
        }
    }

    /**
     * Find nearest point in KD tree.
     * @param x
     * @param y
     * @return
     */
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = root;
        best = nearest(root, goal, best, 0);
        return best.point;
    }

    /**
     * Implement insert function for initialize KD tree.
     * @param p
     */
    private void insert(Point p) {
        root = insert(p, root, 0);
    }

    /**
     * Helpful function to implement nearest function.
     * @param root
     * @param goal
     * @param best
     * @param level decide which dimension to compare.
     * @return
     */
    private Node nearest(Node root, Point goal, Node best, int level) {
        if (root == null) {
            return best;
        }
        if (root.distance(goal) < best.distance(goal)) {
            best = root;
        }
        Node goodSide;
        Node badSide;
        if (level % 2 == 0) {
            if (goal.getX() < root.point.getX()) {
                goodSide = root.left;
                badSide = root.right;
            } else {
                goodSide = root.right;
                badSide = root.left;
            }
        } else {
            if (goal.getY() < root.point.getY()) {
                goodSide = root.left;
                badSide = root.right;
            } else {
                goodSide = root.right;
                badSide = root.left;
            }
        }
        best = nearest(goodSide, goal, best, level + 1);
        double shortestDistance;
        if (level % 2 == 0) {
            shortestDistance = Math.pow(root.point.getX() - goal.getX(), 2);
        } else {
            shortestDistance = Math.pow(root.point.getY() - goal.getY(), 2);
        }
        if (shortestDistance < Point.distance(best.point, goal)) {
            best = nearest(badSide, goal, best, level + 1);
        }
        return best;
    }

    /**
     * Helpful function to implement insert function.
     * @param p
     * @param root
     * @param level decide which dimension to compare.
     * @return
     */
    private Node insert(Point p, Node root, int level) {
        if (root == null) {
            return new Node(p);
        }
        if (level % 2 == 0) {
            if (p.getX() >= root.point.getX()) {
                root.right = insert(p, root.right, level + 1);
            } else {
                root.left = insert(p, root.left, level + 1);
            }
        } else {
            if (p.getY() >= root.point.getY()) {
                root.right = insert(p, root.right, level + 1);
            } else {
                root.left = insert(p, root.left, level + 1);
            }
        }
        return root;
    }

    /**
     * Customized node contains point object and left/right child.
     */
    private class Node {
        Point point;
        Node left;
        Node right;

        public Node(Point point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }

        public double distance(Point p) {
            double x1 = this.point.getX();
            double y1 = this.point.getY();
            double x2 = p.getX();
            double y2 = p .getY();
            return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        }
    }
}
