import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return new Ember.RSVP.hash({
            myfollowers: Ember.$.ajax({ url: 'http://localhost:8080/api/map/myfollowers', contentType: 'application/json' }),
            tweetoverview: Ember.$.ajax({ url: 'http://localhost:8080/api/map/mapanalyse', contentType: 'application/json' }),
            tweetsbytheme: Ember.$.ajax({ url: 'http://localhost:8080/api/barchart/countbytheme', contentType: 'application/json' }),
            sentiment: Ember.$.ajax({ url: 'http://localhost:8080/api/piechart/sentimentanalyse', contentType: 'application/json' }),
            tweetsbylanguage: Ember.$.ajax({ url: 'http://localhost:8080/api/barchart/countbylanguage', contentType: 'application/json' }),
            
        })
    }
});
