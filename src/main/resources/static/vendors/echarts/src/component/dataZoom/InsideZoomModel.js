/**
 * @file Data zoom mdl
 */
define(function(require) {

    return require('./DataZoomModel').extend({

        type: 'dataZoom.inside',

        /**
         * @protected
         */
        defaultOption: {
            zoomLock: false // Whether disable zoom but only pan.
        }
    });
});