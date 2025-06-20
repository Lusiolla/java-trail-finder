package lu.karpychev.trailfinder.mapper;

import lu.karpychev.trailfinder.model.TrackPoint;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.util.Arrays;
import java.util.List;

public class LineStringMapper {

    public static LineString toLineString(List<TrackPoint> points) {

        Point[] postgisPoints = points.stream()
                .map(p -> {
                    Point point = new Point(p.getLon(), p.getLat(), p.getElevation());
                    point.dimension = 3;
                    return point;
                })
                .toArray(Point[]::new);

        LineString line = new LineString(postgisPoints);
        line.dimension = 3;
        line.setSrid(4326);
        return line;
    }

    public static List<TrackPoint> fromLineString(PGgeometry points) {
        LineString line = (LineString) points.getGeometry();
        return Arrays.stream(line.getPoints())
                .map(p -> new TrackPoint(p.getY(), p.getX(), p.getZ()))
                .toList();
    }
}
