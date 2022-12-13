define(function (require) {

    require('../../mdl/Component').registerSubTypeDefaulter('dataZoom', function (option) {
        // Default 'slider' when no type specified.
        return 'slider';
    });

});