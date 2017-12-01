import Ember from 'ember';

export default Ember.Route.extend({
    model() {

        return [{

            title: "chart 1",
            chartdata: "myfollowers",
            link: "http://www.chartjs.org"
        }, {
            title: "chart 2",
            chartdata: "tweetoverview",
            link: "http://www.chartjs.org"
        }, ]
    }
});
