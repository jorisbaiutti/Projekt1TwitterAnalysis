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

  QUnit.test('models/mostdiscussedtopic.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'models/mostdiscussedtopic.js should pass ESLint\n\n');
  });

  QUnit.test('models/person.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'models/person.js should pass ESLint\n\n');
  });

  QUnit.test('resolver.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'resolver.js should pass ESLint\n\n');
  });

  QUnit.test('router.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'router.js should pass ESLint\n\n');
  });

  QUnit.test('routes/barcharts/test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/barcharts/test.js should pass ESLint\n\n');
  });

  QUnit.test('routes/charts.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/charts.js should pass ESLint\n\n');
  });

  QUnit.test('routes/index.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/index.js should pass ESLint\n\n');
  });

  QUnit.test('routes/keyvalueanalyse/mostdiscussedtopics.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/keyvalueanalyse/mostdiscussedtopics.js should pass ESLint\n\n');
  });

  QUnit.test('routes/keyvalueanalyse/test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/keyvalueanalyse/test.js should pass ESLint\n\n');
  });

  QUnit.test('routes/linecharts/samplechart.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/linecharts/samplechart.js should pass ESLint\n\n');
  });

  QUnit.test('routes/person/show.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/person/show.js should pass ESLint\n\n');
  });

  QUnit.test('serializers/application.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'serializers/application.js should pass ESLint\n\n');
  });

  QUnit.test('serializers/barcharttest.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'serializers/barcharttest.js should pass ESLint\n\n');
  });

  QUnit.test('serializers/mostdiscussedtopic.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'serializers/mostdiscussedtopic.js should pass ESLint\n\n');
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
