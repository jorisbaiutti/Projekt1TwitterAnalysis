'use strict';

define('twitter-frontend/tests/app.lint-test', [], function () {
  'use strict';

  QUnit.module('ESLint | app');

  QUnit.test('adapters/application.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'adapters/application.js should pass ESLint\n\n5:5 - Duplicate key \'namespace\'. (no-dupe-keys)\n6:5 - Duplicate key \'namespace\'. (no-dupe-keys)');
  });

  QUnit.test('app.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'app.js should pass ESLint\n\n');
  });

  QUnit.test('components/card-listing.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'components/card-listing.js should pass ESLint\n\n');
  });

  QUnit.test('resolver.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'resolver.js should pass ESLint\n\n');
  });

  QUnit.test('router.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'router.js should pass ESLint\n\n');
  });

  QUnit.test('routes/barcharts/tweetsbytheme.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/barcharts/tweetsbytheme.js should pass ESLint\n\n');
  });

  QUnit.test('routes/charts.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/charts.js should pass ESLint\n\n');
  });

  QUnit.test('routes/index.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/index.js should pass ESLint\n\n');
  });

  QUnit.test('routes/linecharts/samplechart.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/linecharts/samplechart.js should pass ESLint\n\n');
  });

  QUnit.test('routes/maps/myfollowers.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/maps/myfollowers.js should pass ESLint\n\n');
  });

  QUnit.test('routes/maps/tweetoverview.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/maps/tweetoverview.js should pass ESLint\n\n');
  });

  QUnit.test('serializers/application.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'serializers/application.js should pass ESLint\n\n');
  });
});
define('twitter-frontend/tests/helpers/destroy-app', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = destroyApp;
  function destroyApp(application) {
    Ember.run(application, 'destroy');
  }
});
define('twitter-frontend/tests/helpers/ember-cli-g-maps/register-async-helpers', ['exports', 'twitter-frontend/tests/helpers/ember-cli-g-maps/select-autocomplete-place-helper', 'twitter-frontend/tests/helpers/ember-cli-g-maps/wait-for-google-map-helper', 'twitter-frontend/tests/helpers/ember-cli-g-maps/wait-for-geocode-requests-helper', 'twitter-frontend/tests/helpers/ember-cli-g-maps/stub-geocode-requests-helper'], function (exports, _selectAutocompletePlaceHelper, _waitForGoogleMapHelper, _waitForGeocodeRequestsHelper, _stubGeocodeRequestsHelper) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function () {
    Ember.Test.registerAsyncHelper('selectPlace', function () {
      Ember.Logger.warn('Please replace disabled helper "selectPlace" with "selectAutocompletePlace" helper.\nUsage details here: http://http://matt-jensen.github.io/ember-cli-g-maps/#/place-autocomplete/index');
    });
    Ember.Test.registerAsyncHelper('selectAutocompletePlace', _selectAutocompletePlaceHelper.default);
    Ember.Test.registerAsyncHelper('waitForGoogleMap', _waitForGoogleMapHelper.default);
    Ember.Test.registerAsyncHelper('waitForGeocodeRequests', _waitForGeocodeRequestsHelper.default);
    Ember.Test.registerAsyncHelper('stubGeocodeRequests', _stubGeocodeRequestsHelper.default);
  };
});
define('twitter-frontend/tests/helpers/ember-cli-g-maps/select-autocomplete-place-helper', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function (app) {
    var requestedResult = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;
    var selector = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : '.' + GAUTOCOMPLETE_CLASS;

    return new Ember.Test.promise(function (resolve, reject) {

      // User only provided selector argument
      if (typeof requestedResult === 'string') {
        selector = requestedResult;
        requestedResult = 0;
      }

      longPollExternalElement(GOOGLE_AUTOCOMPLETE_RESULTS).then(function (autocompletePlaces) {
        var textResults = autocompletePlaces.map(function (i, el) {
          return $(el).text();
        });

        var _app$testHelpers$find = app.testHelpers.find(selector),
            _app$testHelpers$find2 = _slicedToArray(_app$testHelpers$find, 1),
            input = _app$testHelpers$find2[0];

        assert('No g-autocomplete component found for selector: ' + selector, input && $(input).hasClass(GAUTOCOMPLETE_CLASS));

        var targetResult = 0;

        /*
         * Set target to requested result if it exists
         */
        if (requestedResult > 0 && requestedResult <= textResults.length - 1) {
          targetResult = parseInt(requestedResult, 10);
        }

        /*
         * Keydown to requested result (40 = down arrow)
         */
        for (var i = 0; i <= targetResult; i++) {
          google.maps.event.trigger(input, 'keydown', { keyCode: 40 });
        }

        // Select active result (13 = Enter)
        google.maps.event.trigger(input, 'keydown', { keyCode: 13 });
        Ember.run.later(function () {
          return resolve(textResults[targetResult]);
        }, 300);
      }, reject);
    });
  };

  exports.longPollExternalElement = longPollExternalElement;

  var _slicedToArray = function () {
    function sliceIterator(arr, i) {
      var _arr = [];
      var _n = true;
      var _d = false;
      var _e = undefined;

      try {
        for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) {
          _arr.push(_s.value);

          if (i && _arr.length === i) break;
        }
      } catch (err) {
        _d = true;
        _e = err;
      } finally {
        try {
          if (!_n && _i["return"]) _i["return"]();
        } finally {
          if (_d) throw _e;
        }
      }

      return _arr;
    }

    return function (arr, i) {
      if (Array.isArray(arr)) {
        return arr;
      } else if (Symbol.iterator in Object(arr)) {
        return sliceIterator(arr, i);
      } else {
        throw new TypeError("Invalid attempt to destructure non-iterable instance");
      }
    };
  }();

  var $ = Ember.$,
      assert = Ember.assert;

  var GAUTOCOMPLETE_CLASS = 'g-autocomplete';
  var GOOGLE_AUTOCOMPLETE_RESULTS = '.pac-container .pac-item';

  function longPollExternalElement(selector) {
    return new Ember.RSVP.Promise(function (resolve, reject) {
      var pollAgain = function () {
        var counter = 0;

        return function () {
          /*
           * NOTE searching for elements potentially outside of #ember-testing container
           */
          var results = $(selector);

          if (results.length) {
            return resolve(results);
          }

          if (counter > 5) {
            return reject();
          }

          counter++;
          Ember.run.later(pollAgain, 300);
        };
      }();

      pollAgain();
    });
  };
});
define("twitter-frontend/tests/helpers/ember-cli-g-maps/setup-test", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function () {};
});
define('twitter-frontend/tests/helpers/ember-cli-g-maps/stub-geocode-requests-helper', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function (app) {
    var config = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

    var onlyPhantomJS = Boolean(config.onlyPhantomJS);

    // Abandon if only stubbing phantomJS
    if (onlyPhantomJS && IS_PHANTOMJS_ENV === false) {
      return;
    }

    assert('A results array is required', config.results && config.results instanceof Array && config.results.length);

    var stubs = void 0;
    if (config.results[0] instanceof Array) {
      // Clone 2 demensional array
      stubs = config.results.map(function (results) {
        return results.map(toPlaceResult);
      });
    } else {
      // Clone 1 demensional into 2 demensional array
      stubs = [config.results.map(toPlaceResult)];
    }

    assert('Geocode stubbed requests are still unresolved', ORIGINAL_GEOCODE === GMaps.prototype.geocode);

    var stubIndex = 0;

    /*
     * Stub GMaps geocode
     */
    GMaps.prototype.geocode = function geocodeStub(_ref) {
      var callback = _ref.callback;

      run(function () {
        callback(stubs[stubIndex], 'OK');
        stubIndex += 1;

        if (stubIndex >= stubs.length) {
          GMaps.prototype.geocode = ORIGINAL_GEOCODE;
        }
      });
    };
  };

  exports.toPlaceResult = toPlaceResult;

  var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) {
    return typeof obj;
  } : function (obj) {
    return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
  };

  var RSVP = Ember.RSVP;
  var getOwner = Ember.getOwner;
  var assert = Ember.assert;
  var copy = Ember.copy;
  var run = Ember.run;
  var Logger = Ember.Logger;

  var ORIGINAL_GEOCODE = GMaps.prototype.geocode;
  var IS_PHANTOMJS_ENV = (typeof window === 'undefined' ? 'undefined' : _typeof(window)) === 'object' && window.hasOwnProperty('_phantom');

  function toPlaceResult(result) {
    var clone = copy(result, true);
    clone.geometry = clone.geometry || {};
    clone.geometry.location = clone.geometry.location || {};

    if (clone.hasOwnProperty('lat') && typeof clone.geometry.location.lat !== 'function') {
      clone.geometry.location.lat = function () {
        return clone.lat;
      };
    }

    if (clone.hasOwnProperty('lng') && typeof clone.geometry.location.lng !== 'function') {
      clone.geometry.location.lng = function () {
        return clone.lng;
      };
    }

    if (clone.hasOwnProperty('address') && !clone.formatted_address) {
      clone.formatted_address = clone.address;
    }

    return clone;
  }
});
define('twitter-frontend/tests/helpers/ember-cli-g-maps/wait-for-geocode-requests-helper', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function (app) {
    var container = getOwner(app) || app.__container__;
    assert('failed to recover application container', container);

    var gMap = container.lookup && container.lookup('service:gMap');
    assert('gMap service lookup failed', gMap);

    return new Ember.Test.promise(function (resolve, reject) {
      Ember.Test.adapter.asyncStart();

      var queue = gMap._geocodeQueue || [];

      if (!queue.length) {
        Logger.warn('Geocode request queue was not found, or is currently empty');
      }

      return RSVP.Promise.all(queue).then(function () {
        Ember.run.scheduleOnce('afterRender', null, resolve);
        Ember.Test.adapter.asyncEnd();
      }).catch(function () {
        reject();
        Ember.Test.adapter.asyncEnd();
      });
    });
  };

  var RSVP = Ember.RSVP;
  var getOwner = Ember.getOwner;
  var assert = Ember.assert;
  var Logger = Ember.Logger;
});
define('twitter-frontend/tests/helpers/ember-cli-g-maps/wait-for-google-map-helper', ['exports', 'ember-cli-g-maps/utils/load-google-maps'], function (exports, _loadGoogleMaps) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function (app) {
    var selector = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : EMBER_CLI_GMAPS_SELECTOR;

    return new Ember.Test.promise(function (resolve, reject) {
      Ember.Test.adapter.asyncStart();

      (0, _loadGoogleMaps.default)().then(function () {
        Ember.run.scheduleOnce('afterRender', function () {
          var $map = $(selector);
          assert('No g-maps component found at selector: ' + selector, !$map.length || !$map.eq(0).hasClass(EMBER_CLI_GMAPS_SELECTOR));

          google.maps.event.addListenerOnce($map.get(0).__GOOGLE_MAP__, 'tilesloaded', function () {
            Ember.run(resolve);
            Ember.Test.adapter.asyncEnd();
          });
        });
      }).catch(function () {
        reject();
        Ember.Test.adapter.asyncEnd();
      });
    });
  };

  var $ = Ember.$,
      assert = Ember.assert;

  var EMBER_CLI_GMAPS_SELECTOR = '.ember-cli-g-map';
});
define('twitter-frontend/tests/helpers/module-for-acceptance', ['exports', 'qunit', 'twitter-frontend/tests/helpers/start-app', 'twitter-frontend/tests/helpers/destroy-app'], function (exports, _qunit, _startApp, _destroyApp) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  exports.default = function (name) {
    var options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

    (0, _qunit.module)(name, {
      beforeEach: function beforeEach() {
        this.application = (0, _startApp.default)();

        if (options.beforeEach) {
          return options.beforeEach.apply(this, arguments);
        }
      },
      afterEach: function afterEach() {
        var _this = this;

        var afterEach = options.afterEach && options.afterEach.apply(this, arguments);
        return resolve(afterEach).then(function () {
          return (0, _destroyApp.default)(_this.application);
        });
      }
    });
  };

  var resolve = Ember.RSVP.resolve;
});
define('twitter-frontend/tests/helpers/resolver', ['exports', 'twitter-frontend/resolver', 'twitter-frontend/config/environment'], function (exports, _resolver, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });


  var resolver = _resolver.default.create();

  resolver.namespace = {
    modulePrefix: _environment.default.modulePrefix,
    podModulePrefix: _environment.default.podModulePrefix
  };

  exports.default = resolver;
});
define('twitter-frontend/tests/helpers/start-app', ['exports', 'twitter-frontend/app', 'twitter-frontend/config/environment'], function (exports, _app, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = startApp;
  function startApp(attrs) {
    var attributes = Ember.merge({}, _environment.default.APP);
    attributes = Ember.merge(attributes, attrs); // use defaults, but you can override;

    return Ember.run(function () {
      var application = _app.default.create(attributes);
      application.setupForTesting();
      application.injectTestHelpers();
      return application;
    });
  }
});
define('twitter-frontend/tests/integration/components/card-listing-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForComponent)('card-listing', 'Integration | Component | card listing', {
    integration: true
  });

  (0, _emberQunit.test)('it renders', function (assert) {
    // Set any properties with this.set('myProperty', 'value');
    // Handle any actions with this.on('myAction', function(val) { ... });

    this.render(Ember.HTMLBars.template({
      "id": "2GNiOqby",
      "block": "{\"symbols\":[],\"statements\":[[1,[18,\"card-listing\"],false]],\"hasEval\":false}",
      "meta": {}
    }));

    assert.equal(this.$().text().trim(), '');

    // Template block usage:
    this.render(Ember.HTMLBars.template({
      "id": "sFVw6JtF",
      "block": "{\"symbols\":[],\"statements\":[[0,\"\\n\"],[4,\"card-listing\",null,null,{\"statements\":[[0,\"      template block text\\n\"]],\"parameters\":[]},null],[0,\"  \"]],\"hasEval\":false}",
      "meta": {}
    }));

    assert.equal(this.$().text().trim(), 'template block text');
  });
});
define('twitter-frontend/tests/test-helper', ['twitter-frontend/tests/helpers/resolver', 'ember-qunit', 'ember-cli-qunit'], function (_resolver, _emberQunit, _emberCliQunit) {
  'use strict';

  (0, _emberQunit.setResolver)(_resolver.default);
  (0, _emberCliQunit.start)();
});
define('twitter-frontend/tests/tests.lint-test', [], function () {
  'use strict';

  QUnit.module('ESLint | tests');

  QUnit.test('helpers/destroy-app.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'helpers/destroy-app.js should pass ESLint\n\n');
  });

  QUnit.test('helpers/module-for-acceptance.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'helpers/module-for-acceptance.js should pass ESLint\n\n');
  });

  QUnit.test('helpers/resolver.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'helpers/resolver.js should pass ESLint\n\n');
  });

  QUnit.test('helpers/start-app.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'helpers/start-app.js should pass ESLint\n\n');
  });

  QUnit.test('integration/components/card-listing-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'integration/components/card-listing-test.js should pass ESLint\n\n');
  });

  QUnit.test('test-helper.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'test-helper.js should pass ESLint\n\n');
  });

  QUnit.test('unit/adapters/application-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/adapters/application-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/models/barcharttest-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/models/barcharttest-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/models/mostdiscussedtopics-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/models/mostdiscussedtopics-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/models/person-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/models/person-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/barcharts/test-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/barcharts/test-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/barcharts/tweetsbytheme-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/barcharts/tweetsbytheme-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/charts-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/charts-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/charts/test-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/charts/test-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/index-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/index-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/keyvalueanalyse/mostdiscussedtopics-test.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'unit/routes/keyvalueanalyse/mostdiscussedtopics-test.js should pass ESLint\n\n3:33 - Unnecessary escape character: \\m. (no-useless-escape)\n3:87 - Unnecessary escape character: \\m. (no-useless-escape)');
  });

  QUnit.test('unit/routes/keyvalueanalyse/test-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/keyvalueanalyse/test-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/linecharts/samplechart-test.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'unit/routes/linecharts/samplechart-test.js should pass ESLint\n\n3:28 - Unnecessary escape character: \\s. (no-useless-escape)\n3:69 - Unnecessary escape character: \\s. (no-useless-escape)');
  });

  QUnit.test('unit/routes/maps/myfollowers-test.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'unit/routes/maps/myfollowers-test.js should pass ESLint\n\n3:22 - Unnecessary escape character: \\m. (no-useless-escape)\n3:57 - Unnecessary escape character: \\m. (no-useless-escape)');
  });

  QUnit.test('unit/routes/maps/tweetoverview-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/maps/tweetoverview-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/mostdiscussedtopics-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/mostdiscussedtopics-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/person-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/person-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/person/show-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/person/show-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/testhome-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/testhome-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/serializers/application-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/serializers/application-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/serializers/barcharttest-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/serializers/barcharttest-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/serializers/mostdiscussedtopic-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/serializers/mostdiscussedtopic-test.js should pass ESLint\n\n');
  });
});
define('twitter-frontend/tests/unit/adapters/application-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('adapter:application', 'Unit | Adapter | application', {
    // Specify the other units that are required for this test.
    // needs: ['serializer:foo']
  });

  // Replace this with your real tests.
  (0, _emberQunit.test)('it exists', function (assert) {
    var adapter = this.subject();
    assert.ok(adapter);
  });
});
define('twitter-frontend/tests/unit/models/barcharttest-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('barcharttest', 'Unit | Model | barcharttest', {
    // Specify the other units that are required for this test.
    needs: []
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var model = this.subject();
    // let store = this.store();
    assert.ok(!!model);
  });
});
define('twitter-frontend/tests/unit/models/mostdiscussedtopics-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('mostdiscussedtopics', 'Unit | Model | mostdiscussedtopics', {
    // Specify the other units that are required for this test.
    needs: []
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var model = this.subject();
    // let store = this.store();
    assert.ok(!!model);
  });
});
define('twitter-frontend/tests/unit/models/person-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('person', 'Unit | Model | person', {
    // Specify the other units that are required for this test.
    needs: []
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var model = this.subject();
    // let store = this.store();
    assert.ok(!!model);
  });
});
define('twitter-frontend/tests/unit/routes/barcharts/test-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:barcharts\test', 'Unit | Route | barcharts\test', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/barcharts/tweetsbytheme-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:barcharts\tweetsbytheme', 'Unit | Route | barcharts\tweetsbytheme', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/charts-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:charts', 'Unit | Route | charts', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/charts/test-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:charts\test', 'Unit | Route | charts\test', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/index-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:index', 'Unit | Route | index', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/keyvalueanalyse/mostdiscussedtopics-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:keyvalueanalyse\mostdiscussedtopics', 'Unit | Route | keyvalueanalyse\mostdiscussedtopics', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/keyvalueanalyse/test-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:keyvalueanalyse\test', 'Unit | Route | keyvalueanalyse\test', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/linecharts/samplechart-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:linecharts\samplechart', 'Unit | Route | linecharts\samplechart', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/maps/myfollowers-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:maps\myfollowers', 'Unit | Route | maps\myfollowers', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/maps/tweetoverview-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:maps\tweetoverview', 'Unit | Route | maps\tweetoverview', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/mostdiscussedtopics-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:mostdiscussedtopics', 'Unit | Route | mostdiscussedtopics', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/person-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:person', 'Unit | Route | person', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/person/show-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:person/show', 'Unit | Route | person/show', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/routes/testhome-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleFor)('route:testhome', 'Unit | Route | testhome', {
    // Specify the other units that are required for this test.
    // needs: ['controller:foo']
  });

  (0, _emberQunit.test)('it exists', function (assert) {
    var route = this.subject();
    assert.ok(route);
  });
});
define('twitter-frontend/tests/unit/serializers/application-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('application', 'Unit | Serializer | application', {
    // Specify the other units that are required for this test.
    needs: ['serializer:application']
  });

  // Replace this with your real tests.
  (0, _emberQunit.test)('it serializes records', function (assert) {
    var record = this.subject();

    var serializedRecord = record.serialize();

    assert.ok(serializedRecord);
  });
});
define('twitter-frontend/tests/unit/serializers/barcharttest-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('barcharttest', 'Unit | Serializer | barcharttest', {
    // Specify the other units that are required for this test.
    needs: ['serializer:barcharttest']
  });

  // Replace this with your real tests.
  (0, _emberQunit.test)('it serializes records', function (assert) {
    var record = this.subject();

    var serializedRecord = record.serialize();

    assert.ok(serializedRecord);
  });
});
define('twitter-frontend/tests/unit/serializers/mostdiscussedtopic-test', ['ember-qunit'], function (_emberQunit) {
  'use strict';

  (0, _emberQunit.moduleForModel)('mostdiscussedtopic', 'Unit | Serializer | mostdiscussedtopic', {
    // Specify the other units that are required for this test.
    needs: ['serializer:mostdiscussedtopic']
  });

  // Replace this with your real tests.
  (0, _emberQunit.test)('it serializes records', function (assert) {
    var record = this.subject();

    var serializedRecord = record.serialize();

    assert.ok(serializedRecord);
  });
});
require('twitter-frontend/tests/test-helper');
EmberENV.TESTS_FILE_LOADED = true;
//# sourceMappingURL=tests.map
