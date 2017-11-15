import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return Ember.$.ajax({ url: 'http://localhost:8080/api/barchart/countbytheme', contentType: 'application/json' });
    }
});
