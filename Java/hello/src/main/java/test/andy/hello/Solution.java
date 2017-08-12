package test.andy.hello;

import java.util.Arrays;
import java.util.Comparator;

class Point {
	int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution {
	
    class Dis {
    	public final Point point;
        public final int index;
        public final double dis;
        
        public Dis(Point point, int index, double dis) {
        	this.point = point;
        	this.index = index;
        	this.dis = dis;
        }
    }
    
    class ByDis implements Comparator<Dis> {

		@Override
		public int compare(Dis o1, Dis o2) {
			double o1dis = o1.dis;
			double o2dis = o2.dis;
			if(o1dis > o2dis) {
				return 1;
			}
			if(o1dis < o2dis) {
				return -1;
			}
			if(o1.point.x > o2.point.x) {
				return 1;
			}
			if(o1.point.x < o2.point.x) {
				return -1;
			}
			if(o1.point.y > o2.point.y) {
				return 1;
			}
			if(o1.point.y < o2.point.y) {
				return -1;
			}
			return 0;
		}
    	
    }
    
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Dis[] temp =  new Dis[points.length];
        Point[] result = new Point[k];
        for(int i = 0; i < points.length; i++) {
            double a = Math.abs(points[i].x - origin.x);
            double b = Math.abs(points[i].y - origin.y);
            double dis = a*a + b*b;
            temp[i] = new Dis(points[i], i, dis);
        }
        Arrays.sort(temp, new ByDis());
        for(int i = 0; i < k; i++) {
        	result[i] = points[temp[i].index];
        }
        return result;
    }

}
