import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        
            //return this.get('store').findAll('barcharttest');
        return Ember.$.ajax({ url: 'http://localhost:8080/api/charts/test', contentType: 'application/json' });
 
    },
});
