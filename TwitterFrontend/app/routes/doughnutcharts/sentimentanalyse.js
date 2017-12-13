import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return Ember.$.ajax({ url: 'http://localhost:8080/api/doughnutchart/sentimentanalyse', contentType: 'application/json' })
    }
});
