import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        let myfollowers = Ember.$.ajax({ url: 'http://localhost:8080/api/map/myfollowers', contentType: 'application/json' });
        let tweetoverview = Ember.$.ajax({ url: 'http://localhost:8080/api/map/mapanalyse', contentType: 'application/json' });
        return [{

            title: "chart 1",
            chartdata: myfollowers,
            link: "http://www.chartjs.org"
        }, {
            title: "chart 2",
            chartdata: tweetoverview,
            link: "http://www.chartjs.org"
        }, ]
    }
});
