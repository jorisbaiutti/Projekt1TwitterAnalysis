import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return this.get('store').findAll('mostdiscussedtopic');
    },
    setupController: function (controller, model) {
        let labels = new Array();
        model.forEach(d => labels.push(d.id));

        let modelasJSON = model.map(function (x) {
            return x.toJSON();
        })

        let dataArray = new Array();
        modelasJSON.forEach(d => dataArray.push(d.value));
        let chartData = {
            datasets: [{
                label: "most discussed topics",
                data: dataArray,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
            }],
            labels: labels,
            options:  {
                responsive: true
              }
           
        };

        model =  chartData;
        this._super(controller, model);
        controller.set('mostdiscussedtopic', model);
    }
});
