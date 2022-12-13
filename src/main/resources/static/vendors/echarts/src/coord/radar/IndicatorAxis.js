define(function (require) {

    var zrUtil = require('zrender/core/util');
    var Axis = require('../Axis');

    function IndicatorAxis(dim, scale, radiusExtent) {
        Axis.call(this, dim, scale, radiusExtent);

        /**
         * Axis type
         *  - 'category'
         *  - 'value'
         *  - 'time'
         *  - 'log'
         * @type {string}
         */
        this.type = 'value';

        this.angle = 0;

        /**
         * Indicator name
         * @type {string}
         */
        this.name = '';
        /**
         * @type {module:echarts/mdl/Model}
         */
        this.mdl;
    }

    zrUtil.inherits(IndicatorAxis, Axis);

    return IndicatorAxis;
});