define(function (require) {

    require('../../mdl/Component').registerSubTypeDefaulter('timeline', function () {
        // Only slider now.
        return 'slider';
    });

});