define(function (require) {
    return {
        getBarItemStyle: require('../../mdl/mixin/makeStyleMapper')(
            [
                ['fill', 'color'],
                ['stroke', 'borderColor'],
                ['lineWidth', 'borderWidth'],
                // Compatitable with 2
                ['stroke', 'barBorderColor'],
                ['lineWidth', 'barBorderWidth'],
                ['opacity'],
                ['shadowBlur'],
                ['shadowOffsetX'],
                ['shadowOffsetY'],
                ['shadowColor']
            ]
        )
    };
});