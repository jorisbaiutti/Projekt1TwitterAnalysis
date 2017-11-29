import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('person', function() {
    this.route('show', { path: '/show/:id' });
  });
  this.route('keyvalueanalyse',function(){
    this.route('mostdiscussedtopics',{path: '/mostdiscussedtopics'});
    this.route('test')
  });
  this.route('barcharts',function(){
    this.route('test',{path: '/test'});
    this.route('tweetsbytheme');
  });


  this.route('charts');
  this.route('linecharts', function(){
    this.route('samplechart');
  });

  this.route('maps', function(){
    this.route('tweetoverview');
  });
 
});

export default Router;
