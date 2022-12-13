define(function (require) {

    var simpleLayoutEdge = require('./simpleLayoutEdge');

    return function (seriesModel) {
        var coordSys = seriesModel.coordinateSystem;
        if (coordSys && coordSys.type !== 'view') {
            return;
        }
        var graph = seriesModel.getGraph();

        graph.eachNode(function (node) {
            var mdl = node.getModel();
            node.setLayout([+mdl.get('x'), +mdl.get('y')]);
        });

        simpleLayoutEdge(graph);
    };
});