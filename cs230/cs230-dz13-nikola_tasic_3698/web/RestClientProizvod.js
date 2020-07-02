/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = {
    // Create this closure to contain the cached modules
    module: function () {
        // Internal module cache.
        var modules = {};
        
        // Create a new module reference scaffold or load an
        // existing module.
        return function (name) {
            // If this module has already been created, return it.
            if (modules[name]) {
                return modules[name];
            }
            
            // Create a module and save it under this name
            return modules[name] = {Views: {}};
        };
    }()
};

(function (models) {
    
// Model for Proizvod entity
    models.Proizvod = Backbone.Model.extend({
        urlRoot: "http://localhost:8080/CS230-DZ13-David_Ivanovic-3585/webresources/entity.proizvod/",
        idAttribute: 'idProizvod',
        defaults: {
            jedinicamere: "",
            naziv: "",
            cena: "",
            tipproizvod: ""
        },
        toViewJson: function () {
            var result = this.toJSON(); // displayName property is used to render item in the list
            result.displayName = this.get('idProizvod');
            return result;
        },
        isNew: function () {
            // default isNew() method imlementation is
            // based on the 'id' initialization which
            // sometimes is required to be initialized.
            // So isNew() is rediefined here
            return this.notSynced;
        },
        sync: function (method, model, options) {
            options || (options = {});
            var errorHandler = {
                error: function (jqXHR, textStatus, errorThrown) {
                    // TODO: put your error handling code here
                    // If you use the JS client from the different domain
                    // (f.e. locally) then Cross-origin resource sharing 
                    // headers has to be set on the REST server side.
                    // Otherwise the JS client has to be copied into the
                    // some (f.e. the same) Web project on the same domain
                    alert('Unable to fulfil the request');
                }}
            
            if (method == 'create') {
                options.url = 'http://localhost:8080/CS230-DZ13-David_Ivanovic-3585/webresources/entity.proizvod/';
            }
            var result = Backbone.sync(method, model, _.extend(options, errorHandler));
            return result;
        }
        
        
    });
    
    
    // Collection class for Proizvod entities
    models.ProizvodCollection = Backbone.Collection.extend({
        model: models.Proizvod,
        url: "http://localhost:8080/CS230-DZ13-David_Ivanovic-3585/webresources/entity.proizvod/",
        sync: function (method, model, options) {
            options || (options = {});
            var errorHandler = {
                error: function (jqXHR, textStatus, errorThrown) {
                    // TODO: put your error handling code here
                    // If you use the JS client from the different domain
                    // (f.e. locally) then Cross-origin resource sharing 
                    // headers has to be set on the REST server side.
                    // Otherwise the JS client has to be copied into the
                    // some (f.e. the same) Web project on the same domain
                    alert('Unable to fulfil the request');
                }}
            
            var result = Backbone.sync(method, model, _.extend(options, errorHandler));
            return result;
        }
    });
    
    
})(app.module("models"));

(function (views) {
    
    views.ListView = Backbone.View.extend({
        tagName: 'ul',
        initialize: function (options) {
            this.options = options || {};
            this.model.bind("reset", this.render, this);
            var self = this;
            this.model.bind("add", function (modelName) {
                var row = new views.ListItemView({
                    model: modelName,
                    templateName: self.options.templateName
                }).render().el;
                $(self.el).append($(row));
            });
        },
        render: function (eventName) {
            var self = this;
            _.each(this.model.models, function (modelName) {
                $(this.el).append(new views.ListItemView({
                    model: modelName,
                    templateName: self.options.templateName
                }).render().el);
            }, this);
            return this;
        }
    });
    
    views.ListItemView = Backbone.View.extend({
        tagName: "li",
        initialize: function (options) {
            this.options = options || {};
            this.model.bind("change", this.render, this);
            this.model.bind("destroy", this.close, this);
        },
        template: function (json) {
            /*
             *  templateName is element identifier in HTML
             *  $(this.options.templateName) is element access to the element
             *  using jQuery 
             */ 
            return _.template($(this.options.templateName).html())(json);
        },
        render: function (eventName) {
            $(this.el).html(this.template(this.model.toViewJson()));
            return this;
        },
        close: function () {
            $(this.el).unbind();
            $(this.el).remove();
        }
        
    });
    
    views.ModelView = Backbone.View.extend({
        initialize: function (options) {
            this.options = options || {};
            this.model.bind("change", this.render, this);
        },
        render: function (eventName) {
            $(this.el).html(this.template(this.model.toViewJson()));
            return this;
        },
        template: function (json) {
            /*
             *  templateName is element identifier in HTML
             *  $(this.options.templateName) is element access to the element
             *  using jQuery 
             */
            return _.template($(this.options.templateName).html())(json);
        },
        /*
         *  Classes "save"  and "delete" are used on the HTML controls to listen events.
         *  So it is supposed that HTML has controls with these classes.
         */
        events: {
            "change input": "change",
            "click .save": "save",
            "click .delete": "drop"
        },
        change: function (event) {
            var target = event.target;
            console.log('changing ' + target.id + ' from: ' + target.defaultValue + ' to: ' + target.value);
        },
        save: function () {
            // TODO : put save code here
            var hash = this.options.getHashObject();
            this.model.set(hash);
            if (this.model.isNew() && this.collection) {
                var self = this;
                this.collection.create(this.model, {
                    success: function () {
                        // see isNew() method implementation in the model
                        self.model.notSynced = false;
                        self.options.navigate(self.model.id);
                    }
                });
            } else {
                this.model.save();
            }
            return false;
        },
        drop: function () {
            this.model.destroy({
                success: function () {
                    /*
                     *  TODO : put your code here
                     *  f.e. alert("Model is successfully deleted");
                     */  
                    window.history.back();
                }
            });
            return false;
        },
        close: function () {
            $(this.el).unbind();
            $(this.el).empty();
        }
    });
    
    // This view is used to create new model element
    views.CreateView = Backbone.View.extend({
        initialize: function (options) {
            this.options = options || {};
            this.render();  
        },
        render: function (eventName) {
            $(this.el).html(this.template());
            return this;
        },
        template: function (json) {
            /*
             *  templateName is element identifier in HTML
             *  $(this.options.templateName) is element access to the element
             *  using jQuery 
             */
            return _.template($(this.options.templateName).html())(json);
        },
        /*
         *  Class "new" is used on the control to listen events.
         *  So it is supposed that HTML has a control with "new" class.
         */
        events: {
            "click .new": "create"
        },
        create: function (event) {
            this.options.navigate();
            return false;
        }
    });
    
})(app.module("views"));


$(function () {
    var models = app.module("models");
    var views = app.module("views");
    
    var AppRouter = Backbone.Router.extend({
        routes: {
            '': 'list',
            'new': 'create'
            ,
            ':id': 'details'
        },
        initialize: function () {
            var self = this;
            $('#header').html(new views.CreateView({
                // tpl-create is template identifier for 'create' block
                templateName: '#tpl-create',
                navigate: function () {
                    self.navigate('new', true);
                }
            }).render().el);
        },
        list: function () {
            this.collection = new models.ProizvodCollection();
            var self = this;
            this.collection.fetch({
                success: function () {
                    self.listView = new views.ListView({
                        model: self.collection,
                        // tpl-proizvod-list-itemis template identifier for item
                        templateName: '#tpl-proizvod-list-item'
                    });
                    $('#sidebar').html(self.listView.render().el);
                    if (self.requestedId) {
                        self.details(self.requestedId);
                    }
                }
            });
        },
        details: function (id) {
            if (this.collection) {
                this.proizvod = this.collection.get(id);
                if (this.view) {
                    this.view.close();
                }
                var self = this;
                this.view = new views.ModelView({
                    model: this.proizvod,
                    // tpl-proizvod-details is template identifier for chosen model element
                    templateName: '#tpl-proizvod-details',
                    getHashObject: function () {
                        return self.getData();
                    }
                });
                $('#content').html(this.view.render().el);
            } else {
                this.requestedId = id;
                this.list();
            }
        },
        create: function () {
            if (this.view) {
                this.view.close();
            }
            var self = this;
            var dataModel = new models.Proizvod();
            // see isNew() method implementation in the model
            dataModel.notSynced = true;
            this.view = new views.ModelView({
                model: dataModel,
                collection: this.collection,
                // tpl-proizvod-details is a template identifier for chosen model element
                templateName: '#tpl-proizvod-details',
                navigate: function (id) {
                    self.navigate(id, false);
                },
                getHashObject: function () {
                    return self.getData();
                }
            });
            $('#content').html(this.view.render().el);
        },
        getData: function () {
            return {
                /*
                 * get values from the HTML controls and put them here as a hash of attributes
                 * f.e.
                 * idProizvod:$('#idProizvod').val(),
                 * ....
                 */
            };
        }
    });
    new AppRouter();
    
    
    Backbone.history.start();
});
