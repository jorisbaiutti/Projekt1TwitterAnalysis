import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('barcharts',function(){
    this.route('tweetsbylanguage');
    this.route('tweetsbytheme');
  });

  this.route('maps', function(){
    this.route('tweetoverview');
    this.route('myfollowers');
  });

  this.route('piecharts', function(){
    this.route('sentimentanalyse');
  });


  this.route('charts');


  this.route('piecharts\\sentimentanalyse');
});

export default Router;
