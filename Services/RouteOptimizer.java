package Services;
import java.util.*;

public class RouteOptimizer {
    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    public void addRoute(String from, String to, int distanceKm) {
        graph.putIfAbsent(from, new HashMap<>());
        graph.putIfAbsent(to, new HashMap<>());
        graph.get(from).put(to, distanceKm);
        graph.get(to).put(from, distanceKm);
    }

    public RouteResult calculateShortestPath(String startNode, String endNode) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        List<String> nodes = new ArrayList<>();

        for (String node : graph.keySet()) {
            if (node.equals(startNode)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
            nodes.add(node);
        }

        while (!nodes.isEmpty()) {
            nodes.sort(Comparator.comparingInt(distances::get));
            String smallest = nodes.remove(0);

            if (smallest.equals(endNode)) {
                List<String> path = new ArrayList<>();
                String current = smallest;
                while (previous.containsKey(current)) {
                    path.add(current);
                    current = previous.get(current);
                }
                path.add(startNode);
                Collections.reverse(path);
                return new RouteResult(path, distances.get(endNode));
            }

            if (distances.get(smallest) == Integer.MAX_VALUE) break;

            for (Map.Entry<String, Integer> neighbor : graph.get(smallest).entrySet()) {
                int alt = distances.get(smallest) + neighbor.getValue();
                if (alt < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), alt);
                    previous.put(neighbor.getKey(), smallest);
                }
            }
        }

        return new RouteResult(new ArrayList<>(), -1);
    }

    public static class RouteResult {
        private final List<String> path;
        private final int totalDistance;

        public RouteResult(List<String> path, int totalDistance) {
            this.path = path;
            this.totalDistance = totalDistance;
        }

        public List<String> getPath() { return path; }
        public int getTotalDistance() { return totalDistance; }
    }
}