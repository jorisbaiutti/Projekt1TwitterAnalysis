"use strict";



define('twitter-frontend/adapters/application', ['exports', 'ember-data'], function (exports, _emberData) {
    'use strict';

    Object.defineProperty(exports, "__esModule", {
        value: true
    });

    var _DS$JSONAPIAdapter$ex;

    function _defineProperty(obj, key, value) {
        if (key in obj) {
            Object.defineProperty(obj, key, {
                value: value,
                enumerable: true,
                configurable: true,
                writable: true
            });
        } else {
            obj[key] = value;
        }

        return obj;
    }

    exports.default = _emberData.default.JSONAPIAdapter.extend((_DS$JSONAPIAdapter$ex = {
        namespace: 'api'
    }, _defineProperty(_DS$JSONAPIAdapter$ex, 'namespace', 'api/keyvalueanalyse'), _defineProperty(_DS$JSONAPIAdapter$ex, 'namespace', 'api/charts'), _DS$JSONAPIAdapter$ex));
});
define('twitter-frontend/app', ['exports', 'twitter-frontend/resolver', 'ember-load-initializers', 'twitter-frontend/config/environment'], function (exports, _resolver, _emberLoadInitializers, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });


  var App = Ember.Application.extend({
    modulePrefix: _environment.default.modulePrefix,
    podModulePrefix: _environment.default.podModulePrefix,
    Resolver: _resolver.default
  });

  (0, _emberLoadInitializers.default)(App, _environment.default.modulePrefix);

  exports.default = App;
});
define('twitter-frontend/components/card-listing', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.Component.extend({});
});
define('twitter-frontend/components/ember-chart', ['exports', 'ember-cli-chartjs/components/ember-chart'], function (exports, _emberChart) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _emberChart.default;
    }
  });
});
define('twitter-frontend/components/welcome-page', ['exports', 'ember-welcome-page/components/welcome-page'], function (exports, _welcomePage) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _welcomePage.default;
    }
  });
});
define('twitter-frontend/helpers/app-version', ['exports', 'twitter-frontend/config/environment', 'ember-cli-app-version/utils/regexp'], function (exports, _environment, _regexp) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.appVersion = appVersion;
  var version = _environment.default.APP.version;
  function appVersion(_) {
    var hash = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

    if (hash.hideSha) {
      return version.match(_regexp.versionRegExp)[0];
    }

    if (hash.hideVersion) {
      return version.match(_regexp.shaRegExp)[0];
    }

    return version;
  }

  exports.default = Ember.Helper.helper(appVersion);
});
define('twitter-frontend/helpers/pluralize', ['exports', 'ember-inflector/lib/helpers/pluralize'], function (exports, _pluralize) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _pluralize.default;
});
define('twitter-frontend/helpers/singularize', ['exports', 'ember-inflector/lib/helpers/singularize'], function (exports, _singularize) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _singularize.default;
});
define('twitter-frontend/initializers/app-version', ['exports', 'ember-cli-app-version/initializer-factory', 'twitter-frontend/config/environment'], function (exports, _initializerFactory, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var _config$APP = _environment.default.APP,
      name = _config$APP.name,
      version = _config$APP.version;
  exports.default = {
    name: 'App Version',
    initialize: (0, _initializerFactory.default)(name, version)
  };
});
define('twitter-frontend/initializers/container-debug-adapter', ['exports', 'ember-resolver/resolvers/classic/container-debug-adapter'], function (exports, _containerDebugAdapter) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'container-debug-adapter',

    initialize: function initialize() {
      var app = arguments[1] || arguments[0];

      app.register('container-debug-adapter:main', _containerDebugAdapter.default);
      app.inject('container-debug-adapter:main', 'namespace', 'application:main');
    }
  };
});
define('twitter-frontend/initializers/data-adapter', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'data-adapter',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('twitter-frontend/initializers/ember-data', ['exports', 'ember-data/setup-container', 'ember-data'], function (exports, _setupContainer) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'ember-data',
    initialize: _setupContainer.default
  };
});
define('twitter-frontend/initializers/export-application-global', ['exports', 'twitter-frontend/config/environment'], function (exports, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.initialize = initialize;
  function initialize() {
    var application = arguments[1] || arguments[0];
    if (_environment.default.exportApplicationGlobal !== false) {
      var theGlobal;
      if (typeof window !== 'undefined') {
        theGlobal = window;
      } else if (typeof global !== 'undefined') {
        theGlobal = global;
      } else if (typeof self !== 'undefined') {
        theGlobal = self;
      } else {
        // no reasonable global, just bail
        return;
      }

      var value = _environment.default.exportApplicationGlobal;
      var globalName;

      if (typeof value === 'string') {
        globalName = value;
      } else {
        globalName = Ember.String.classify(_environment.default.modulePrefix);
      }

      if (!theGlobal[globalName]) {
        theGlobal[globalName] = application;

        application.reopen({
          willDestroy: function willDestroy() {
            this._super.apply(this, arguments);
            delete theGlobal[globalName];
          }
        });
      }
    }
  }

  exports.default = {
    name: 'export-application-global',

    initialize: initialize
  };
});
define('twitter-frontend/initializers/injectStore', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'injectStore',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('twitter-frontend/initializers/store', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'store',
    after: 'ember-data',
    initialize: function initialize() {}
  };
});
define('twitter-frontend/initializers/transforms', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'transforms',
    before: 'store',
    initialize: function initialize() {}
  };
});
define("twitter-frontend/instance-initializers/ember-data", ["exports", "ember-data/instance-initializers/initialize-store-service"], function (exports, _initializeStoreService) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: "ember-data",
    initialize: _initializeStoreService.default
  };
});
define('twitter-frontend/resolver', ['exports', 'ember-resolver'], function (exports, _emberResolver) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _emberResolver.default;
});
define('twitter-frontend/router', ['exports', 'twitter-frontend/config/environment'], function (exports, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });


  var Router = Ember.Router.extend({
    location: _environment.default.locationType,
    rootURL: _environment.default.rootURL
  });

  Router.map(function () {
    this.route('person', function () {
      this.route('show', { path: '/show/:id' });
    });
    this.route('keyvalueanalyse', function () {
      this.route('mostdiscussedtopics', { path: '/mostdiscussedtopics' });
      this.route('test');
    });
    this.route('barcharts', function () {
      this.route('test', { path: '/test' });
      this.route('tweetsbytheme');
    });

    this.route('charts');
    this.route('linecharts', function () {
      this.route('samplechart');
    });
  });

  exports.default = Router;
});
define('twitter-frontend/routes/barcharts/tweetsbytheme', ['exports'], function (exports) {
    'use strict';

    Object.defineProperty(exports, "__esModule", {
        value: true
    });
    exports.default = Ember.Route.extend({
        model: function model() {
            return Ember.$.ajax({ url: 'http://localhost:8080/api/barchart/countbytheme', contentType: 'application/json' });
        }
    });
});
define("twitter-frontend/routes/charts", ["exports"], function (exports) {
    "use strict";

    Object.defineProperty(exports, "__esModule", {
        value: true
    });
    exports.default = Ember.Route.extend({
        model: function model() {
            return [{

                title: "chart 1",
                text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
                imageurl: "/assets/images/chart.png",
                link: "http://www.chartjs.org"
            }, {
                title: "chart 2",
                text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
                imageurl: "/assets/images/chart.png",
                link: "http://www.chartjs.org"
            }, {
                title: "chart 3",
                text: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore",
                imageurl: "/assets/images/chart.png",
                link: "http://www.chartjs.org"
            }];
        }
    });
});
define('twitter-frontend/routes/index', ['exports'], function (exports) {
    'use strict';

    Object.defineProperty(exports, "__esModule", {
        value: true
    });
    exports.default = Ember.Route.extend({
        beforeModel: function beforeModel() {
            this.replaceWith('charts');
        }
    });
});
define('twitter-frontend/routes/linecharts/samplechart', ['exports'], function (exports) {
    'use strict';

    Object.defineProperty(exports, "__esModule", {
        value: true
    });
    exports.default = Ember.Route.extend({
        model: function model() {
            return Ember.$.ajax({ url: 'http://localhost:8080/api/linecharts/sample', contentType: 'application/json' });
        }
    });
});
define('twitter-frontend/serializers/application', ['exports', 'ember-data'], function (exports, _emberData) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _emberData.default.RESTSerializer.extend({
    normalizeResponse: function normalizeResponse(store, primaryModelClass, hateoasPayload, id, requestType) {
      var restPayload = {};
      hateoasPayload.meta = {};
      hateoasPayload.meta.links = hateoasPayload.links;
      delete hateoasPayload.links;
      restPayload[primaryModelClass.modelName] = hateoasPayload;
      return this._super(store, primaryModelClass, restPayload, id, requestType);
    }
  });
});
define('twitter-frontend/services/ajax', ['exports', 'ember-ajax/services/ajax'], function (exports, _ajax) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _ajax.default;
    }
  });
});
define("twitter-frontend/templates/application", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "wgNwtVBJ", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"class\",\"container-fluid\"],[7],[0,\"\\n  \"],[6,\"nav\"],[9,\"class\",\"navbar navbar-expand-lg navbar-light nav-twitter\"],[7],[0,\"\\n    \"],[4,\"link-to\",[\"index\"],[[\"class\"],[\"navbar-brand\"]],{\"statements\":[[0,\"Twitter Analysie\"]],\"parameters\":[]},null],[0,\"\\n    \"],[6,\"button\"],[9,\"class\",\"navbar-toggler twitter-yellow\"],[9,\"type\",\"button\"],[9,\"data-toggle\",\"collapse\"],[9,\"data-target\",\"#navbarSupportedContent\"],[9,\"aria-controls\",\"navbarSupportedContent\"],[9,\"aria-expanded\",\"false\"],[9,\"aria-label\",\"Toggle navigation\"],[7],[0,\"\\n    \"],[6,\"span\"],[9,\"class\",\"navbar-toggler-icon\"],[7],[8],[0,\"\\n  \"],[8],[0,\"\\n    \"],[6,\"div\"],[9,\"class\",\"collapse navbar-collapse\"],[9,\"id\",\"navbarSupportedContent\"],[7],[0,\"\\n      \"],[6,\"ul\"],[9,\"class\",\"nav navbar-nav\"],[7],[0,\"\\n        \"],[4,\"link-to\",[\"index\"],[[\"tagName\"],[\"li\"]],{\"statements\":[[6,\"a\"],[9,\"href\",\"\"],[7],[0,\"Home\"],[8]],\"parameters\":[]},null],[0,\"\\n      \"],[8],[0,\"\\n    \"],[8],[0,\"\\n  \"],[8],[0,\"\\n\"],[8],[0,\"\\n\"],[6,\"div\"],[9,\"class\",\"container-fluid\"],[7],[0,\"\\n  \"],[1,[18,\"outlet\"],false],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/application.hbs" } });
});
define("twitter-frontend/templates/barcharts/tweetsbytheme", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "4yAP+dIf", "block": "{\"symbols\":[],\"statements\":[[6,\"br\"],[7],[8],[0,\"\\n\"],[1,[25,\"ember-chart\",null,[[\"type\",\"data\",\"height\"],[\"bar\",[19,0,[\"model\",\"data\"]],600]]],false]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/barcharts/tweetsbytheme.hbs" } });
});
define("twitter-frontend/templates/charts", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "qYGsYRNy", "block": "{\"symbols\":[\"cardunit\"],\"statements\":[[6,\"div\"],[9,\"class\",\"jumbotron twitter-blue\"],[7],[0,\"\\n    \"],[6,\"h1\"],[9,\"class\",\"display-3\"],[7],[0,\"Available Charts\"],[8],[0,\"\\n    \"],[6,\"div\"],[9,\"class\",\"row\"],[7],[0,\"\\n        \"],[4,\"each\",[[19,0,[\"model\"]]],null,{\"statements\":[[0,\" \"],[1,[25,\"card-listing\",null,[[\"card\"],[[19,1,[]]]]],false],[0,\" \"]],\"parameters\":[1]},null],[0,\"\\n    \"],[8],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/charts.hbs" } });
});
define("twitter-frontend/templates/components/card-listing", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "eGO+lCrm", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"class\",\"card twitter-yellow\"],[9,\"style\",\"width: 20rem;\"],[7],[0,\"\\n    \"],[6,\"img\"],[9,\"class\",\"card-img-top\"],[10,\"src\",[26,[[20,[\"card\",\"imageurl\"]]]]],[9,\"alt\",\"Card image cap\"],[7],[8],[0,\"\\n    \"],[6,\"div\"],[9,\"class\",\"card-body\"],[7],[0,\"\\n        \"],[6,\"h4\"],[9,\"class\",\"card-title\"],[7],[1,[20,[\"card\",\"value\"]],false],[8],[0,\"\\n        \"],[6,\"p\"],[9,\"class\",\"card-text\"],[7],[1,[20,[\"card\",\"text\"]],false],[8],[0,\"\\n        \"],[6,\"a\"],[10,\"href\",[26,[[20,[\"card\",\"link\"]]]]],[9,\"class\",\"btn btn-primary twitter-orange\"],[7],[0,\"Go somewhere\"],[8],[0,\"\\n    \"],[8],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/components/card-listing.hbs" } });
});
define("twitter-frontend/templates/index", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "Lp7MGlt9", "block": "{\"symbols\":[],\"statements\":[[1,[18,\"outlet\"],false]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/index.hbs" } });
});
define("twitter-frontend/templates/linecharts/samplechart", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "d9X3lGBw", "block": "{\"symbols\":[],\"statements\":[[1,[25,\"ember-chart\",null,[[\"type\",\"data\",\"height\"],[\"line\",[19,0,[\"model\",\"data\"]],600]]],false]],\"hasEval\":false}", "meta": { "moduleName": "twitter-frontend/templates/linecharts/samplechart.hbs" } });
});
define('twitter-frontend/utils/chart-object', ['exports', 'ember-cli-chartjs/utils/chart-object'], function (exports, _chartObject) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _chartObject.default;
    }
  });
});


define('twitter-frontend/config/environment', ['ember'], function(Ember) {
  var prefix = 'twitter-frontend';
try {
  var metaName = prefix + '/config/environment';
  var rawConfig = document.querySelector('meta[name="' + metaName + '"]').getAttribute('content');
  var config = JSON.parse(unescape(rawConfig));

  var exports = { 'default': config };

  Object.defineProperty(exports, '__esModule', { value: true });

  return exports;
}
catch(err) {
  throw new Error('Could not read config from meta tag with name "' + metaName + '".');
}

});

if (!runningTests) {
  require("twitter-frontend/app")["default"].create({"name":"twitter-frontend","version":"0.0.0+2bbc56a1"});
}
//# sourceMappingURL=twitter-frontend.map
