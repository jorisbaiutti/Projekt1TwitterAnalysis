package ch.bfh.assembler;

import be.ceau.chart.BarChart;
import ch.bfh.beans.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class testChartAssembler implements ResourceAssembler<BarChart,Resource<BarChart>> {

    @Override
    public Resource<BarChart> toResource(BarChart entity) {
        Resource<BarChart> resource = new Resource<>(entity);
        resource.add(new Link("http://barcharts/test"));
        return resource;
    }
}
