import Ember from 'ember';

export default Ember.Route.extend({
    model(){
        return[{
        
             title: "chart 1",
             text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
             imageurl: "/assets/images/chart.png",
             link: "http://www.chartjs.org"
         },{
            title: "chart 2",
            text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
            imageurl: "/assets/images/chart.png",
            link: "http://www.chartjs.org"
         },{
            title: "chart 3",
            text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
            imageurl: "/assets/images/chart.png",
            link: "http://www.chartjs.org"
        }]
    }
});
