package doublee.plantmanager.view;

import java.util.List;

import doublee.plantmanager.entity.Graph;
import doublee.plantmanager.entity.Image;

public class MainVO {
    private Graph graph;
    private List<Image> images;

    public void setGraph(Graph graph){
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setImage(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }
}
