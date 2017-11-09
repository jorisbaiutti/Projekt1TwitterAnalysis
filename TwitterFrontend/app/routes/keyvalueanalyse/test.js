import Ember from 'ember';

export default Ember.Route.extend({
    model(){
       return {
            datasets: [{
                data: [10, 20, 30]
            }],
        
            // These labels appear in the legend and in the tooltips when hovering different arcs
            labels: [
                'Red',
                'Yellow',
                'Blue'
            ]
        };
    }

});
