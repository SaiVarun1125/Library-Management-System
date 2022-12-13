/**
 * @file Treemap action
 */
define(function(require) {

    var echarts = require('../../echarts');
    var helper = require('./helper');

    var noop = function () {};

    var actionTypes = [
        'treemapZoomToNode',
        'treemapRender',
        'treemapMove'
    ];

    for (var i = 0; i < actionTypes.length; i++) {
        echarts.registerAction({type: actionTypes[i], update: 'updateView'}, noop);
    }

    echarts.registerAction(
        {type: 'treemapRootToNode', update: 'updateView'},
        function (payload, ecModel) {

            ecModel.eachComponent(
                {mainType: 'series', subType: 'treemap', query: payload},
                handleRootToNode
            );

            function handleRootToNode(mdl, index) {
                var targetInfo = helper.retrieveTargetInfo(payload, mdl);

                if (targetInfo) {
                    var originViewRoot = mdl.getViewRoot();
                    if (originViewRoot) {
                        payload.direction = helper.aboveViewRoot(originViewRoot, targetInfo.node)
                            ? 'rollUp' : 'drillDown';
                    }
                    mdl.resetViewRoot(targetInfo.node);
                }
            }
        }
    );

});